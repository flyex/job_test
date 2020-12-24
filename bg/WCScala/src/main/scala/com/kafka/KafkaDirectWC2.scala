package com.kafka


import kafka.api.OffsetRequest
import kafka.common.TopicAndPartition
import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import kafka.utils.{ZKGroupTopicDirs, ZkUtils}
import org.I0Itec.zkclient.ZkClient
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaUtils, OffsetRange}
import org.apache.spark.streaming.{Duration, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object KafkaDirectWC2 {

  def main(args: Array[String]): Unit = {

    val groupId = "flyex"
    val conf = new SparkConf().setAppName("aa").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val ssc = new StreamingContext(sc,Duration(5000))
    val topic = "order"
    val brokerList = "hdp-01:9092,hdp-02:9092,hdp-03:9092"

    val zkQuorum = "hdp-02:2181,hdp-03:2181,hdp-04:2181"
    val topics:Set[String] = Set(topic)

    val topicDirs = new ZKGroupTopicDirs(groupId,topic)

    val zkTopicPath: String = s"${topicDirs.consumerOffsetDir}"



//    val zkClient = new ZkClient(zkQuorum)
//
//    val i = zkClient.countChildren(zkTopicPath)
//
//    val offsets: String = zkClient.readData[String](s"${zkTopicPath}/0")
//
//    println(zkTopicPath)
//    println(i)
//    println(offsets)


        val kafkaParams = Map(
      "metadata.broker.list" -> brokerList,
      "group.id" -> groupId,
      "auto.offset.reset" -> OffsetRequest.SmallestTimeString
    )

    val zkClinet = new ZkClient(zkQuorum)

    val children = zkClinet.countChildren(zkTopicPath)

    var kafkaStream : InputDStream[(String,String)] = null
    var fromOffsets : Map[TopicAndPartition,Long] = Map()

    if (children > 0){
      for (i <- 0 until children){
        val partitionOffset = zkClinet.readData[String](s"$zkTopicPath/${i}")
        val tp = TopicAndPartition(topic,i)

        fromOffsets += (tp -> partitionOffset.toLong)

      }
      val messageHandler = (mmd: MessageAndMetadata[String,String]) => (mmd.key(),mmd.message())

      kafkaStream = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder,(String,String)](ssc,kafkaParams,fromOffsets,messageHandler)

    }else{
      kafkaStream = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](ssc,kafkaParams,topics)
    }

    var offsetRanges = Array[OffsetRange]()

    kafkaStream.foreachRDD{ kafkaRDD =>
      if (!kafkaRDD.isEmpty()){
        offsetRanges = kafkaRDD.asInstanceOf[HasOffsetRanges].offsetRanges
        val lines: RDD[String] = kafkaRDD.map(_._2)

        val allLines: Array[String] = lines.collect()

        println(allLines)
//        lines.foreachPartition(partition =>
//          partition.foreach(x =>{
//            println(x)
//          })
//        )
      }

      for (o <- offsetRanges){
        val zkPath = s"${topicDirs.consumerOffsetDir}/${o.partition}"
        ZkUtils.updatePersistentPath(zkClinet,zkPath,o.untilOffset.toString)
      }

      ssc.start()
      ssc.awaitTermination()
    }

  }

}

package com.kafka.toRedis

import kafka.api.OffsetRequest
import kafka.common.TopicAndPartition
import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import kafka.utils.{ZKGroupTopicDirs, ZkUtils}
import org.I0Itec.zkclient.ZkClient
import org.apache.spark.SparkConf
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaUtils, OffsetRange}
import org.apache.spark.streaming.{Duration, StreamingContext}


object OrderCount {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("a").setMaster("local[4]")
    val ssc = new StreamingContext(conf,Duration(5000))

    val ipRulesRef: Broadcast[Array[(Long, Long, String)]] = IPUtils.broadcastIpRules(ssc,"D:\\ip.txt")

    val group = "flyex2"
    val topic = "order"
    val brokeList = "hdp-01:9092,hdp-02:9092,hdp-03:9092"
    val zkQuorum = "hdp-02:2181,hdp-03:2181,hdp-04:2181"
    val topics : Set[String] = Set(topic)

    val topicDirs: ZKGroupTopicDirs = new ZKGroupTopicDirs(group,topic)
    val zkTopicPath: String = s"${topicDirs.consumerOffsetDir}"

    val kafkaParams = Map(
      "metadata.broker.list" -> brokeList,
      "group.id" -> group,
      "auto.offset.reset" -> OffsetRequest.SmallestTimeString
    )

    val zkClient = new ZkClient(zkQuorum)

    val children = zkClient.countChildren(zkTopicPath)

    var fromOffset : Map[TopicAndPartition,Long] = Map()
    var kafkaStream : InputDStream[(String,String)] = null

    if (children > 0){
      for (i <- 0 until children){
        val partitionOffset: String = zkClient.readData[String](s"$zkTopicPath/${i}")
        val tp: TopicAndPartition = TopicAndPartition(topic,i)
        fromOffset += (tp -> partitionOffset.toLong)
      }

      val messageHandler = (mmd:MessageAndMetadata[String,String]) => (mmd.key(),mmd.message())

      kafkaStream = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder,(String,String)](ssc,kafkaParams,fromOffset,messageHandler)

    }else{
      kafkaStream = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](ssc,kafkaParams,topics)
    }

    var offsetRanges = Array[OffsetRange]()

    kafkaStream.foreachRDD{ kafkaRDD =>
      if (!kafkaRDD.isEmpty()){
        offsetRanges = kafkaRDD.asInstanceOf[HasOffsetRanges].offsetRanges
        val lines: RDD[String] = kafkaRDD.map(_._2)
        val fields: RDD[Array[String]] = lines.map(_.split(" "))

        /*
        测试输出输入的数据
         */
//        val r: Array[Array[String]] = fields.collect()
//
//        val iterator: Iterator[Array[String]] = r.iterator
//
//        while (iterator.hasNext){
//          val arr: Array[String] = iterator.next()
//          for(i <- arr){
//            println(i)
//          }
//        }

        //总金额累加
        CalculateUtil.calculateIncom(fields)
        //各分类商品累加
        CalculateUtil.calculateItem(fields)
        //根据商品归属地累加
        CalculateUtil.calculateZone(fields,ipRulesRef)

        for (o <- offsetRanges){
          val zkPath = s"${topicDirs.consumerOffsetDir}/${o.partition}"
          ZkUtils.updatePersistentPath(zkClient,zkPath,o.untilOffset.toString)
        }
      }
    }

    ssc.start()
    ssc.awaitTermination()

  }

}

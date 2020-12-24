package com.kafka

import kafka.Kafka
import kafka.api.OffsetRequest
import kafka.common.TopicAndPartition
import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import kafka.utils.{ZKGroupTopicDirs, ZkUtils}
import org.I0Itec.zkclient.ZkClient
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaUtils, OffsetRange}
import org.apache.spark.streaming.{Duration, Seconds, StreamingContext}

object KafkaDirectWC {

  def main(args: Array[String]): Unit = {

    val groupId = "flyex"
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("a")
    val ssc: StreamingContext = new StreamingContext(conf, Seconds(5))
    val topic = "flyex"
    val brokerList = "hdp-01:9092,hdp-02:9092,hdp-03:9092"
    val zkQuorum = "hdp-02:2181,hdp-03:2181,hdp-04:2181"

    val topics: Set[String] = Set(topic)
    //往zk写数据保存偏移量
    val topicDirs = new ZKGroupTopicDirs(groupId, topic)
    //获取zk记录偏移量保存路径
    val zkTopicPath = s"$topicDirs.consumerOffsetDir"

    //配置Kadka参数
    val KafkaParams = Map(
      "metadata.broker.list" -> brokerList,
      "group.id" -> groupId,
      "auto.offset.reset" -> OffsetRequest.SmallestTimeString
    )

    //创建一个ZK客服端
    val zkClient = new ZkClient(zkQuorum)

    val children: Int = zkClient.countChildren(zkTopicPath)

    var kafkaStream: InputDStream[(String, String)] = null
    var fromoffsets: Map[TopicAndPartition, Long] = Map()

    if (children > 0) {
      for (i <- 0 until children) {
        val partitionoffset = zkClient.readData[String](s"$zkTopicPath/${i}")
        val tp: TopicAndPartition = TopicAndPartition(topic, i)
        fromoffsets += (tp -> partitionoffset.toLong)
      }

      val messageHandler = (mmd: MessageAndMetadata[String, String]) => (mmd.key(), mmd.message())
      kafkaStream= KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder, (String, String)](ssc, KafkaParams, fromoffsets, messageHandler)

    } else {
      val kafkaStream: InputDStream[(String, String)] = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, KafkaParams, topics)
    }

    var offsetRanges = Array[OffsetRange]()

    val transform: DStream[(String, String)] = kafkaStream.transform { rdd =>
      offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      rdd
    }

    val messages: DStream[String] = transform.map(_._2)

    messages.foreachRDD{ rdd =>
      rdd.foreachPartition(partition =>
        partition.foreach(x =>{
        println(x)
        })
      )
      for (o <- offsetRanges){
        val zkPath = s"${topicDirs.consumerOffsetDir}/${o.partition}"
        ZkUtils.updatePersistentPath(zkClient,zkPath,o.untilOffset.toString)
      }
    }
    ssc.start()

    ssc.awaitTermination()
  }
}

package com.kafka.review.test

import kafka.common.TopicAndPartition
import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import kafka.utils.ZKGroupTopicDirs
import org.I0Itec.zkclient.ZkClient
import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf}
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaCluster, KafkaUtils}
import org.apache.spark.streaming.kafka.KafkaCluster.Err
import org.apache.spark.streaming.{Milliseconds, StreamingContext}

object CheckpointTest {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("test").setMaster("local[2]")

    val ssc = new StreamingContext(conf,Milliseconds(5000))

    val groupId = "flyex"
    val topic = "ckp"
    //设置topic组
    val topics = Set(topic)

    val brokerList = "hdp-01:9092,hdp-02:9092,hdp-03:9092"

    val kafkaParams = Map(
      "metadata.broker.list" -> brokerList,
      "group.id" -> groupId,
      "auto.offset.reset" -> kafka.api.OffsetRequest.SmallestTimeString
    )

    //创建一个zk对应kafka信息存储的对象,并获取路径
    val topicDirs = new ZKGroupTopicDirs(groupId,topic)
    val zkTopicPath = s"${topicDirs.consumerOffsetDir}"

    //创建一个zk对象，获取kafka偏移量
    val zkQurom = "hdp-02:2181,hdp-03:2181,hdp-04:2181"
    val zkClient = new ZkClient(zkQurom)
    val children = zkClient.countChildren(zkTopicPath)

    //创建kafka流
    var kafkaStream: InputDStream[(String,String)] = null
    //记录zk中各partition对应的偏移量
    var fromOffsets: Map[TopicAndPartition, Long] = Map()

    //判断是否第一次消费kafka主题的数据
    if (children > 0){
      //获取zk中各分区对应的偏移量
      for (i <- 0 until children){
        //获取对应分区的偏移量
        val partitionOffset = zkClient.readData[String](s"$zkTopicPath/${i}")
        //获取这个对象
        val partition: TopicAndPartition = TopicAndPartition(topic,i)
        //将每个分区对应的offset放入map topicAndPartition -> offset
        fromOffsets += (partition -> partitionOffset.toLong)
      }

      val messageHandler = (mmd: MessageAndMetadata[String, String]) => (mmd.key(), mmd.message())

      kafkaStream = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder,(String,String)](ssc,kafkaParams,fromOffsets,messageHandler)
    }else {
      //如果没有保存过offset，则根据默认配置使用最新或者最旧的offset
      kafkaStream = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](ssc,kafkaParams,topics)
    }

    kafkaStream.foreachRDD(kafkaRdd =>{
      val offsetRanges = kafkaRdd.asInstanceOf[HasOffsetRanges].offsetRanges

    })


  }

}

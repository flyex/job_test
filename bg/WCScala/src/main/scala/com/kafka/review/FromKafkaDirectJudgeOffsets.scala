package com.kafka.review

import kafka.common.TopicAndPartition
import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import kafka.utils.{ZKGroupTopicDirs, ZkUtils}
import org.I0Itec.zkclient.ZkClient
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka.KafkaCluster.Err
import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaCluster, KafkaUtils, OffsetRange}
import org.apache.spark.streaming.{Milliseconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object FromKafkaDirectJudgeOffsets {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("kas")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc,Milliseconds(4000))

    //指定kafka参数
    val groupId = "fly1"
    val brokerList = "hdp-01:9092,hdp-02:9092,hdp-03:9092"
    val kafkaParams = Map(
      "metadata.broker.list" -> brokerList,
      "group.id" -> groupId,
      "auto.offset.reset" -> kafka.api.OffsetRequest.SmallestTimeString
    )

    //指定topic
    val topic = "review_1"
    //设置topic组
    val topics = Set(topic)

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
      ////有可能这个sparkStreaming程序挂掉了，kafka只会保留最近七天的数据
      //需要判断zk中存入的记录的偏移量是否还在Kafka保存范围内，如果已
      // 不再，需要使用kafka的最早的偏移量
      var checkOffsets: Map[TopicAndPartition, Long] = Map[TopicAndPartition, Long]()
      val kafkaCluster = new KafkaCluster(kafkaParams)
      //获取到kafka中自己记录的最早偏移量
      val earliestLeaderOffsets: Either[Err, Map[TopicAndPartition, KafkaCluster.LeaderOffset]] = kafkaCluster.getEarliestLeaderOffsets(fromOffsets.keySet)
      //开始判断
      if (earliestLeaderOffsets.isRight){
        val earliestOffsets = earliestLeaderOffsets.right.get
        fromOffsets.map(zkOffsets =>{
          //开始比较zk中和kafka中的偏移量大小
          val kafkaEarliestOffsets: Long = earliestOffsets.get(zkOffsets._1).get.offset
          if (zkOffsets._2 >= kafkaEarliestOffsets){
            zkOffsets
          }else {
            (zkOffsets._1,kafkaEarliestOffsets)
          }
        })
      }
      val messageHandler = (mmd: MessageAndMetadata[String, String]) => (mmd.key(), mmd.message())

      kafkaStream = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder,(String,String)](ssc,kafkaParams,fromOffsets,messageHandler)
    }else {
      //如果没有保存过offset，则根据默认配置使用最新或者最旧的offset
      kafkaStream = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](ssc,kafkaParams,topics)
    }

    //获取偏移量的范围
    var offsetRanges = Array[OffsetRange]()
    //只有kafka的DStream能从当前批次的RDD偏移量取出来
    //第一种利用transform,但是里面的数据类型是普通java类型
//    val transform: DStream[(String, String)] = kafkaStream.transform { rdd =>
//      offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
//      rdd
//    }
//
//    val message: DStream[String] = transform.map(_._2)
//
//    message.foreachRDD {rdd =>
//      rdd.foreachPartition(r => {
//        r.foreach(data =>{
//          println(data)
//        })
//        }
//      )
//
//      for (o <- offsetRanges){
//        val zkPath = s"${topicDirs.consumerOffsetDir}/${o.partition}"
//        ZkUtils.updatePersistentPath(zkClient,zkPath,o.untilOffset.toString)
//      }
//
//    }

    //第二种直接操作kafka的rdd
    kafkaStream.foreachRDD {kafkaRdd =>
      offsetRanges = kafkaRdd.asInstanceOf[HasOffsetRanges].offsetRanges
      val data: RDD[String] = kafkaRdd.map(_._2)
      val res: RDD[(String, Int)] = data.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
      println(res.collect().toBuffer)

      for (o <- offsetRanges){
        val zkPath = s"${topicDirs.consumerOffsetDir}/${o.partition}"
        ZkUtils.updatePersistentPath(zkClient,zkPath,o.untilOffset.toString)
      }
    }
    ssc.start()
    ssc.awaitTermination()


  }

}

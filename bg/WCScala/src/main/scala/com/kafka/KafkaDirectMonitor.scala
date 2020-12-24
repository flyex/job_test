package com.kafka

import com.alibaba.fastjson.{JSON, JSONObject}
import com.kafka.utils.JedisUtils
import com.typesafe.config.ConfigFactory
import kafka.api.OffsetRequest
import kafka.common.TopicAndPartition
import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka.KafkaCluster.Err
import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaCluster, KafkaUtils, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import scalikejdbc.config.DBs
import scalikejdbc._



object KafkaDirectMonitor {

  def main(args: Array[String]): Unit = {

    //加载工程中配置文件
    val load = ConfigFactory.load()

    val brokerList = ""
    val groupId = ""

    //kafka参数
    val kafkaParams:Map[String,String] = Map(
      "metadata.broker.list" -> brokerList,
      "group.id" -> groupId,
      "auto.offset.reset" -> OffsetRequest.SmallestTimeString
    )
    val topics: Set[String] = Set()

    val conf = new SparkConf()

    conf.setAppName(s"${this.getClass.getSimpleName}")
    conf.setMaster("local[2]")

    val ssc: StreamingContext = new StreamingContext(conf,Seconds(2))

    //读取偏移量信息
    DBs.setup()

    val fromOffsets: Map[TopicAndPartition, Long] = DB.readOnly { implicit session =>
      sql"select * from streaming_offset_24 where groupid=?".bind(load.getString("kafka.group.id"))
        .map(rs => {
          (TopicAndPartition(rs.string("topic"), rs.int("partitions")), rs.long("offset"))
        }).toList().apply()
    }.toMap


    val stream: InputDStream[(String,String)] = if(fromOffsets.size == 0){
      //程序第一次启动
      KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](ssc,kafkaParams,topics)
    }else{
      //有可能这个sparkStreaming程序挂掉了，kafka只会保留最近七天的数据
      //需要判断mysql中存入的记录的偏移量是否还在Kafka保存范围内，如果已
      // 不再，需要使用kafka的最早的偏移量
      var checkOffsets: Map[TopicAndPartition, Long] = Map[TopicAndPartition,Long]()
      val kafkaCluster: KafkaCluster = new KafkaCluster(kafkaParams)
      val earliestLeaderOffsets: Either[Err, Map[TopicAndPartition, KafkaCluster.LeaderOffset]] =
                                                  kafkaCluster.getEarliestLeaderOffsets(fromOffsets.keySet)

      if (earliestLeaderOffsets.isRight){
      //得到kafak集群中每个主题分区下对应的最早偏移量并依次和自己MySQL中记录的偏移量对比
        val earliestOffsets: Map[TopicAndPartition, KafkaCluster.LeaderOffset] =
                                                                        earliestLeaderOffsets.right.get

        checkOffsets = fromOffsets.map(my =>{
          val clusterEarliestOffset: Long = earliestOffsets.get(my._1).get.offset
          if (my._2 >= clusterEarliestOffset){
            my
          }else {
            (my._1,clusterEarliestOffset)
          }
        })
      }
      //程序非第一次启动时，创建stream流过程
      val messageHandler = (mm: MessageAndMetadata[String,String]) => (mm.key(),mm.message())
      KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder,(String,String)](ssc,kafkaParams,checkOffsets,messageHandler)

    }

    stream.foreachRDD(rdd =>{
      val offsetRanges: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      /*
      系列业务操作*******************************************************************************************
       */
      val baseData: RDD[(String, String, List[Double], String, String)] = rdd.map(t => JSON.parseObject(t._2))
        .filter(_.getString("sad").equalsIgnoreCase("asd"))
        .map(jsObj => {

          val result = jsObj.getString("bussinessRst")
          val fee: Double = if (result.equals("0000")) jsObj.getDouble("chargefee") else 0
          val isSucc: Double = if (result.equals("0000")) 1 else 0
          val receiveTime = jsObj.getString("receiveNotifyTime")
          val startTime = jsObj.getString("requestId")
          val pCode = jsObj.getString("provinceCode")

          // 消耗时间
          val costime = 123L
          ("A-" + startTime.substring(0, 8), startTime.substring(0, 10), List[Double](1, isSucc, fee, costime.toDouble), pCode, startTime.substring(0, 12))
        })

      baseData.map(t => (t._1,t._3)).reduceByKey((list1,list2) =>{
        (list1.zip(list2)) map(t => t._1+t._2)
      }).foreachPartition(itr =>{
        val jedisClient = JedisUtils.getJedisClient

        itr.foreach(tp =>{
          jedisClient.hincrBy(tp._1,"total",tp._2(0).toLong)

          jedisClient.expire(tp._1,60*60*24*2)
        })

        jedisClient.close()
      })






      //记录偏移************************************************************************************************
      offsetRanges.foreach(ors =>{
        DB.autoCommit { implicit session =>
          sql"REPLACE INTO streaming_offset_24(topic, groupid, partitions, offset) VALUES(?,?,?,?)"
            .bind(ors.topic, load.getString("kafka.group.id"), ors.partition, ors.untilOffset).update().apply()
        }
      })

    })

    ssc.start()
    ssc.awaitTermination()


  }

}

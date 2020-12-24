package com.kafka.utils

import com.alibaba.fastjson.{JSON, JSONObject}
import com.typesafe.config.ConfigFactory
import kafka.common.TopicAndPartition
import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka.KafkaCluster.Err
import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaCluster, KafkaUtils, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object MonitorUtils {

  def rddMethods(rdd : InputDStream[(String,String)]) = {

    val baseData: DStream[(String, String, List[Double], String, String)] = rdd.map(t => JSON.parseObject(t._2))
      .filter(_.getString("serviceName").equalsIgnoreCase("DDD"))
      .map(jsObj => {

        val result = jsObj.getString("bussinessRst")
        val fee: Double = if (result.equals("0000")) jsObj.getDouble("chargefee") else 0
        val isSucc: Double = if (result.equals("0000")) 1 else 0

        val receiveTime = jsObj.getString("receiveNotifyTime")
        val startTime = jsObj.getString("requestId")

        val pCode = jsObj.getString("provinceCode")

        // 消耗时间
        val costime = 123131312L

        ("A-" + startTime.substring(0, 8), startTime.substring(0, 10), List[Double](1, isSucc, fee, costime.toDouble), pCode, startTime.substring(0, 12))

      })

    baseData.map(t => (t._1,t._3)).reduceByKey((list1,list2) =>{
      (list1.zip(list2)) map(t => t._1+t._2)
    })

  }

}

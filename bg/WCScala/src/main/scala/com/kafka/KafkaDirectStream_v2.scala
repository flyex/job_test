package com.kafka

import java.lang

import kafka.consumer.ConsumerConfig
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import scalikejdbc.{DB, SQL}
import scalikejdbc.config.DBs

object KafkaDirectStream_v2 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("java_bike_real_time")

    val streamingContext = new StreamingContext(conf,Seconds(5))

    DBs.setup()
    DB.readOnly{implicit session =>{
      SQL("select * from spark where id=?").bind("")
    }}

    val group = "spark-Streaming-bike"
    val topic = "bike"

    val kafkaParams: Map[String, Object] = Map[String, Object](
      "bootstrap.servers" -> "es-01:9092,es-02:9092,es-03:9092",
      "key.deserializer" -> "classOf[StringDeserializer]",
      "value.deserializer" -> "classOf[StringDeserializer]",
      "group.id" -> group,
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false:lang.Boolean)
    )

    val topics: Array[String] = Array(topic)

    //创建kafka连接

//    KafkaUtils.createDirectStream[String,String](
//      streamingContext,
//      PreferConsistent, kafka0.8没有最优位置
//
//    )
  }

}

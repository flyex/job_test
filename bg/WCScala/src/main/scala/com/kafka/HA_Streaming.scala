package com.kafka

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Duration, Seconds, StreamingContext}

object HA_Streaming {

  private val ckp = "/app/spark/3"

  def createSSC(): StreamingContext = {
    val conf = new SparkConf().setAppName("").setMaster("")
    val ssc: StreamingContext = new StreamingContext(conf, Seconds(3))

    val lines = ssc.socketTextStream("",12)
    //.........


    ssc.checkpoint(ckp)
    ssc
  }

  def main(args: Array[String]): Unit = {

    val context: StreamingContext = StreamingContext.getOrCreate(ckp, createSSC _)


    context.start()
    context.awaitTermination()
  }

}

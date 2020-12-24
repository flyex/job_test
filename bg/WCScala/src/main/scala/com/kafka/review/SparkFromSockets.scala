package com.kafka.review

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Milliseconds, StreamingContext}

object SparkFromSockets {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("as").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val ssc = new StreamingContext(sc,Milliseconds(5000))

    val data = ssc.socketTextStream("hdp-01",3000)

    val res = data.map((_,1)).reduceByKey(_+_)

    res.print()
    ssc.start()
    ssc.awaitTermination()
  }
}

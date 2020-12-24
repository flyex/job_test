package com

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test2 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("a").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val data: RDD[Char] = sc.makeRDD("haha")
    val mapped: RDD[String] = data.flatMap(x=> for(i <- 1 to 10) yield i+""+x)

    println(mapped.collect().toBuffer)

    sc.stop()
  }

}

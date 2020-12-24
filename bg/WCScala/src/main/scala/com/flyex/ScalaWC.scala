package com.flyex

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object ScalaWC {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("scalawc").setMaster("local[2]")

    val sc = new SparkContext(conf)

    val lines:RDD[String] = sc.textFile("D:\\testData")

    val words:RDD[String] = lines.flatMap(_.split(" "))

    val wordAndOne : RDD[(String,Int)]= words.map((_,1))

    val reduced : RDD[(String,Int)]= wordAndOne.reduceByKey(_+_)

    val result:RDD[(String,Int)] = reduced.sortBy(_._2,false)

    result.saveAsTextFile("D:\\testOutput")

    sc.stop()

  }
}

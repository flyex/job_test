package com.flyex

import java.net.URL

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object PSF2 {
  def main(args: Array[String]): Unit = {
    val topn = args(0).toInt
    val conf = new SparkConf().setAppName("s").setMaster("local[2]")
    val jsc = new SparkContext(conf)

    val subjects = Array("bigdata","j2ee","scala")

    val lines: RDD[String] = jsc.textFile("")

    val tAndOne = lines.map(line => {
      val index = line.lastIndexOf("/")
      val teacher = line.substring(index + 1)
      val httpHost = line.substring(0, index)
      val subject = new URL(httpHost).getHost.split("[.]")(0)
      ((subject, teacher), 1)
    })


    val reduced = tAndOne.reduceByKey(_+_)

    for (sb <- subjects){
      val filtered: RDD[((String, String), Int)] = reduced.filter(_._1._1 == sb)
      val topned: Array[((String, String), Int)] = filtered.sortBy(_._2,false).take(topn)
    }



  }
}

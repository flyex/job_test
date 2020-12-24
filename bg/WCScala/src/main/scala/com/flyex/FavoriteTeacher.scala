package com.flyex

import org.apache.spark.{SparkConf, SparkContext}

object FavoriteTeacher {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("favApp").setMaster("local[2]")

    val sc = new SparkContext(conf)

    val lines = sc.textFile("D:\\testData\\teacher.log")

    val TAO = lines.map(line => {
      val strings = line.split("[/]")
      val subject = strings(2).split("[.]")(0)
      val teacher = strings(3)
      (teacher, 1)
    })

    val reduced = TAO.reduceByKey(_+_)

    val sorted = reduced.sortBy(_._2,false)

    val result = sorted.collect()

    println(result.toBuffer)

    sc.stop()

  }

}

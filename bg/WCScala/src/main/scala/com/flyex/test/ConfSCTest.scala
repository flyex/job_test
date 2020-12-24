package com.flyex.test

import org.apache.spark.{SparkConf, SparkContext}

object ConfSCTest {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("aa").setMaster("local[2]")

    conf.set("spark.default.parallelism","50");

    val sc = new SparkContext(conf)

    var data : List[Int] = List()
    for(i <- 1 to 100){
      data = data :+ i
    }

    val dataRdd = sc.parallelize(data)

    print(dataRdd.getNumPartitions)



    sc.stop()

  }

}

package com.flyex.newSpark.test

import org.apache.spark.{SparkConf, SparkContext}

object RddTest {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("asd")
    val sc = new SparkContext(conf)

    val l1 = List(1,0,1)
    val l2 = List(1,1,0)
    val rdd = Seq(("A",List(1,1,0)),("A",List(1,1,0)), ("A",List(1,1,1)))

    val data = sc.makeRDD(rdd)

    val res = data.reduceByKey((list1, list2) => {
      list1 zip list2 map (x => x._1 + x._2)
    })

    println(res.collect().toBuffer)

    sc.stop()








  }

}

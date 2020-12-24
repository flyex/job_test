package com.flyex

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Per_sub_favTeacher {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("psft").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val lines: RDD[String] = sc.textFile("D:\\testData\\1")

    val subTeacherOne = lines.map(line => {
      val strings = line.split("[/]")
      val subject = strings(2).split("[.]")(0)
      val teacher = strings(3)
      ((subject, teacher), 1)
    })

    val reduced = subTeacherOne.reduceByKey(_+_)

    //("bigdata","laozhao"),2   ("bigdata","laoli"),3
    val grouped: RDD[(String, Iterable[((String, String), Int)])] = reduced.groupBy(t => t._1._1)

    val result: RDD[(String, List[((String, String), Int)])] = grouped.mapValues(_.toList.sortBy(_._2).reverse.take(3))

    val asResult: Array[(String, List[((String, String), Int)])] = result.collect()

    println(asResult.toBuffer)

    sc.stop()
  }
}

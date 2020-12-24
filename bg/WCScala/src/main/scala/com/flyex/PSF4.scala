package com.flyex

import java.net.URL

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

import scala.collection.mutable

object PSF4 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("").setMaster("")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("")

    val tAndOne: RDD[((String, String), Int)] = lines.map(line => {
      val index = line.lastIndexOf("/")
      val teacher = line.substring(index + 1)
      val httpHost = line.substring(0, index)
      val subject = new URL(httpHost).getHost.split("[.]")(0)
      ((subject, teacher), 1)
    })

    val subjects: Array[String] = tAndOne.map(it => it._1._1).distinct().collect()
    val myPartitioner = new SubjectPartitioner2(subjects)

    val reduced: RDD[((String, String), Int)] = tAndOne.reduceByKey(myPartitioner,_+_)

    val sorted: RDD[((String, String), Int)] = reduced.mapPartitions(it => {
      it.toList.sortBy(_._2).reverse.take(3).iterator
    })
    sorted.saveAsTextFile("")

  }
}
class SubjectPartitioner2(sbs:Array[String]) extends Partitioner {
  //定义一个规则map
  val rules = new mutable.HashMap[String,Int]()
  var i = 0
  for (sb <-sbs){
    rules.put(sb,i)
    i += 1
  }
  //以下俩方法为必须重写的方法
  //返回的分区数量（下个RDD有多少分区）
  override def numPartitions: Int = sbs.length
  //根据传入RDD的key（String,String）来确定分区号
  override def getPartition(key: Any): Int = {
    val subject: String = key.asInstanceOf[(String,String)]._1
    rules(subject)
  }
}

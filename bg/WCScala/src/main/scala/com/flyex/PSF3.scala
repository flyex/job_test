package com.flyex

import java.net.URL

import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

import scala.collection.mutable

object PSF3 {
  def main(args: Array[String]): Unit = {
    val topn = args(0).toInt
    val conf = new SparkConf().setMaster("").setAppName("")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("")
    val tAndOne = lines.map(line => {
      val index = line.lastIndexOf("/")
      val teacher = line.substring(index + 1)
      val httpHost = line.substring(0, index)
      val subject = new URL(httpHost).getHost.split("[.]")(0)
      ((subject, teacher), 1)
    })
    val reduced: RDD[((String, String), Int)] = tAndOne.reduceByKey(_+_)

    val subjects: Array[String] = reduced.map(i=>i._1._1).distinct().collect()

    val partitioner: SubjectPartitioner = new SubjectPartitioner(subjects)

    val partitioned: RDD[((String, String), Int)] = reduced.partitionBy(partitioner)
    //partitioned.sortBy(_._2,false)  不行


    val result: RDD[((String, String), Int)] = partitioned.mapPartitions(it => {
      it.toList.sortBy(_._2).reverse.take(topn).iterator
    })
    val finall: Array[((String, String), Int)] = result.collect()

    println(finall.toBuffer)

    sc.stop()
  }
}
//自定义一个类继承Partitioner
class SubjectPartitioner(sbs:Array[String]) extends Partitioner {
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
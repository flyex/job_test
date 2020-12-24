package com.flyex.test

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

object My1 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("").setAppName("")
    val sc = new SparkContext(conf)

    val lines: RDD[String] = sc.textFile("")

    val ageAddrAndOne: RDD[((Int, String), Int)] = lines.map(line => {
      val words: Array[String] = line.split("[/]")
      val name: String = words(0)
      val age: Int = words(1).toInt
      val address: String = words(2)
      ((age, address), 1)
    })

    val reduced: RDD[((Int, String), Int)] = ageAddrAndOne.reduceByKey(_+_)


    //val grouped: RDD[(Int, Iterable[((Int, String), Int)])] = reduced.groupBy(_._1._1)

    //搜集年纪范围
    val ages: Array[Int] = reduced.map(_._1._1).distinct().collect()
    //val ref: Broadcast[Array[Int]] = sc.broadcast(ages)

//    for (age <- ageRange){
//      val filterd: RDD[((Int, String), Int)] = reduced.filter(_._1._1 == age)
//
//      val topn = filterd.sortBy(_._2,false).take(2)
//    }
    val agePartitioner = new AgePartitioner(ages)

    val partitioned: RDD[((Int, String), Int)] = reduced.partitionBy(agePartitioner)

    val sorted: RDD[((Int, String), Int)] = partitioned.mapPartitions(it => {
      it.toList.sortBy(_._2).reverse.take(2).iterator
    })

    val tuples = sorted.collect()

  }
}

class AgePartitioner(ageRange:Array[Int]) extends Partitioner{
  override def numPartitions: Int = ageRange.length

  override def getPartition(key: Any): Int = {
    val num: Int = key.asInstanceOf[(Int,String)]._1
    num
  }
}
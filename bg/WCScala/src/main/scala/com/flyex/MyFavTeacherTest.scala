package com.flyex

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

import scala.collection.mutable

object MyFavTeacherTest {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[4]").setAppName("as")
    val sc = new SparkContext(conf)
    sc.setCheckpointDir("hdfs://ns-ha/ck")

    val data = sc.textFile("D:\\testData\\favTeacher")

    //(math:bob) => ((math,bob),1)
    val mapped: RDD[((String, String), Int)] = data.map(t => {
      val splits = t.split(":")
      val subject = splits(0)
      val teacher = splits(1)
      ((subject, teacher), 1)
    })

    val subjects = mapped.map((_._1._1)).distinct().collect()
    //println(subjects.toBuffer)
    //val res = mapped.reduceByKey(_+_).groupBy(_._1._1).mapValues(_.toList.sortBy(_._2).reverse.take(5))
    //println(res.collect().toBuffer)

    val reduced: RDD[((String, String), Int)] = mapped.reduceByKey(_+_)

//    for (sub <- subjects){
//      val filtered = reduced.filter(_._1._1 == sub)
//      val res = filtered.sortBy(_._2,false).take(5)
//      println(res.toBuffer)
//    }

    //test cache
    val cachedd = reduced.persist()
    reduced.checkpoint()

    val subjectPartitioner = new SubjectPartitioner2(subjects)
    val partitionered = reduced.partitionBy(subjectPartitioner)
//    partitionered.foreachPartition(s => {
//      val res = s.toList.sortBy(_._2).reverse.take(5)
//      println(res.toBuffer)
//    })

    val res = partitionered.mapPartitions(it => {
      it.toList.sortBy(_._2).reverse.iterator
    })

    println(res.collect().toBuffer)

    sc.stop()



  }
class SubjectPartitioner2(subs: Array[String]) extends Partitioner{
  val rules = new mutable.HashMap[String,Int]()
  var i = 0

  for (s <- subs){
    rules.put(s,i)
    i += 1
  }
  override def numPartitions: Int = subs.length


  override def getPartition(key: Any): Int = {
    val value: String = key.asInstanceOf[(String,String)]._1
    rules(value)
  }
}
}

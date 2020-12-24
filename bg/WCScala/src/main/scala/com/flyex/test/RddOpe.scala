package com.flyex.test

import java.util

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.concurrent.Awaitable

object RddOpe {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("test").setMaster("local[3]")

    val sc = new SparkContext(conf)

    val data = sc.makeRDD(Seq(1,2,3,4,5)).repartition(3)


//    data.foreachPartition(rdd =>{
//      rdd.foreach(print(_))
//      println("-----------------")
//    })

    val mapFunc = (index: Int, itr: Iterator[(String,Int)]) => {
      itr.map(data => s"分区号：$index,ele：$data")
    }
//
//    val dataShow = data.mapPartitionsWithIndex(mapFunc)
//
//    println(dataShow.collect().toBuffer)

    val pp = data.map(("a",_))

    //val res: RDD[(String, Int)] = pp.aggregateByKey(2)(_+_,_+_)
    //val res = pp.combineByKey(x => (x*3), (m: Int,n: Int)=>m + n, (a:Int,b:Int)=>a + b )
    //val res = pp.combineByKey(x => (x*3), (m: Int,n: Int)=>Math.max(m,n), (a:Int,b:Int)=>a + b )
    println(data.aggregate(3)(Math.max(_,_), _ + _))
    //println(res.collect().toBuffer)



    sc.stop()

  }

}

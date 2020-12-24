package com.flyex.test

import org.apache.spark.{HashPartitioner, RangePartitioner, SparkConf, SparkContext}

object TestMapPartitionWithIndex {

  def main(args: Array[String]): Unit = {

    val fxMapPartition = (i:Int,itr: Iterator[(String,Int)]) => {
      itr.map(e => s"partID:$i,value:$e")
    }

    def mapPartition(i : Int,itr : Iterator[(String,Int)]): Iterator[String] = {
      itr.toList.map(e => "partId:"+ i + ",value="+ e._1 + "," + e._2).iterator
    }

    val conf = new SparkConf().setMaster("local[2]").setAppName("tets")

    val sc = new SparkContext(conf)

    val data = sc.makeRDD(Seq(("a",1),("a",2),("a",2),("a",2),("a",2),("a",2),("a",2),("c",3),("b",3)))

    val data2 = data.partitionBy(new RangePartitioner(2,data))

    //val res = data2.mapPartitionsWithIndex(fxMapPartition)
    //println(res.collect().toBuffer)

    data2.foreachPartition(rdd =>{
     val length = rdd.toList.length
      println(length)
    })

    //println(res)

    sc.stop()
  }

}

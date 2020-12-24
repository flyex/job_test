package com.sort

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, RangePartitioner, SparkConf, SparkContext}

object RepartitionAndSort {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("aa")
    val sc = new SparkContext(conf)

    val line = sc.parallelize(Seq("1","2","3","4","5","5","4","3","7","8","9","3","4","5","6","7","8","9"),3)
    //println(line.getNumPartitions)

    val resultRDD = line.flatMap(_.split(",")).map((_,1)).reduceByKey(_+_).map(t =>(t._2,t._1))

    implicit val yourOrdering = new Ordering[String]{
      override def compare(x: String, y: String): Int = {

        val xv = x.split("_")
        val xv_1 = xv(0).toInt
        val xv_2 = xv(1).toInt
        val yv = y.split("_")
        val yv_1 = yv(0).toInt
        val yv_2 = yv(1).toInt
        if (xv_1 == yv_1){
          yv_2 - xv_2
        }else {
          xv_1 - yv_1
        }
      }
    }

    val value = resultRDD.map(x => (x._1+ "_" + x._2, x._2)).repartitionAndSortWithinPartitions(new MyPartitioner(2))

    value.foreachPartition(t =>{

      val finalV = t.map(tuple => {
        val fields = tuple._1.split("_")
        ("数字:" + fields(1), "次数:" + fields(0))
      })
      //new RangePartitioner
      println(finalV.toBuffer)

    })




  }

}

class MyPartitioner(partitions:Int) extends Partitioner {
  override def numPartitions: Int = partitions

  override def getPartition(key: Any): Int = {
    val k = key.asInstanceOf[String]
    Math.abs(k.hashCode() % numPartitions)
  }
}

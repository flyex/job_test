package com.sort

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object MySort1 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("asd").setMaster("local[2]")

    val sc = new SparkContext(conf)

    val lines: RDD[String] = sc.parallelize(Array("laoduan 30 99",
      "laozhao 29 9999",
      "laozhang 28 98",
      "laoyang 28 99"))

    val userRDD: RDD[User] = lines.map(line => {
      val fields = line.split(" ")
      val name = fields(0)
      val age = fields(1).toInt
      val fv = fields(2).toInt
      new User(name, age, fv)
    })

    val sorted: RDD[User] = userRDD.sortBy(o => o)


    val r = sorted.collect()

    println(r.toBuffer)

    sc.stop()




  }

}

class User(val name:String,val age:Int,val fv:Int) extends Ordered[User] with Serializable {
  override def compare(that: User): Int = {
    if (this.fv == that.fv){
      this.age - that.age
    } else {
      that.fv-this.fv
    }
  }

  override def toString: String = s"name:$name, age:$age, fv:$fv"
}

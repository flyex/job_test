package com.sort

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object MySort2 {

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setAppName("asd").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val lines: RDD[String] = sc.parallelize(Array("lz 90 18","xz 99 30","ld 99 28"))

    val tpRdd: RDD[(String, Int, Int)] = lines.map(line => {
      val fields = line.split(" ")
      val name = fields(0)
      val fv = fields(1).toInt
      val age = fields(2).toInt
      (name, fv,age)
    })

    //val sorted: RDD[(String, Int, Int)] = tpRdd.sortBy(t => new User2(t._3,t._2))

    /*
    隐式申明
    */
//    implicit val rules = Ordering[(Int,Int)].on[(String,Int,Int)](t =>(-t._2,t._3))
//    val sorted: RDD[(String, Int, Int)] = tpRdd.sortBy(t=>t)

    /*
    隐式申明2
    */
    import SortRules.Ordering1
    val sorted: RDD[(String, Int, Int)] = tpRdd.sortBy(t=>FvAndAge(t._2,t._3))
    /*根据元组特性排
      val sorted: RDD[(String, Int, Int)] = tpRdd.sortBy(t=>(-t._2,t._3))
     */

    println(sorted.collect().toBuffer)

  }

}

//class User2(val age:Int,val fv:Int) extends Ordered[User] with Serializable {
//  override def compare(that: User): Int = {
//    if (this.fv == that.fv){
//      this.age-that.age
//    }else{
//      that.fv-this.fv
//    }
//  }
//}
case class FvAndAge(fv:Int,age:Int)
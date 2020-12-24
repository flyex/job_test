package com.flyex.test

import org.apache.spark._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SQLContext}

object DFDS {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("a")

    val sc = new SparkContext(conf)

    //val spark = new SQLContext(sc)

    val dataOriginal = Seq((1,"dog",18),(2,"pig",15),(3,"chicken",22),(4,"pig",16))

    val rddData: RDD[(Int, String, Int)] = sc.parallelize(dataOriginal)

    val animalses: RDD[Animals] = rddData.map(data => {
      Animals(data._1, data._2, data._3)
    })

    //TODO  test dataset/dataFrame
    //import spark.implicits._
    //val aniamlSet: Dataset[Animals] = spark.createDataset(animalses)

//    val frame: DataFrame = animalses.toDF()
//
//    frame.registerTempTable("animal")
//
//    val frame2: DataFrame = spark.sql("select * from animal")
//
//    frame2.show()

    //TODO 隐式对象
//    import OrderAll.OrderingAnimals
//    val sorted = rddData.sortBy(t => Animals(t._1,t._2,t._3))

    //TODO 隐式方法
//    implicit def orderAnimal(ani:Animals):Ordered[Animals] = new Ordered[Animals]{
//      override def compare(that: Animals): Int = {
//        that.age - ani.age
//      }
//    }
//    val sorted = animalses.sortBy(t => t)

    //TODO 隐式转换
    implicit val ord = Ordering[(Int,Int)].on[(Int, String, Int)](x=>(-x._1,-x._3))
    val sorted = rddData.sortBy(r => r)

    println(sorted.collect().toBuffer)

    sc.stop()




  }

}

case class Animals(id:Integer,name:String,age:Integer)



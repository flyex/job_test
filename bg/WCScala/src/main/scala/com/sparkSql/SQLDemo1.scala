package com.sparkSql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

object SQLDemo1 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("asd").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val sqlContext = new SQLContext(sc)

    val lines = sc.textFile("hdfs://hdp-01:9000/sparkSqlData/person")

    val boyRdd: RDD[Boy] = lines.map(line => {
      val fields = line.split(",")
      val id = fields(0).toLong
      val name = fields(1)
      val age = fields(2).toInt
      val fv = fields(3).toDouble
      Boy(id, name, age, fv)
    })

    import sqlContext.implicits._
    val bdf: DataFrame = boyRdd.toDF

    bdf.registerTempTable("t_boy")

    val result: DataFrame = sqlContext.sql("SELECT * FROM t_boy where id=3")

    result.show()

    sc.stop()
  }
}
case class Boy(id:Long,name:String,age:Int,fv:Double)
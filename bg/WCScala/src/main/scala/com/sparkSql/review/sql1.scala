package com.sparkSql.review

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

object sql1 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("")

    val sc = new SparkContext(conf)

    val sqlContext = new SQLContext(sc)

    val personRDD: RDD[String] = sc.textFile("hdfs://hdp-01:9000/sparkSqlData")

    val tpPerson: RDD[Person] = personRDD.map(line => {
      val fields = line.split(",")
      val id = fields(0).toLong
      val name = fields(1)
      val age = fields(2).toInt
      val fv = fields(3).toInt
      Person(id, name, age, fv)
    })

    import sqlContext.implicits._
    val personDF: DataFrame = tpPerson.toDF

    personDF.createTempView("t_p")

    val result: DataFrame = sqlContext.sql("select * from t_p order by fv desc ,age asc")

    result.show()

    sc.stop()


  }

}
case class Person(id: Long,name: String,age: Int,fv: Int)
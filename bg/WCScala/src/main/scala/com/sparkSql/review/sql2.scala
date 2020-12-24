package com.sparkSql.review

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{IntegerType, LongType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SQLContext}

object sql2 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("")

    val sc = new SparkContext(conf)

    val sqlContext = new SQLContext(sc)

    val personRDD: RDD[String] = sc.textFile("hdfs://hdp-01:9000/sparkSqlData")

    val tpPerson: RDD[Row] = personRDD.map(line => {
      val fields = line.split(",")
      val id = fields(0).toLong
      val name = fields(1)
      val age = fields(2).toInt
      val fv = fields(3).toInt
      Row(id, name, age, fv)
    })

    val structType: StructType = StructType(List(
      StructField("id", LongType, true),
      StructField("name", StringType, true),
      StructField("age", IntegerType, true),
      StructField("fv", IntegerType, true)
    ))

    val personDF: DataFrame = sqlContext.createDataFrame(tpPerson,structType)

    personDF.printSchema()

    import sqlContext.implicits._
    personDF.select($"age"+1).show()

  }

}

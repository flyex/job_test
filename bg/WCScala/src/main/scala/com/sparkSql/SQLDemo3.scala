package com.sparkSql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{DoubleType, IntegerType, LongType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

object SQLDemo3 {

  def main(args: Array[String]): Unit = {

    //SparkSession方式
    val session: SparkSession = SparkSession.builder().appName("ad").master("local[2]").getOrCreate()

    val lines: RDD[String] = session.sparkContext.textFile("hdfs://hdp-01:9000/sparkSqlData/person")

    //session.read.textFile("").toDF("")


    val rowRdd: RDD[Row] = lines.map(line => {
      val fields: Array[String] = line.split(",")
      val id = fields(0).toLong
      val name = fields(1)
      val age = fields(2).toInt
      val fv = fields(3).toDouble
      Row(id, name, age, fv)
    })

    val schema: StructType = StructType(List(
      StructField("id", LongType, true),
      StructField("name", StringType, true),
      StructField("age", IntegerType, true),
      StructField("fv", DoubleType, true)
    ))

    val df: DataFrame = session.createDataFrame(rowRdd,schema)

    import session.implicits._
    val df1: Dataset[Row] = df.where($"fv" >=99).orderBy($"fv" desc,$"age" asc)

    df1.show()

    session.stop()

  }

}

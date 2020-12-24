package com.flyex

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

object Text2Parquet {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("test")

    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val data: RDD[String] = sc.textFile("D:\\testData\\text\\1.txt")

    val wordWithRow = data.map(line => {
      val words: Array[String] = line.split(",")
      val id = words(0)
      val name = words(1)
      Row(id, name)
    })

    val schema = StructType(
      List(
        StructField("id",StringType),
        StructField("name",StringType)
      )
    )

    val res: DataFrame = sqlContext.createDataFrame(wordWithRow,schema)

    res.repartition(1).write.parquet("D:\\testDataOut\\parquet")

    sc.stop()

  }
}

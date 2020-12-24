package com.sparkSql

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object wcBySql {

  def main(args: Array[String]): Unit = {

    //创建sparkSession
    val sqlContext: SparkSession = SparkSession.builder().
      appName("sd").
      master("local[2]").
      getOrCreate()

    //使用dataSet操作 对RDD的进一步封装  更智能
    val ds: Dataset[String] = sqlContext.
      read.textFile("hdfs://hdp-01:9000/wcInput")

    import sqlContext.implicits._
    val words: Dataset[String] = ds.flatMap(_.split(" "))

    words.createTempView("t_wc")

    val result: DataFrame = sqlContext.sql("select value as word,count(*) as counts from " +
      "t_wc group by value order by counts desc")

    result.show()

    sqlContext.stop()


  }
}

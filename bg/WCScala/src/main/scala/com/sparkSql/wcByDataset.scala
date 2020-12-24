package com.sparkSql

import org.apache.spark.sql.{Dataset, Row, SparkSession}

object wcByDataset {

  def main(args: Array[String]): Unit = {

    val spark: SparkSession = SparkSession.builder().
      appName("ad").
      master("local[2]").
      getOrCreate()

    //spark.conf.set("spark.sql.autoBroadcastJoinThreshold",1024*1024*10)
    val lines: Dataset[String] = spark.read.textFile("hdfs://hdp-01:9000/wcInput")

    import spark.implicits._
    //val s: Dataset[(Int, String)] = Seq((1,"bob"),(2,"flyex")).toDS()

    import spark.implicits._
    val ds: Dataset[String] = lines.flatMap(_.split(" "))

    //dataSet的api(DSL)
    //val ds2: Dataset[Row] = ds.groupBy($"value" as "word").count().orderBy($"count" desc)

    //导入聚合函数
    import org.apache.spark.sql.functions._
    val ds2: Dataset[Row] = ds.groupBy($"value" as "word").
      agg(count("*") as "counts").
      sort($"counts" desc)

    ds2.show()

    spark.stop()



  }
}

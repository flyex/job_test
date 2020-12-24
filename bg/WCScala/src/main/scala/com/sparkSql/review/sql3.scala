package com.sparkSql.review

import org.apache.spark.sql.{Dataset, Row, SparkSession}

object sql3 {

  def main(args: Array[String]): Unit = {

    val spark: SparkSession = SparkSession.builder().appName("s").master("local[2]").getOrCreate()

    val person: Dataset[String] = spark.read.textFile("hdfs://hdp-01:9000/sparkSqlData")

    import spark.implicits._
    val tp: Dataset[(Long, String, Int, Int)] = person.map(p => {
      val fields = p.split(",")
      val id = fields(0).toLong
      val name = fields(1)
      val age = fields(2).toInt
      val fv = fields(3).toInt
      (id, name, age, fv)
    })
    tp.show()

    import spark.implicits._
    val words: Dataset[String] = person.flatMap(_.split(","))

//    val result: Dataset[Row] = words.groupBy($"value" as "word").count().orderBy($"count" desc)

//    import org.apache.spark.sql.functions._
//    val result: Dataset[Row] = words.groupBy($"value" as "word").agg(count("*") as "counts").sort($"counts" desc)

    words.createTempView("t_p")

    val result = spark.sql("select value as word ,count(*) as counts from t_p group by value order by counts desc")

    //result.show()

    spark.stop()

  }

}

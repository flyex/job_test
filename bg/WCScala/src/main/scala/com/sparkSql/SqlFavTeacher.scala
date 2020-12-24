package com.sparkSql

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object SqlFavTeacher {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("s").master("local[2]").getOrCreate()

    val tealog: Dataset[String] = spark.read.textFile("hdfs://hdp-01:9000/favTeacher")


    import spark.implicits._
    val teacher: DataFrame = tealog.map(line => {
      val fields: Array[String] = line.split("[/]")
      val subject = fields(2).split("[.]")(0)
      val teacher: String = fields(3)
      (subject, teacher)
    }).toDF("subject", "teacher")

    teacher.createTempView("t_tea")

    val t1: DataFrame = spark.sql("select subject,teacher,count(*) as counts from t_tea group by subject,teacher")

    t1.createTempView("t1")
    //counts,row_number() over(partition by subject order by counts) as sub_rk
    val t2 = spark.sql("select subject,teacher,counts,rank() over(order by counts) as gl_rk from t1")

    t2.show()

    spark.stop()


  }

}

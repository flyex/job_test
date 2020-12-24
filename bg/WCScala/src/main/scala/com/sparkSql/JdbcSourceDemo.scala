package com.sparkSql

import java.util.Properties

import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object JdbcSourceDemo {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("sd").master("local[2]").getOrCreate()

    val conn: DataFrame = spark.read.format("jdbc").options(
      Map("url" -> "jdbc:mysql://hdp-01:3306/taotaoshop",
        "driver" -> "com.mysql.jdbc.Driver",
        "dbtable" -> "test",
        "user" -> "root",
        "password" -> "2112qwe"
      )
    ).load()

//    conn.printSchema()
//    conn.show()
//    val filtered: Dataset[Row] = conn.filter(r => {
//      r.getAs[Int]("age") <= 40
//    })

    //lambda表达式
    import spark.implicits._
    //    conn.filter($"age" <= 40)
    //    conn.where($"age" <= 20)
    //val filtered: Dataset[Row] = conn.filter("age <= 20")
    //filtered.show()

    val r: DataFrame = conn.select($"id",$"name",$"age"*2 as "age")

//    val prop = new Properties()
//    prop.put("user","root")
//    prop.put("password","2112qwe")
//    r.write.mode("append").jdbc("jdbc:mysql://hdp-01:3306/taotaoshop","test",prop)

    r.write.json("d:\\nouse")
    spark.stop()

  }

}

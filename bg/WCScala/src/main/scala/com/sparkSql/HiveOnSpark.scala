package com.sparkSql

import org.apache.spark.sql.{DataFrame, SparkSession}

object HiveOnSpark {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("asd").master("local[2]").enableHiveSupport().getOrCreate()

    val r: DataFrame = spark.sql("desc mm")

    r.show()

    spark.close()

  }

}

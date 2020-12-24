package com.flyex.utils

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

object FIle2Parquet {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName(s"${this.getClass.getSimpleName}").setMaster("local[2]")

    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")

    val sc = new SparkContext(conf)

    val ssc: SQLContext = new SQLContext(sc)

    ssc.setConf("spark.sql.parquet.compression.codec", "gzip")

    val line: RDD[String] = sc.textFile("D:\\testData\\advertiser\\original info\\user.info")

    val dataRow: RDD[Row] = line
      .map(line => {  line.split(",", -1) })
      .filter(_.length >= 3)
      .map(arr => { Row(arr(0), arr(1).toInt, arr(2), arr(3)) })

    val scheam: StructType = StructType(Seq(
      StructField("name", StringType),
      StructField("age", IntegerType),
      StructField("sex", StringType),
      StructField("habit", StringType)
    ))

    val dataFrame: DataFrame = ssc.createDataFrame(dataRow, scheam).repartition(1)

    dataFrame.write.parquet("D:\\testDataOut\\advertiser\\parquet_user_info")

    sc.stop()

  }

}

package com.flyex.analyse

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object UserAnalyse{

  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setAppName(s"${this.getClass.getSimpleName}").setMaster("local[2]")

    val sc = new SparkContext(sparkConf)

    val sQLContext = new SQLContext(sc)

    val dataFrame = sQLContext.read.parquet("D:\\testDataOut\\advertiser\\parquet_user_info")

    dataFrame.createTempView("t_user")

    val result = sQLContext.sql(
      "select * from t_user where age > 20"
    )

    result.write.json("D:\\testDataOut\\advertiser\\userAnalyse")

    sc.stop()
  }




}

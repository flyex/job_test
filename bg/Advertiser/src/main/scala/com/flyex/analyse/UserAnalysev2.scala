package com.flyex.analyse

import com.flyex.bean.User
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SQLContext, SparkSession}

object UserAnalysev2 {

  def main(args: Array[String]): Unit = {

    //val session: SparkSession = SparkSession.builder().appName("a").master("local[2]").getOrCreate()

    val sparkConf: SparkConf = new SparkConf().setAppName(s"${this.getClass.getSimpleName}").setMaster("local[2]")

    val sc = new SparkContext(sparkConf)

    val sQLContext = new SQLContext(sc)

    val parquetData: DataFrame = sQLContext.read.parquet("D:\\testDataOut\\advertiser\\parquet_user_info")


    parquetData.rdd.map(row => {
      val name = row.getAs[String]("name")
      val habit = row.getAs[String]("habit")
      (name,habit)
    }).filter(_._2.contains("ball")).saveAsTextFile("D:\\testDataOut\\advertiser\\userAnalyse")


//    sc.textFile("D:\\testData\\advertiser\\original info").map(_.split(",",-1))
//        .filter(_.length > 1)
//        .map(arr =>{
//          val user: User = User(arr)
//          (user.name,user.sex,user.habit)
//        }).saveAsTextFile("D:\\testDataOut\\advertiser\\userAnalyse")


    sc.stop()
  }
}

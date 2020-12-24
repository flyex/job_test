package com.flyex.utils

import com.flyex.bean.User
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

object File2Parquet_v2 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName(s"${this.getClass.getSimpleName}")

    //conf.set("spark.serializer","org.apache.spark.serializer.KryoSerializer")
    conf.registerKryoClasses(Array(classOf[User]))

    val sc = new SparkContext(conf)

    val ssc: SQLContext = new SQLContext(sc)

    ssc.setConf("spark.sql.parquet.compression.codec","gzip")

    val line: RDD[String] = sc.textFile("D:\\testData\\advertiser\\original info")

    val user: RDD[User] = line.map(line => line.split(",",-1)).filter(_.length >= 3).map(arr => User(arr))

//    import ssc.implicits._
//    user.toDF

    val userDF: DataFrame = ssc.createDataFrame(user)

    userDF.write.partitionBy("sex","age").parquet("D:\\testDataOut\\advertiser\\withPart")

    sc.stop()

  }

}

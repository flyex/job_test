package demo

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Dataset, SparkSession}

object Test2 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName(s"${this.getClass.getSimpleName}").setMaster("local[2]")

    val sc = new SparkContext(conf)




  }

}

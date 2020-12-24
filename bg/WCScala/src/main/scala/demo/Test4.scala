package demo

import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Before
import org.junit.Test

object Test4 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("aa")
    val sc = new SparkContext(conf)

    val t1 = Seq((1,"spark"),(2,"scala"),(3,"hadoop"))
    val t2 = Seq((1,100),(2,98),(3,95))
    val t3 = Seq(1,2,3,4,5,6)
    val t4 = Seq(3,4,5,6,7,8)

    val rdd1 = sc.parallelize(t1)
    val rdd2 = sc.parallelize(t2)
    val rdd3 = sc.parallelize(t3)
    val rdd4 = sc.parallelize(t4)

    val value = rdd1.join(rdd2)
    val v2 = rdd3.union(rdd4).distinct().sortBy(t =>t)

    println(v2.collect().toBuffer)

    sc.stop()

  }





}

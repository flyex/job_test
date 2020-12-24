package com.flyex.test

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object FileReadTest {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("test2")

    val sc = new SparkContext(conf)

    //val lines: RDD[String] = sc.textFile("hdfs://hdp-01:9000/access2.log")
    //val lines: RDD[String] = sc.parallelize(Array("20090121000132095572000|125.213.100.123|show.51.com|/shoplist.php?phpfile=shoplist2.php&style=1&sex=137|Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Mozilla/4.0(Compatible Mozilla/4.0(Compatible-EmbeddedWB 14.59 http://bsalsa.com/ EmbeddedWB- 14.59  from: http://bsalsa.com/ )|http://show.51.com/main.php|"))

//    val test: RDD[String] = lines.map(line => {
//      val words = line.split("[|]")
//      words(2)
//    })



    //val rdd2 = urlAnd1.collect()

    //test.saveAsTextFile("D:\\myTempNoUse")

    val lines: RDD[((String, String), Int)] = sc.parallelize(Seq((("php","fa"),1),(("php","fa"),1),(("java","fa"),1),(("bg","fa"),1)))

    lines.map(t => t._1._1).distinct()

  }
}

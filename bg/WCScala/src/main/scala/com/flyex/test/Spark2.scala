package com.flyex.test

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object Spark2 {

  def main(args: Array[String]): Unit = {

    //val spark = SparkSession.builder().appName("aa").master("local[2]").getOrCreate()

    val conf = new SparkConf().setAppName("aa").setMaster("local")

    val sc = new SparkContext(conf)

    val data2 = sc.parallelize(Seq(1,2,3,4,5,6,7,8))

    val data = sc.textFile("D:\\testData\\sample")
    val info = sc.textFile("D:\\testData\\sample_info")

    val infoData = info.map(line => {
      val words = line.split(",", -1)
      (words(0), words(1))
    }).repartition(2)

    val infoMap = infoData.collectAsMap()

    val infoBro = sc.broadcast(infoMap)

    val dataTuple = data.map(line => {
      val words = line.split(",", -1)
      Tuple2(words(0), words(1))
    })

    /*
    val value: String = dataTuple.sample(false,0.1)
              .map(t => (t._1,1))
              .reduceByKey(_+_).map(t => t.swap)
              .sortByKey(false).first()._2

    println(value.toString)
    */
    conf.set("spark.default.parallelism","5")

    val res = dataTuple.groupByKey()

    //res.saveAsTextFile("D:\\testDataOut\\sampleOut")

    println(data2.getNumPartitions)


    //TODO hashJoin
    /*
    //conf.set("spark.shuffle.manager","hash")
    //conf.set("spark.shuffle.consolidateFiles","true");
    //conf.set("spark.shuffle.sort.bypassMergeThreshold","0")

    val res = dataTuple.join(infoData,2)
    res.saveAsTextFile("D:\\testDataOut\\sampleOut")
    */

    //TODO broadCast join
    /*
    val res: RDD[(String, Option[String])] = dataTuple.map(tuple => {
      val value = infoBro.value
      if (value.contains(tuple._1)) {
        (tuple._1, value.get(tuple._1))
      } else {
        (tuple._1, null)
      }
    })
    res.saveAsTextFile("D:\\testDataOut\\sampleOut")

     */




    sc.stop()




  }

}

package com.flyex.broadcast

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object IpLocation1 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("Local[2]").setAppName("akl")

    val sc = new SparkContext(conf)

    //在driver端获取全部ip规则用于广播
    val rules: Array[(Long, Long, String)] = MyUtils.readRules("D:\\ip.txt")

    val broadcastRef: Broadcast[Array[(Long, Long, String)]] = sc.broadcast(rules)

    val accessLog: RDD[String] = sc.textFile(args(1))

    val func = (line:String) =>{
      val fields = line.split("[|]")
      val ip = fields(1)
      val ipNum = MyUtils.ip2Long(ip)
      //以用driver端的ip规则广播
      val rulesExecutor: Array[(Long, Long, String)] = broadcastRef.value
      val index: Int = MyUtils.binarySearch(rulesExecutor,ipNum)

      var province = " "
      if (index != -1){
        province = rulesExecutor(index)._3
      }
      (province,1)
    }

    val pAndOne: RDD[(String, Int)] = accessLog.map(func)

    val reduced: RDD[(String, Int)] = pAndOne.reduceByKey(_+_)

    val result: Array[(String, Int)] = reduced.collect()

    println(result.toBuffer)

    sc.stop()

  }

}

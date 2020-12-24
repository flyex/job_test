package com.flyex.broadcast

import java.sql.DriverManager

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object IpLocation2 {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("asd")
    val sc = new SparkContext(conf)

    val rules = sc.textFile("")

    val rulesRDD: RDD[(Long, Long, String)] = rules.map(line => {
      val fields = line.split("[|]")
      val startNum = fields(1).toLong
      val endNum = fields(2).toLong
      val province = fields(6)
      (startNum, endNum, province)
    })
    val rulesDriver: Array[(Long, Long, String)] = rulesRDD.collect()

    val rulesRef: Broadcast[Array[(Long, Long, String)]] = sc.broadcast(rulesDriver)


    val accessRdd = sc.textFile("")

    val pAndOne: RDD[(String, Int)] = accessRdd.map(log => {
      val fields = log.split("[|]")
      val ip = fields(1)
      val ipNum = MyUtils.ip2Long(ip)
      val rulesExecutor = rulesRef.value
      var province = " "

      val index = MyUtils.binarySearch(rulesExecutor, ipNum)

      if (index != -1) {
        province = rulesExecutor(index)._3
      }
      (province, 1)
    })
    val reduced: RDD[(String, Int)] = pAndOne.reduceByKey(_+_)

//    reduced.foreach(tp =>{
//      val conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bigdata?charatorEncoding=utf-8","root","2113qwe")
//
//      val pstm = conn.prepareStatement("inseret into as values (?,?)")
//
//      pstm.setString(1,"sa")
//      pstm.setInt(2,12)
//      pstm.executeUpdate()
//      pstm.close()
//      conn.close()
//    })

//    reduced.foreachPartition(tp =>{
//      val conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bigdata?characterEncoding=utf-8","root","2114qwe")
//
//      val pstm = conn.prepareStatement("insert into as values (?,?)")
//
//      tp.foreach(tp2=>{
//        pstm.setString(1,tp2._1)
//        pstm.setInt(2,tp2._2)
//        pstm.executeUpdate()
//      })
//
//      pstm.close()
//      conn.close()
//    })


    reduced.foreachPartition(it =>MyUtils.data2Mysql(it))

    sc.stop()

  }

}

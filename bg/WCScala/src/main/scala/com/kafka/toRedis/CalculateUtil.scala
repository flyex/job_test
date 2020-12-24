package com.kafka.toRedis

import com.flyex.broadcast.MyUtils
import com.redis.Test
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import redis.clients.jedis.Jedis

object CalculateUtil {

  def calculateIncom(fields : RDD[Array[String]]) = {
    val priceRDD = fields.map(arr => {
      val price = arr(4).toDouble
      price
    })
    val sum: Double = priceRDD.reduce(_+_)
    //获取redis连接
    val conn: Jedis = Test.getConnection()

    conn.incrByFloat("amount",sum)
    conn.close()
  }

  def calculateItem(fields : RDD[Array[String]]) = {
    val itemAndPrice: RDD[(String, Double)] = fields.map(arr => {
      //分类
      val item = arr(2)
      //金额
      val parice = arr(4).toDouble
      (item, parice)
    })

    val reduced: RDD[(String, Double)] = itemAndPrice.reduceByKey(_+_)
    reduced.foreachPartition(part =>{
      val conn: Jedis = Test.getConnection()
      part.foreach(t =>{
        conn.incrByFloat(t._1,t._2)
      })
      //将当前分区里的数据更新完毕后关闭连接
      conn.close()
    })
  }

  def calculateZone(fields:RDD[Array[String]],broadcast: Broadcast[Array[(Long,Long,String)]]) = {
    val provinceAndPrice: RDD[(String, Double)] = fields.map(arr => {
      val ip = arr(1)
      val price = arr(4).toDouble
      val ipNum = MyUtils.ip2Long(ip)

      val allIpRules: Array[(Long, Long, String)] = broadcast.value
      val index = MyUtils.binarySearch(allIpRules, ipNum)
      var province = "null"
      if (index != -1) {
        province = allIpRules(index)._3
      }
      (province, price)
    })

    val reduced: RDD[(String, Double)] = provinceAndPrice.reduceByKey(_+_)

    reduced.foreachPartition(part =>{
      val conn = Test.getConnection()
      part.foreach(t =>{
        conn.incrByFloat(t._1,t._2)
      })
      conn.close()
    })
  }

}

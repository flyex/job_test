package com.kafka.toRedis

import org.apache.commons.codec.language.bm.Lang
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.StreamingContext

object IPUtils {

  def broadcastIpRules(ssc: StreamingContext,ipRulesPath: String) : Broadcast[Array[(Long,Long,String)]] = {
    val sc = ssc.sparkContext

    val rulesRDD: RDD[String] = sc.textFile(ipRulesPath)
    val ipRuleRDD: RDD[(Long, Long, String)] = rulesRDD.map(line => {
      val fields = line.split("[|]")
      val startNum = fields(2).toLong
      val endNum = fields(3).toLong
      val province = fields(6)
      (startNum, endNum, province)
    })
    val rulesDriver: Array[(Long, Long, String)] = ipRuleRDD.collect()

    sc.broadcast(rulesDriver)
  }

}

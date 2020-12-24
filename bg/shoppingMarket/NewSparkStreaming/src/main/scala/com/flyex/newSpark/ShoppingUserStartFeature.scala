package com.flyex.newSpark

import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}
import com.flyex.newSpark.test.TimeTransform
import com.flyex.newSpark.utils.{JedisUtils, TimeUtils}
import com.typesafe.config.ConfigFactory
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, HasOffsetRanges, KafkaUtils, LocationStrategies}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import scalikejdbc.{DB, SQL}
import scalikejdbc.config.DBs

object ShoppingUserStartFeature {

  Logger.getLogger("org.apache").setLevel(Level.WARN)

  def main(args: Array[String]): Unit = {

    val load = ConfigFactory.load()

    val group = load.getString("kafka.group.id")

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> load.getString("kafka.broker.list"),
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> group,
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topic = load.getString("kafka.topics")
    val topics = Array(topic)

    DBs.setup()
    val fromOffsets: Map[TopicPartition, Long] = DB.readOnly { implicit session =>
      SQL("select * from forSpark where groupid=?").bind(load.getString("kafka.group.id")).map(rs => {
        (new TopicPartition(rs.string("topic"), rs.int("partition")), rs.long("offset"))
      }).list().apply()
    }.toMap

    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("streaming-userWC")

    val ssc = new StreamingContext(sparkConf, Seconds(2))


    //程序第一次启动
    val stream = if (fromOffsets.size == 0) {
      KafkaUtils.createDirectStream[String, String](
        ssc,
        //位置策略
        LocationStrategies.PreferConsistent,
        //订阅策略
        ConsumerStrategies.Subscribe[String, String](topics, kafkaParams)
      )
    } else {
      //判断kafka中主题的最大偏移量是否在mysql偏移量的范围内
      //new AdminClient()
      //用来保存kafka中的偏移量
      //var checkedOffset = Map[TopicPartition,Long]
      KafkaUtils.createDirectStream[String, String](
        ssc,
        LocationStrategies.PreferConsistent,
        ConsumerStrategies.Assign[String, String](fromOffsets.keys, kafkaParams, fromOffsets)
      )
    }

    stream.foreachRDD(rdd => {

      //获取到这次rdd范围偏移量
      val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

      //rdd.foreach(println)
      //TODO 实际业务处理
      val jsonAll: RDD[JSONObject] = rdd.map(line => {
        val line2 = line.toString.split("AppMain - ")(1).split("\\|")(1)
        val jsonStr = line2.substring(0, line2.length - 1)
        JSON.parseObject(jsonStr)
      }).filter(_.getJSONArray("et").size() > 0)

      //      jsonAll.foreach(js =>{
      //        println(js.toJSONString)
      //      })

      //start启动日志实时处理
      val startLog = jsonAll.filter(_.toJSONString.contains("start")).map(js => {
        //获取start json串
        val startJson = js.getJSONArray("et").getJSONObject(0)

        val et = startJson.getString("ett")

        val en = startJson.getString("en")

        val kv = startJson.getJSONObject("kv")
        val entry = kv.getString("entry")
        val action = kv.getString("action")
        val loadTime = kv.getString("loading_time")

        (et, en, entry, action, loadTime)
      })
      //按小时统计总点击登录、成功登录、失败登录
      startLog.map(log => {
        val key = "ul" + TimeUtils.time2String(log._1)
        if (log._4 == "1") {
          (key, List(1, 1, 0))
        } else {
          (key, List(1, 0, 1))
        }
      }).reduceByKey((list1, list2) => {
        list1 zip list2 map (x => x._1 + x._2)
      }).foreachPartition(itr => {
        val jedis = JedisUtils.getJedisClient()

        itr.foreach(itr => {
          jedis.hincrBy(itr._1, "total", itr._2(0))
          jedis.hincrBy(itr._1, "succ", itr._2(1))
          jedis.hincrBy(itr._1, "fail", itr._2(2))

          jedis.expire(itr._1, 60 * 60 * 24 * 2)
        })

      })


      //将这次处理的偏移量保存到mysql中
      //      offsetRanges.foreach(osr => {
      //        DB.autoCommit { implicit session =>
      //          SQL("replace into forSpark values (?,?,?,?)")
      //            .bind(osr.topic, osr.partition, load.getString("kafka.group.id"), osr.untilOffset)
      //            .update().apply()
      //          println(osr.fromOffset + "    " + osr.untilOffset)
      //        }
      //      })



    })

    ssc.start()
    ssc.awaitTermination()

  }

}

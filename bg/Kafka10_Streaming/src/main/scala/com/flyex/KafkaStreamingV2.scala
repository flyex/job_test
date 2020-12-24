package com.flyex

import java.lang

import com.alibaba.fastjson.{JSON, JSONObject}
import kafka.common.TopicAndPartition
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}

object KafkaStreamingV2 {
  def main(args: Array[String]): Unit = {

    //设置SparkConf和StreamingContext
    val conf = new SparkConf().setMaster("local[2]").setAppName("spark-streaming-bike")
    val streamingContext = new StreamingContext(conf, Seconds(5))

    val sc = new SparkContext(conf)
    //sc.makeRDD()

    val group = "fly1"

    val topic = "review_1"

    //配置kafka_11的参数
    val kafkaParams: Map[String, Object] = Map[String, Object](
      "bootstrap.servers" -> "es-01:9092,es-02:9092,es-03:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> group,
      "auto.offset.reset" -> "earliest", // lastest
      "enable.auto.commit" -> (false: lang.Boolean)
    )

    val topics: Array[String] = Array(topic)

    //11版的kafka流不同于08,09,10
    val stream: InputDStream[ConsumerRecord[String, String]] =
      KafkaUtils.createDirectStream[String, String](
        streamingContext,
        LocationStrategies.PreferConsistent,
        Subscribe[String, String](topics, kafkaParams)
      )

    var offsetRanges = Array[OffsetRange]()

    stream.foreachRDD { rdd =>
      if (!rdd.isEmpty()) {


        offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

        rdd.foreach(line =>
          println(line.key() + " " + line.value())
        )

        //          val lineObject = line.value()
        //          val json_bike: JSONObject = JSON.parseObject(lineObject)
        //          println("id:" + json_bike.getLong("id") + ",log: "+
        //            json_bike.getDouble("longitude"))
      }

      //更新偏移量
      stream.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)

    }

    streamingContext.start()
    streamingContext.awaitTermination()

  }

}

package com.kafka

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Milliseconds, StreamingContext}

object KafkaWordCount {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("sa")
    val ssc = new StreamingContext(conf,Milliseconds(5000))
    //指定zk地址
    val zkQuorum = "hdp-02:2181,hdp-03:2181,hdp-04:2181"
    //指定群组
    val groupId = "flyex"
    //指定主题和主题对应的执行线程
    val topic = Map[String,Int]("flyex" -> 1)
    //使用高级封装api创建DStream,操作简单，效率低（执行慢）
    val data: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc,zkQuorum,groupId,topic)
    val lines: DStream[String] = data.map(_._2)
    val words: DStream[String] = lines.flatMap(_.split(" "))
    val wAo: DStream[(String, Int)] = words.map((_,1))
    val reduced: DStream[(String, Int)] = wAo.reduceByKey(_+_)

    reduced.print()

    ssc.start()

    ssc.awaitTermination()
  }

}

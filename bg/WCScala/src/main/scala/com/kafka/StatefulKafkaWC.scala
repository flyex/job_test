package com.kafka

import org.apache.spark.{HashPartitioner, SparkConf}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StatefulKafkaWC {

  val updateFunc = (iter: Iterator[(String, Seq[Int], Option[Int])]) => {
    //iter.map(t =>(t._1 ,t._2.sum + t._3.getOrElse(0)))
    iter.map { case (x, y, z) => (x, y.sum + z.getOrElse(0)) }
  }

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("a").setMaster("local[2]")
    val ssc = new StreamingContext(conf, Seconds(5))
    ssc.checkpoint("D:\\tmp\\kafka")

    val zkQuorum = "hdp-02:2181,hdp-03:2181,hdp-04:2181"
    val groupId = "flyex"
    val topic = Map[String, Int]("flyex_vip" -> 3)

    val data: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc, zkQuorum, groupId, topic)
    val lines: DStream[String] = data.map(_._2)
    val words = lines.flatMap(_.split(" "))
    val wAo = words.map((_, 1))
    val reduced: DStream[(String, Int)] = wAo.updateStateByKey(updateFunc, new HashPartitioner(ssc.sparkContext.defaultParallelism), true)

    reduced.print()

    ssc.start()
    ssc.awaitTermination()
  }

}

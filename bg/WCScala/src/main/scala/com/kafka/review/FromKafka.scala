package com.kafka.review

import java.util.Properties

import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Milliseconds, StreamingContext}
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object FromKafka {

  def main(args: Array[String]): Unit = {

    val updateFunc = (iter: Iterator[(String,Seq[Int], Option[Int])]) =>{
      iter.map(t =>(t._1 ,t._2.sum + t._3.getOrElse(0)))
    }

    val conf = new SparkConf().setMaster("local[2]").setAppName("as")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc,Milliseconds(5000))

    sc.setCheckpointDir("D:\\testchp_Spark_kafka\\wc")

    val groupId = "fly"
    val zkQuorum = "hdp-02:2181,hdp-03:2181,hdp-04:2181"
    val topic = Map[String,Int]("review_1" -> 1)

    val kafkaData: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc,zkQuorum,groupId,topic)

    val data = kafkaData.map(_._2)

    val wordAndOne = data.flatMap(_.split(" ")).map((_,1))

    val res: DStream[(String, Int)] = wordAndOne.updateStateByKey(updateFunc,new HashPartitioner(ssc.sparkContext.defaultParallelism),true)

    res.print()




    ssc.start()
    ssc.awaitTermination()


  }

}

package com.kafka.review

import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Milliseconds, StreamingContext}
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object HATestFromKafka {

  private val ckp = "D:\\testchp_Spark_kafka\\Driver_ckp"

  def createSCC(): StreamingContext = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("as")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc,Milliseconds(5000))

    //供updateFunction使用
    //sc.setCheckpointDir("D:\\testchp_Spark_kafka\\wc")
    val updateFunc = (iter: Iterator[(String,Seq[Int], Option[Int])]) =>{
      iter.map(t =>(t._1 ,t._2.sum + t._3.getOrElse(0)))
    }

    val groupId = "fly"
    val zkQuorum = "hdp-02:2181,hdp-03:2181,hdp-04:2181"
    val topic = Map[String,Int]("ckp" -> 1)

    val kafkaData: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc,zkQuorum,groupId,topic)

    val data = kafkaData.map(_._2)

    val wordAndOne: DStream[(String, Int)] = data.flatMap(_.split(" ")).map((_,1))
    val res: DStream[(String, Int)] = wordAndOne.updateStateByKey(updateFunc,new HashPartitioner(ssc.sparkContext.defaultParallelism),true)
    res.print()

    ssc.checkpoint(ckp)
    ssc
  }
  def main(args: Array[String]): Unit = {

    val ssc: StreamingContext = StreamingContext.getOrCreate(ckp,createSCC)

    ssc.start()
    ssc.awaitTermination()


  }

}

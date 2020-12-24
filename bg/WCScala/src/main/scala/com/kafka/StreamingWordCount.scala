package com.kafka

import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Milliseconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object StreamingWordCount {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("a").setMaster("local[2]")
    val sc = new SparkContext(conf);
    val scc = new StreamingContext(sc,Milliseconds(5000))

    //创建接受socket连接
    val lines: ReceiverInputDStream[String] = scc.socketTextStream("hdp-04",8888)
    val words: DStream[String] = lines.flatMap(_.split(" "))
    val wordsAndOne: DStream[(String, Int)] = words.map((_,1))
    val result: DStream[(String, Int)] = wordsAndOne.reduceByKey(_+_)

    result.print()
    scc.start()
    scc.awaitTermination()
  }

}
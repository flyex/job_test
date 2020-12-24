package com.flyex.streaming.scalaCustormSource

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time

object UseSNoParalleSource {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    val source: DataStream[Long] = env.addSource(new SNoParalleSource)

    val word: DataStream[Long] = source.map(word => {
      println("接受的数据为：" + word)
      word
    })

    val result = word.timeWindowAll(Time.seconds(2),Time.seconds(1)).sum(0)

    result.print().setParallelism(1)

    env.execute("ads")

  }

}

package com.flyex.streaming.streamApi

import java.{lang, util}

import com.flyex.streaming.scalaCustormSource.SNoParalleSource
import org.apache.flink.streaming.api.collector.selector.OutputSelector
import org.apache.flink.streaming.api.scala.{DataStream, SplitStream, StreamExecutionEnvironment}

object SApiSplit {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    val source1 = env.addSource(new SNoParalleSource)

    val splited: SplitStream[Long] = source1.split(new OutputSelector[Long] {
      override def select(value: Long): lang.Iterable[String] = {
        val outlist = new util.ArrayList[String]()
        if (value % 2 == 0) {
          outlist.add("even")
        } else {
          outlist.add("odd")
        }
        outlist
      }
    })

    val evenStream = splited.select("even")

    evenStream.print().setParallelism(1)

    env.execute("asd")

  }

}

package com.flyex.streaming.split_select

import java.{lang, util}

import com.flyex.streaming.scalaCustormSource.SNoParalleSource
import org.apache.flink.streaming.api.collector.selector.OutputSelector
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

object SplitTest {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    val s1 = env.addSource(new SNoParalleSource)
   // val s2 = env.addSource(new SNoParalleSource)

    import scala.collection.JavaConverters._
    val splited = s1.split(new OutputSelector[Long] {
      override def select(value: Long): lang.Iterable[String] = {
        val arr = new util.ArrayList[String]()
        if (value % 2 ==0){
          arr.add("even")
        }else{
          arr.add("odd")
        }
        arr
      }
    })

    val odd: DataStream[Long] = splited.select("odd")

    odd.print().setParallelism(1)

    env.execute("split")

  }


}

package com.flyex.streaming.streamApi

import com.flyex.streaming.scalaCustormSource.SNoParalleSource
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

object SAPiPartition {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(3)

    import org.apache.flink.api.scala._
    val source = env.addSource(new SNoParalleSource)

    val stuple = source.map(s => Tuple1(s))

    val result = stuple.partitionCustom(new SParatition,0)

    val finnal = result.map(rs => {
      val id = Thread.currentThread().getId
      val name = Thread.currentThread().getName
      println("当前线程id:" + id + ",线程名:" + name + rs)
      rs
    })

    finnal.print().setParallelism(1)

    env.execute("asd")

  }

}

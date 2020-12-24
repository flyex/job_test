package com.flyex.streaming.streamApi

import com.flyex.streaming.scalaCustormSource.SNoParalleSource
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

object SApiUnion {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    val source1 = env.addSource(new SNoParalleSource)
    val source2 = env.addSource(new SNoParalleSource)

    val s12: DataStream[Long] = source1.union(source2)

    s12.map(s =>{
      println("接收的数据为:"+s)
    }).print().setParallelism(1)

    env.execute("asd")
  }

}

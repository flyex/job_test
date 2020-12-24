package com.flyex.streaming.streamApi

import com.flyex.streaming.scalaCustormSource.SNoParalleSource
import org.apache.flink.streaming.api.functions.co.CoMapFunction
import org.apache.flink.streaming.api.scala.{ConnectedStreams, DataStream, StreamExecutionEnvironment}

object SApiConnect {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    val source1 = env.addSource(new SNoParalleSource)

    val source2 = env.addSource(new SNoParalleSource)
    val strS2: DataStream[String] = source2.map(s => {
      "s2" + s
    })

    val conn: ConnectedStreams[Long, String] = source1.connect(strS2)

    val result: DataStream[Any] = conn.map(s1=>{s1}, s2=>{s2})

    result.print().setParallelism(1)

    env.execute("asd")
  }

}

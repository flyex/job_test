package com.flyex.streaming

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time

/*
  滑动窗口计算
  每隔一秒统计最近两秒数据
 */
object SocketStreamingWinCount {

  def main(args: Array[String]): Unit = {

    val port: Int = try{
      ParameterTool.fromArgs(args).getInt("port")
    }catch{
      case e:Exception =>{
        System.err.println("no port set,Use default 9000 port")
      }
        9000
    }

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    val lines: DataStream[String] = env.socketTextStream("hdp-01",port,'\n')

    import org.apache.flink.api.scala._

    val result: DataStream[(String, Int)] = lines.flatMap(_.split(" "))
      .map((_, 1)).keyBy(_._1)
      .timeWindow(Time.seconds(2), Time.seconds(1))
      .sum(1)

    result.print().setParallelism(1)

    env.execute("Socket windows count")


  }

}

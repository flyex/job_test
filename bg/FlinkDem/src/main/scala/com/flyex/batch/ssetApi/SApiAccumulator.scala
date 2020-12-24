package com.flyex.batch.ssetApi

import java.util

import org.apache.flink.api.common.accumulators.IntCounter
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration

object SApiAccumulator {

  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    val data = env.fromElements(1,2,3,4)

    val result = data.map(new RichMapFunction[Int, Int] {

      private val openSum: IntCounter = new IntCounter()
      private val mapSum: IntCounter = new IntCounter

      override def open(parameters: Configuration): Unit = {
        super.open(parameters)
        getRuntimeContext.addAccumulator("openSum", openSum)
        getRuntimeContext.addAccumulator("mapSum", mapSum)
        openSum.add(1)
      }

      override def map(value: Int): Int = {
        mapSum.add(1)
        value
      }
    }).setParallelism(1)

    result.writeAsText("D:\\testDataOut\\flinkOut\\out")

    val jobResult = env.execute("asd")

    val accMap: util.Map[String, AnyRef] = jobResult.getAllAccumulatorResults

    val keyset: util.Set[String] = accMap.keySet()

    val itr: util.Iterator[String] = keyset.iterator()

    while (itr.hasNext){
      val key = itr.next()
      println("类型:"+key+",次数:"+accMap.get(key))
    }

  }

}

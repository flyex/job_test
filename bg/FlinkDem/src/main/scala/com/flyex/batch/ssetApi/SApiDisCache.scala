package com.flyex.batch.ssetApi

import java.io.File
import java.util

import org.apache.commons.io.FileUtils
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration

object SApiDisCache {

  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment

    env.registerCachedFile("D:\\testDataOut\\flinkOut\\out","out")

    import org.apache.flink.api.scala._
    val data = env.fromElements(1,2,3,4)

    val result = data.map(new RichMapFunction[Int, Int] {
      override def open(parameters: Configuration): Unit = {
        super.open(parameters)
        val file: File = getRuntimeContext.getDistributedCache.getFile("out")
        val fs: util.List[String] = FileUtils.readLines(file)
        val isGo = fs.iterator()
        while (isGo.hasNext) {
          print(isGo.next())
        }

      }

      override def map(value: Int): Int = value
    })

    result.print()

  }

}

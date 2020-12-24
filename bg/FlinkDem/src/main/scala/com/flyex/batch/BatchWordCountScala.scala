package com.flyex.batch

import org.apache.flink.api.scala.ExecutionEnvironment

object BatchWordCountScala {

  def main(args: Array[String]): Unit = {

    val inputPath = "D:\\testData"
    val outPutPath = "D:\\testDataOut\\wcOut\\"

    val env = ExecutionEnvironment.getExecutionEnvironment
    val lines = env.readTextFile(inputPath)

    import org.apache.flink.api.scala._
    val result: AggregateDataSet[(String, Int)] = lines.flatMap(_.toLowerCase.split(" "))
      .filter(_.nonEmpty)
      .map((_, 1))
      .groupBy(0)
      .sum(1)

    result.writeAsCsv(outPutPath,"\n"," ").setParallelism(1)

    env.execute("batch word count")



  }

}

package com.flyex.streaming.scalaCustormSource

import org.apache.flink.streaming.api.functions.source.{ParallelSourceFunction, SourceFunction}

class SParalleSource extends ParallelSourceFunction[Long] {

  var isRunning = true
  var i = 0
  override def run(ctx: SourceFunction.SourceContext[Long]): Unit = {
    while (isRunning){
      ctx.collect(i)
      i += 1
      Thread.sleep(1000)
    }
  }

  override def cancel(): Unit = {
    isRunning = false
  }
}

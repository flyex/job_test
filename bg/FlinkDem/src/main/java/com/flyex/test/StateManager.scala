package com.flyex.test

import org.apache.flink.api.common.functions.RichFlatMapFunction
import org.apache.flink.util.Collector

class StateManager extends RichFlatMapFunction {



  override def flatMap(value: Nothing, out: Collector[Nothing]): Unit = ???
}
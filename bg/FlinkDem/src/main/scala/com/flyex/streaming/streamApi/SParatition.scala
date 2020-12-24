package com.flyex.streaming.streamApi

import org.apache.flink.api.common.functions.Partitioner

class SParatition extends Partitioner[Long]{

  override def partition(key: Long, numPartitions: Int): Int = {
    print("分区数为："+numPartitions)
    val lid = key%numPartitions
    return lid.intValue()
  }
}

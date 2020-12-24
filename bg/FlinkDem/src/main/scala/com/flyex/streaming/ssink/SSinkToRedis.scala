package com.flyex.streaming.ssink

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.redis.RedisSink
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig
import org.apache.flink.streaming.connectors.redis.common.mapper.{RedisCommand, RedisCommandDescription, RedisDataType, RedisMapper}

object SSinkToRedis {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val source: DataStream[String] = env.socketTextStream("hdp-01",4000,'\n')

    import org.apache.flink.api.scala._
    val mapped = source.map(("l_words_scala",_))

    val conf = new FlinkJedisPoolConfig.Builder().setHost("hdp-04").setPort(6379).build()

    val redisSink = new RedisSink(conf,new SRedisMapper)

    mapped.addSink(redisSink)

    env.execute("asd")

  }

  class SRedisMapper extends RedisMapper[Tuple2[String,String]]{
    override def getKeyFromData(data: (String, String)): String = data._1

    override def getValueFromData(data: (String, String)): String = data._2

    override def getCommandDescription: RedisCommandDescription = new RedisCommandDescription(RedisCommand.LPUSH)
  }
}

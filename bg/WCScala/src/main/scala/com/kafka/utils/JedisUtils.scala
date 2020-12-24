package com.kafka.utils

import org.apache.commons.pool2.impl.GenericObjectPoolConfig
import redis.clients.jedis.JedisPool


object JedisUtils {

  private val jedisPool = new JedisPool(new GenericObjectPoolConfig,"hdp-02",6379)


  def getJedisClient = {

      jedisPool.getResource

  }

}

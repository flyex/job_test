package com.flyex.newSpark.utils

import org.apache.commons.pool2.impl.GenericObjectPoolConfig
import redis.clients.jedis.{Jedis, JedisPool}

object JedisUtils {

  private val jedisPool = new JedisPool(new GenericObjectPoolConfig(), "hdp-04")

  def getJedisClient(): Jedis = {
    jedisPool.getResource
  }

}

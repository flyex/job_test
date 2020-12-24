package com.redis

import org.apache.commons.pool2.impl.GenericObjectPoolConfig
import redis.clients.jedis.{Jedis, JedisPool, JedisPoolConfig}

object Test2 {

  def main(args: Array[String]): Unit = {

    new JedisPool(new GenericObjectPoolConfig,"aa",1);

  }

}

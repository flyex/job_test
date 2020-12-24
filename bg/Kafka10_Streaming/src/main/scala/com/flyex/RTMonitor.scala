package com.flyex

import com.typesafe.config.{Config, ConfigFactory}
import kafka.common.TopicAndPartition
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import scalikejdbc.DB
import scalikejdbc.config.DBs
import scalikejdbc._


object RTMonitor {

  def main(args: Array[String]): Unit = {

    val load: Config = ConfigFactory.load()

    val conf = new SparkConf()
    conf.setMaster("Local[2]").setAppName(s"${this.getClass.getSimpleName}")

    val ssc: StreamingContext = new StreamingContext(conf,Seconds(2))

    DBs.setup()

    DB.readOnly{ implicit session =>
      sql"select * from b where id = ?".bind(1)
        .map(t => {
          (1,2)
        }).toList().apply()
    }.toMap




  }



}

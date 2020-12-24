package com.flyex.newSpark

import com.typesafe.config.ConfigFactory
import org.apache.kafka.common.TopicPartition
import scalikejdbc.{DB, SQL}
import scalikejdbc.config.DBs

object TestDBs {

  def main(args: Array[String]): Unit = {

    val load = ConfigFactory.load()

    DBs.setup()
    val fromOffsets: Map[TopicPartition, Long] = DB.readOnly { implicit session =>
      SQL("select * from forSpark where groupid=?").bind(load.getString("kafka.group.id")).map(rs => {
        (new TopicPartition(rs.string("topic"), rs.int("partition")), rs.long("offset"))
      }).list().apply()
    }.toMap

    println(fromOffsets.size)
  }

}

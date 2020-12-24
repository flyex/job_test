package com.flyex.newSpark

import java.util.Properties

import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.producer.{KafkaProducer, Producer, ProducerRecord}

object KafkaProducer {

  def main(args: Array[String]): Unit = {

    val load = ConfigFactory.load()

    val props = new Properties()
    props.put("bootstrap.servers",load.getString("kafka.broker.list"))
    props.put("acks","1")
    props.put("retries","1")
    props.put("buffer.memory"," 33554432")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer: Producer[String,String] = new KafkaProducer(props)

    producer.send(new ProducerRecord[String,String]("test",0,"axx","14:39:43.358 [main] INFO  com.flyex.AppMain - 1607323183358|{\"cm\":{\"ln\":\"-113.4\",\"sv\":\"V2.3.0\",\"os\":\"8.1.7\",\"g\":\"7BZE66H5@gmail.com\",\"mid\":\"m305\",\"nw\":\"WIFI\",\"l\":\"pt\",\"vc\":\"0\",\"hw\":\"1080*1920\",\"ar\":\"MX\",\"uid\":\"u643\",\"t\":\"1607240453416\",\"la\":\"11.4\",\"md\":\"sumsung-19\",\"vn\":\"1.3.4\",\"ba\":\"Sumsung\",\"sr\":\"Z\"},\"ap\":\"com.flyex.AppMain\",\"et\":[{\"ett\":\"1607273557535\",\"en\":\"start\",\"kv\":{\"entry\":\"2\",\"loading_time\":\"12\",\"action\":\"1\",\"open_ad_type\":\"2\",\"detail\":\"\"}}]}        2020-12-07"))

    producer.close()
  }

}

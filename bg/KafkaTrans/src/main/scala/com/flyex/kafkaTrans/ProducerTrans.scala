package com.flyex.kafkaTrans

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

object ProducerTrans {

  def main(args: Array[String]): Unit = {

    val props = new Properties()

    props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true")
    props.put("acks", "all"); // 当 enable.idempotence 为 true，这里默认为 all
    props.put("bootstrap.servers", "hdp-02:9092,hdp-03:9092,hdp-04:9092")

    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer(props);

    //producer.send(new ProducerRecord[String,String]())

  }

}

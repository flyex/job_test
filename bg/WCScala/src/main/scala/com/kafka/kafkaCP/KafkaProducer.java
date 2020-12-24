package com.kafka.kafkaCP;

import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducer {

    public static void main(String[] args) {

        Properties props = new Properties();

        props.put("enable.idempotence",true);
        props.put("acks", "all"); // 当 enable.idempotence 为 true，这里默认为 all
        props.put("bootstrap.servers", "hdp-01:9092,hdp-02:9092,hdp-03:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        props.put("retries", 3);
        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);
        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33550);

        org.apache.kafka.clients.producer.KafkaProducer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);

        for (int i=0;i<10;i++){
            producer.send(new ProducerRecord<String, String>("",""));
        }


    }
}

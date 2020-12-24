package com.flyex.hbasePro.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.*;

public class LogConsumer {

    public static void main(String[] args) throws Exception {

        Properties props = new Properties();

        props.put("bootstrap.servers", "hdp-01:9092,hdp-02:9092,hdp-03:9092");
        props.put("group.id", "test2");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "3000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("test0_9_01"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(500);

            if (records.isEmpty()){
                continue;
            }
            for (ConsumerRecord<String,String> record1 : records) {
                System.out.printf(" 消费消息：topic=%s, partition=%d, offset=%d, key=%s, value=%s\n",
                        record1.topic(), record1.partition(), record1.offset(), record1.key(), record1.value());
            }
        }
    }
}

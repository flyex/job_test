package com.producerAndConsumer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

public class ProdeuerDemo {
    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("metadata.broker.list","hdp-01:9092,hdp-02:9092,hdp-03:9092");
        prop.put("serializer.class","kafka.serializer.StringEncoder");
        ProducerConfig config = new ProducerConfig(prop);
        Producer<String, String> producer = new Producer<>(config);
        for (int i = 0; i<=1000 ; i++){
            producer.send(new KeyedMessage<String,String>("flyex","message"+i));
        }
    }
}

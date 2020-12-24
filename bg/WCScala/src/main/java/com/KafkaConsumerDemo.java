package com;


import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaConsumerDemo {

    public static Logger logger = LoggerFactory.getLogger(KafkaConsumerDemo.class);
    public static void main(String[] args) {

        Properties pop = new Properties();
        pop.setProperty("","");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(pop);



    }
}

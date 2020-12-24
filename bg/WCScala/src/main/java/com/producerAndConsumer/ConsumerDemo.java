package com.producerAndConsumer;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ConsumerDemo {
    private static final String topic = "flyex";
    private static final Integer threads = 2;

    public static void main(String[] args) {
        Properties prop = new Properties();

        prop.put("zookeeper.connect","hdp-02:2181,hdp-03:2181,hdp-04:2181");
        prop.put("group.id","test1");
        prop.put("auto.offset.reset","smallest");

        ConsumerConfig config = new ConsumerConfig(prop);
        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(config);
        HashMap<String, Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(topic,threads);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

        for (final KafkaStream<byte[],byte[]> kafkaStream : streams){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (MessageAndMetadata<byte[],byte[]> mm : kafkaStream){
                        String msg = new String(mm.message());
                        System.out.println(msg);
                    }
                }
            }).start();
        }

    }
}

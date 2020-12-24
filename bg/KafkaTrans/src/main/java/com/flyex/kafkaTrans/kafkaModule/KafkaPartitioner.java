package com.flyex.kafkaTrans.kafkaModule;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class KafkaPartitioner implements Partitioner {

    public static int PARTITION_NUM = 6;

    @Override
    public void configure(Map<String, ?> configs) {

    }

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        return null==key? 0 : (int)( Long.valueOf(key.toString())%PARTITION_NUM );
    }

    @Override
    public void close() {

    }
}

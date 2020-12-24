package com.flyex.kafkaTrans;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class KafkaPro {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        props.put("acks", "all"); // 当 enable.idempotence 为 true，这里默认为 all
        props.put("bootstrap.servers", "hdp-02:9092,hdp-03:9092,hdp-04:9092");

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer producer = new KafkaProducer(props);

        for (int i = 0; i < 5; i++) {
            producer.send(new ProducerRecord("proTrans", i + "0", "test" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {

                }
            });
        }

        producer.close();

    }
}

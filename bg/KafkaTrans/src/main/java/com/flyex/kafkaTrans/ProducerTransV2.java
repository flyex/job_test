package com.flyex.kafkaTrans;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.ProducerFencedException;

import java.util.Properties;

public class ProducerTransV2 {

    public static void main(String[] args) {

        Properties props = new Properties();

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        props.put("client.id", "ProducerTranscationnalExample");

        props.put("bootstrap.servers", "hdp-02:9092,hdp-03:9092,hdp-04:9092");

        props.put("transactional.id", "test-transactional");

        props.put("acks", "all");

        KafkaProducer producer = new KafkaProducer(props);
        producer.initTransactions();

        producer.beginTransaction();

        try {
            for (int i=0;i<5;i++){
                producer.send(new ProducerRecord("proTrans",i+"0","haHa"+i));
            }

            Thread.sleep(100000);

            producer.commitTransaction();
        } catch (ProducerFencedException e) {
            e.printStackTrace();
            producer.close();
        } catch (KafkaException e2) {
            e2.printStackTrace();
            producer.abortTransaction();
        } catch (InterruptedException e3){
            e3.printStackTrace();
            producer.abortTransaction();
        }

        producer.close();

    }
}

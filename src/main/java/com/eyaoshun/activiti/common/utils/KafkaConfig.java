package com.eyaoshun.activiti.common.utils;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author zmm
 * @date 2020/1/17
 */

public class KafkaConfig {


    public static void main(String[] args) throws ExecutionException, InterruptedException{

//        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(getProperits());
//
//
//        ProducerRecord<String, String> record = new ProducerRecord<String, String>("first", "小脑袋多多多多多");
//        RecordMetadata recordMetadata = kafkaProducer.send(record).get();





    }
    public     static Properties  getProperits() throws ExecutionException, InterruptedException {

        Properties p = new Properties();
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "120.27.147.104:9092");//kafka地址，多个地址用逗号分割
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);


      return  p;

    }
}

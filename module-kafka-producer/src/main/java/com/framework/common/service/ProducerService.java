package com.framework.common.service;

import com.framework.common.config.ProducerConfiguration;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.TimeZone;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {

    private final ProducerConfiguration config;
    private KafkaProducer<String, String> producer;

    @PostConstruct
    public void build() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBootstrapServers());
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, config.getProducer().getKeySerializer());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, config.getProducer().getValueSerializer());
        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, config.getProducer().getPartitionerClass());
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, config.getProducer().getBatchSize());
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, config.getProducer().getLingerMs());
        producer = new KafkaProducer<>(properties);
    }

    public void send(String message) {
        //TODO topic명은 enum에서 불러오자
        ProducerRecord<String, String> record = new ProducerRecord<>("", message);

        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(log.isInfoEnabled()) {
                    log.info("* Producer info");
                    log.info("* Producer timestamp : {}", LocalDateTime.ofInstant(Instant.ofEpochMilli(recordMetadata.timestamp()), TimeZone.getDefault().toZoneId()));
                    log.info("* Producer partition : {}", recordMetadata.partition());
                    log.info("* Producer message : {}", message);
                }
                if(e != null) {
                    log.error("producer send exception :: {}", e.getMessage());
                }
            }
        });
    }
}

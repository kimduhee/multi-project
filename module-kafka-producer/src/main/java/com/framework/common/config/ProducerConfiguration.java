package com.framework.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/application-kafka-producer-${spring.profiles.active}.yml")
@ConfigurationProperties("spring.kafka")
@Getter
@Setter
public class ProducerConfiguration {
    private String bootstrapServers;
    private Producer producer;

    @Getter
    @Setter
    public static class Producer {
        private String keySerializer;
        private String valueSerializer;
        private String batchSize;
        private String partitionerClass;
        private String lingerMs;
    }
}

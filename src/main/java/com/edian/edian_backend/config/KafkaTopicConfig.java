package com.edian.edian_backend.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // Define topics here
    @Value("${kafka.topic.account}")
    private String accountTopicName;

    @Value("${kafka.topic.contract}")
    private String contractTopicName;

    @Value("${kafka.topic.namedinsured}")
    private String namedInsuredTopicName;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic accountTopic() {
        return new NewTopic(accountTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic contractTopic() {
        return new NewTopic(contractTopicName, 1, (short) 1);
    }

    @Bean
    public NewTopic namedInsuredTopic() {
        return new NewTopic(namedInsuredTopicName, 1, (short) 1);
    }
}

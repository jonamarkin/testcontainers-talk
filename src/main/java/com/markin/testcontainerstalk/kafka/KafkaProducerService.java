package com.markin.testcontainerstalk.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    // Define the topic name as a constant for consistency.
    private static final String TOPIC = "my-test-topic";

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends a string message to the predefined Kafka topic.
     * @param message The message string to send.
     */
    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);

        log.info("#### Producing message -> {} to topic {}", message, TOPIC);
    }
}

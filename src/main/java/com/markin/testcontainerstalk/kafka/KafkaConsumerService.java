package com.markin.testcontainerstalk.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Service
public class KafkaConsumerService {

    // A static BlockingQueue to capture received messages for test assertions.
    // This allows tests to read messages that the consumer has processed.
    public static BlockingQueue<String> messages = new LinkedBlockingQueue<>();

    /**
     * Kafka listener method that consumes messages from 'my-test-topic'.
     * The 'groupId' identifies the consumer group.
     * @param message The consumed message string.
     */
    @KafkaListener(topics = "my-test-topic", groupId = "my-group-id")
    public void consume(String message) {
        log.info("#### Consumed message -> {}", message);
        messages.offer(message); // Add the consumed message to the queue
    }
}

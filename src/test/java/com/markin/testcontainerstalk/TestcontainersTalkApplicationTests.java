package com.markin.testcontainerstalk;

import com.markin.testcontainerstalk.kafka.KafkaConsumerService;
import com.markin.testcontainerstalk.kafka.KafkaProducerService;
import com.markin.testcontainerstalk.model.Product;
import com.markin.testcontainerstalk.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.Duration;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TestcontainersTalkApplicationTests {

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Test
    void contextLoads() {
    }

    /**
     * Cleans up data before each test method to ensure test isolation.
     * For PostgreSQL: deletes all products.
     * For Kafka: clears the consumer's message queue.
     */
    @BeforeEach
    void setup() {
        productRepository.deleteAll();
        KafkaConsumerService.messages.clear();
    }

    /**
     * Integration test for PostgreSQL: saves a product and verifies it can be found.
     */
    @Test
    void shouldSaveAndFindProductWithPostgres() {
        // Given: A new Product instance
        Product product = new Product(null, "Test Product", 99.99);

        // When: The product is saved
        Product savedProduct = productRepository.save(product);

        // Then: Verify product was saved and can be retrieved
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isNotNull();

        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());
        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getName()).isEqualTo("Test Product");
    }


    /**
     * Integration test for Kafka: sends a message and verifies it's consumed.
     */
    @Test
    void shouldSendMessageAndReceiveItWithKafka() {
        // Given: A message to send
        String testMessage = "Hello from Kafka Testcontainers!";

        // When: The message is sent
        kafkaProducerService.sendMessage(testMessage);

        // Then: Wait for the message to be consumed and verify
        await().atMost(Duration.ofSeconds(10))
                .pollInterval(Duration.ofMillis(100))
                .until(() -> KafkaConsumerService.messages.size() == 1);

        assertThat(KafkaConsumerService.messages.poll()).isEqualTo(testMessage);
    }

}

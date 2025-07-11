# 🚀 Harnessing TestContainers For Reliable Integration Tests (Java Demo)

This repository contains the demo project accompanying the talk "Harnessing TestContainers For Reliable Integration Test" presented at **pisa.dev** on July 7, 2025.

The goal of this project is to demonstrate how to leverage the powerful [TestContainers](https://testcontainers.org/) library to write reliable, isolated, and production-like integration tests for Java applications.

---

## ✨ Features

This project showcases integration testing for a Spring Boot application using TestContainers for:

* **PostgreSQL Database:** Testing Spring Data JPA repository interactions against a real PostgreSQL instance. The database schema is automatically managed for tests using Hibernate's `ddl-auto` feature.
* **Apache Kafka:** Testing Spring Kafka producer/consumer interactions against a real Kafka broker.

---

## 🛠️ Technologies Used

* **Java 17+** (JDK)
* **Spring Boot 3.3.x**
* **Maven** (Build Tool)
* **JUnit 5** (Testing Framework)
* **TestContainers 1.21.0** (Core library + JUnit Jupiter, PostgreSQL, Kafka modules)
* **Lombok** (Optional, for reducing boilerplate code)
* **Docker** (Required for TestContainers to run)

---

## ⚙️ Prerequisites

Before you can run this project, ensure you have the following installed:

* **Java Development Kit (JDK) 17 or higher**
* **Apache Maven 3.6.x or higher**
* **Docker Desktop (or Docker Engine)**: TestContainers relies on a running Docker daemon. Make sure Docker is installed and running on your system. You can download it from [docker.com](https://www.docker.com/products/docker-desktop/).

---

## 🚀 Getting Started

Follow these steps to get the project up and running and execute the integration tests.

1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/jonamarkin/testcontainers-talk.git](https://github.com/jonamarkin/testcontainers-talk.git)
    cd testcontainers-talk
    ```

2.  **Ensure Docker is Running:**
    Verify that your Docker daemon is active before running tests.

3.  **Build the Project:**
    ```bash
    mvn clean install
    ```
    This command will download all necessary dependencies and compile the project.

4.  **Run the Integration Tests:**
    ```bash
    mvn clean verify
    ```
    This command executes all tests, including the integration tests. On the first run, TestContainers will download the necessary Docker images (`postgres:17.5`, `confluentinc/cp-kafka:7.6.1`), which might take some time depending on your internet connection. Subsequent runs will be significantly faster as images are cached.

    You can also run tests directly from your IDE (e.g., IntelliJ IDEA, Eclipse, VS Code) by navigating to the `src/test/java` directory and executing the tests.

---

## 📂 Project Structure & Key Files
```bash
testcontainers-talk/
├── pom.xml                                   # Maven build file with dependencies
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/testcontainers/talk/
│   │   │       ├── TestcontainersTalkApplication.java      # Spring Boot main class
│   │   │       ├── model/                                  # JPA entity (Product)
│   │   │       │   └── Product.java
│   │   │       ├── repository/                             # Spring Data JPA repository
│   │   │       │   └── ProductRepository.java
│   │   │       ├── service/                                # Business logic layer
│   │   │       │   └── ProductService.java
│   │   │       └── kafka/                                  # Kafka producer/consumer services
│   │   │           ├── KafkaProducerService.java
│   │   │           └── KafkaConsumerService.java
│   │   └── resources/
│   │       └── application.properties                      # Main application properties
│   └── test/
│       ├── java/
│       │   └── com/example/testcontainers/talk/
│       │       ├── TestcontainersConfiguration.java        # Defines container beans with @ServiceConnection
│       │       ├── TestcontainersTalkApplicationTests.java # Main test class importing TestcontainersConfiguration
│       │       └── TestTestcontainersTalkApplication.java  # Utility to run main app with containers locally
│       └── resources/
│           └── application.properties                      # NEW: Test-specific properties for DDL and logging
└── .gitignore                                              # Standard Git ignore for Java projects
```

---

## 💡 Key TestContainers Concepts & Configuration

This project leverages Spring Boot's integrated TestContainers support for a streamlined testing experience:

* **`TestcontainersConfiguration.java`**:
    * Annotated with `@TestConfiguration`, this class defines Spring `@Bean`s for `KafkaContainer` and `PostgreSQLContainer`.
    * The crucial `@ServiceConnection` annotation on these beans tells Spring Boot to automatically configure the application's connection properties (like `spring.datasource.url`, `spring.kafka.bootstrap-servers`) to point to the dynamically started TestContainers instances. This eliminates the need for manual property mapping via `@DynamicPropertySource` for connection details.

* **`TestcontainersTalkApplicationTests.java`**:
    * Annotated with `@SpringBootTest` and `@Import(TestcontainersConfiguration.class)`, this class ensures the full Spring application context is loaded, including the TestContainers-managed services defined in `TestcontainersConfiguration`.
    * `@BeforeEach` methods are used to ensure data isolation by clearing the database and Kafka consumer queue before each test.

* **`src/test/resources/application.properties`**:
    * This file contains properties specific to the test environment.
    * `spring.jpa.hibernate.ddl-auto=create-drop`: This property instructs Hibernate to automatically create the database schema (including the `product` table) when the test application context starts and drop it when it shuts down, ensuring a clean slate for each test run.
    * `spring.jpa.show-sql=true` and `spring.jpa.open-in-view=false`: These are common JPA configurations for debugging and performance in tests.
    * The `spring.datasource.url`, `username`, and `password` placeholders in this file are automatically overridden by the values provided by `@ServiceConnection` from the running PostgreSQL container.


---

## 🤝 Contributing

Feel free to fork this repository, explore the code, and experiment with different TestContainers modules. Pull requests are welcome for any improvements or bug fixes.

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).

---

## 📧 Contact

Jonathan Ato Markin
* GitHub: [@jonamarkin](https://github.com/jonamarkin)
* LinkedIn: [https://www.linkedin.com/in/atomarkin/](https://www.linkedin.com/in/atomarkin/)
* X: [mr_markin1](https://x.com/mr_markin1)

---
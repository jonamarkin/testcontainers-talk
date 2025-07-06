package com.markin.testcontainerstalk;

import org.springframework.boot.SpringApplication;

public class TestTestcontainersTalkApplication {

    public static void main(String[] args) {
        SpringApplication.from(TestcontainersTalkApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}

package com.polling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class PollingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PollingApplication.class, args);
    }

}

package com.geonho.moneyflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MoneyflowApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoneyflowApplication.class, args);
    }
}

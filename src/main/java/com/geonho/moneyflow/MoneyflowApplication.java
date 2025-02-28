package com.geonho.moneyflow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("com.geonho.moneyflow.repository")
public class MoneyflowApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoneyflowApplication.class, args);
    }
}

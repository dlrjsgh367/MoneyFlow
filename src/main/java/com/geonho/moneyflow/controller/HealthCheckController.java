package com.geonho.moneyflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthCheckController {

    @GetMapping("/health")
    // Map 종류
    // https://wooktae.tistory.com/38
    public Map<String, String> healthCheck() {
        return Map.of("status", "OK");
    }
}

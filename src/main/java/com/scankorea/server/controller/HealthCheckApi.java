package com.scankorea.server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthCheckApi {
    @GetMapping("/api/health-check")
    public String healthCheck() {
        return "health-check";
    }
}

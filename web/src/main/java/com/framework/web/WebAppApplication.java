package com.framework.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebAppApplication {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application,application-web");
        SpringApplication.run(WebAppApplication.class, args);
    }
}

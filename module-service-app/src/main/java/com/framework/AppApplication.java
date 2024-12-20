package com.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= "com.framework")
public class AppApplication {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application,application-security,application-h2-jpa,application-app");
        SpringApplication.run(AppApplication.class, args);
    }
}

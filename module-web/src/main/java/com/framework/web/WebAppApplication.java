package com.framework.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages= "com.framework.web")
public class WebAppApplication {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application,application-security,application-h2,application-web");
        SpringApplication.run(WebAppApplication.class, args);
    }
}

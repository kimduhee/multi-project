package com.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= "com.framework")
public class WebApplication {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application,application-security,application-h2-mybatis,application-web");
        SpringApplication.run(WebApplication.class, args);
    }
}

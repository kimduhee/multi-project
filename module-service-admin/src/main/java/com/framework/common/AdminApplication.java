package com.framework.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * packageName    : com.framework.common
 * fileName       : AdminApplication
 * author         : NAMANOK
 * date           : 2024-12-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-23        NAMANOK       최초 생성
 */
@SpringBootApplication(scanBasePackages= "com.framework")
public class AdminApplication {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application,application-h2-jpa,application-admin");
        SpringApplication.run(AdminApplication.class, args);
    }
}

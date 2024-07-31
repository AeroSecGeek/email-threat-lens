package com.aerosecgeek.emailthreatlensservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmailThreatLensServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailThreatLensServiceApplication.class, args);
    }

}

package com.jmaguina.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase main en springboot
 */
@SpringBootApplication(scanBasePackages = {"com.jmaguina.mail"})
public class SendMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SendMailApplication.class, args);
    }

}


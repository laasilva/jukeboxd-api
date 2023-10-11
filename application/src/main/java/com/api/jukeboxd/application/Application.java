package com.api.jukeboxd.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.api.jukeboxd")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
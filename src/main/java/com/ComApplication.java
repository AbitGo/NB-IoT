package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

@SpringBootApplication
public class ComApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComApplication.class, args);
    }

}

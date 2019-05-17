package com.luke.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AppLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppLoginApplication.class,args) ;
    }

}

package com.luke.web.space;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AppSpace {

    public static void main(String[] args) {
        SpringApplication.run(AppSpace.class,args) ;
    }
}

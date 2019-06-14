package com.luke.web.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AppCustomer {

    public static void main(String[] args) {
        SpringApplication.run(AppCustomer.class,args) ;
    }
}

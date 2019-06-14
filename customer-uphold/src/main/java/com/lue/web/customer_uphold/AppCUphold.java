package com.lue.web.customer_uphold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AppCUphold {

    public static void main(String[] args) {
        SpringApplication.run(AppCUphold.class,args) ;
    }
}

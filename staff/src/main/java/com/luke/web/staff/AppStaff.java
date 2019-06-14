package com.luke.web.staff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AppStaff {

    public static void main(String[] args) {
        SpringApplication.run(AppStaff.class,args) ;
    }
}
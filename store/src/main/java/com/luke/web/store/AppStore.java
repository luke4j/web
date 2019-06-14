package com.luke.web.store;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AppStore {
    public static void main(String[] args) {
        SpringApplication.run(AppStore.class,args) ;
    }
}

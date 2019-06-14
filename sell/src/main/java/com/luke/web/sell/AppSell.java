package com.luke.web.sell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AppSell {

    public static void main(String[] args) {
        SpringApplication.run(AppSell.class,args) ;
    }
}

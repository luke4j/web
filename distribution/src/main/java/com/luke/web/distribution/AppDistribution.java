package com.luke.web.distribution;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class AppDistribution {
    public static void main(String[] args) {
        SpringApplication.run(AppDistribution.class,args) ;
    }
}

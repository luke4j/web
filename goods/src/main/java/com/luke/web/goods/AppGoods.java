package com.luke.web.goods;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AppGoods {

    public static void main(String[] args) {
        SpringApplication.run(AppGoods.class,args) ;
    }
}

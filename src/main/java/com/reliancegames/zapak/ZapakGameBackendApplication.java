// src/main/java/com/reliancegames/zapak/ZapakGameBackendApplication.java
package com.reliancegames.zapak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ZapakGameBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZapakGameBackendApplication.class, args);
    }
}
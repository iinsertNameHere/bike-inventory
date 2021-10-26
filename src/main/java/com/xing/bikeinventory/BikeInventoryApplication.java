package com.xing.bikeinventory;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class BikeInventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BikeInventoryApplication.class, args);
    }

}

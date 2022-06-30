package com.atypon.nosqldemoapplication;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NoSqlDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoSqlDemoApplication.class, args);
    }

    @Bean
    public String databaseUrl(ApplicationArguments arguments) {
        return arguments.getOptionValues("db").get(0);
    }
}

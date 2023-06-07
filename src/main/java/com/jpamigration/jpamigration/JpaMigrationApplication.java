package com.jpamigration.jpamigration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class JpaMigrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaMigrationApplication.class, args);
    }

}

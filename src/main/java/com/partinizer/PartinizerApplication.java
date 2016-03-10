package com.partinizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * Created by vincent on 10/03/16.
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class})
public class PartinizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PartinizerApplication.class, args);
    }
}

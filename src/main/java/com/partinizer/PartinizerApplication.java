package com.partinizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;


/**
 * Created by vincent on 10/03/16.
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,EmbeddedServletContainerAutoConfiguration.class,
        WebMvcAutoConfiguration.class
})
public class PartinizerApplication {

    public static void main(String[] args) {

        SpringApplication.run(PartinizerApplication.class, args);
    }
}

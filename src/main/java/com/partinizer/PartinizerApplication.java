package com.partinizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


/**
 * Created by vincent on 10/03/16.
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class
})
public class PartinizerApplication {

    public static void main(String[] args) {

        SpringApplication.run(PartinizerApplication.class, args);
    }

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://postgresql-m1pai.alwaysdata.net/m1pai_pai");
        driverManagerDataSource.setUsername("m1pai");
        driverManagerDataSource.setPassword("m1pailille1");
        return driverManagerDataSource;
    }
}

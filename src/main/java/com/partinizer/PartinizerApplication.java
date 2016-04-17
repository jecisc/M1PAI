package com.partinizer;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;


/**
 * Created by vincent on 10/03/16.
 */
@ComponentScan
@SpringBootApplication
@EnableAutoConfiguration(/*exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class

}*/)
public class PartinizerApplication {

    public static void main(String[] args) {

        SpringApplication.run(PartinizerApplication.class, args);
    }

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost/vincent");
        driverManagerDataSource.setUsername("vincent");
        driverManagerDataSource.setPassword("vincent");
        return driverManagerDataSource;
    }


}

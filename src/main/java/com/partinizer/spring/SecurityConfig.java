package com.partinizer.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


/**
 * Created by vincent on 13/04/16.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/user/validation").permitAll()
                .antMatchers(HttpMethod.GET, "/user/forgotPassword").permitAll()
                .antMatchers(HttpMethod.POST,"/user/create").permitAll().and()
                .authorizeRequests()
                .anyRequest().authenticated().and()
                .httpBasic();

        //TODO For now I let everything pass, let's retrain this later
        /*http .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/event/**").permitAll()
                .antMatchers(HttpMethod.GET, "/event/**").permitAll()
                .antMatchers(HttpMethod.POST,"/event/**").permitAll().and()
                .authorizeRequests()
                .anyRequest().authenticated().and()
                .httpBasic();*/

       http.csrf().disable();
    }

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select pseudo as username , passwd as password, isActive as enabled " +
                                "from appliuser " +
                                "where pseudo=?")
                .authoritiesByUsernameQuery(
                "select pseudo as username, 'ROLE_USER' from appliuser where pseudo=?");
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


}

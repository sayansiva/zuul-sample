package com.example.authservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

/**
 * Created by Sayan on 17.07.2017.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and()

                .headers()
                .cacheControl()
                .and()
                .and()

                .formLogin()
                .defaultSuccessUrl("/")
                .loginPage("/auth/login.html")
                .failureUrl("/auth/login.html?error")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/auth/login.html?logout")
                .logoutUrl("/auth/logout.html")
                .permitAll()
                .and()

                .authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password("pass1").roles("USER").and()
                .withUser("user2").password("pass2").roles("USER", "ADMIN");
    }
}

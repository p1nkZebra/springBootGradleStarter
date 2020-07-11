package com.company.springBootTemplate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Profile("basicAuth")
@Configuration
@EnableWebSecurity
public class WebSecurityBasicAuthConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                    .disable()
//                .authorizeRequests()
////                    .antMatchers("/security/admin").hasRole("ADMIN")
////                    .antMatchers("/security/any").hasAnyRole("ADMIN", "USER")
//                    .antMatchers("/security/admin").hasAnyAuthority("ADMIN_SECTION")
//                    .antMatchers("/security/any").hasAnyAuthority("USER_SECTION", "ADMIN_SECTION")
////                    .antMatchers("/**").authenticated()
//                    .antMatchers("/**").permitAll()
//                .and()
//                    .httpBasic();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                    .withUser("user")
//                    .password(passwordEncoder().encode("123"))
//                    .roles("USER")
//                    .authorities("USER_SECTION")
//                .and()
//                    .withUser("admin")
//                    .password(passwordEncoder().encode("456"))
//                    .roles("ADMIN")
//                    .authorities("USER_SECTION", "ADMIN_SECTION");
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
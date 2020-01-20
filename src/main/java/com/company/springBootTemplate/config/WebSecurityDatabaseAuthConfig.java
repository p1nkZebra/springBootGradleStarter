package com.company.springBootTemplate.config;

import com.company.springBootTemplate.logic.auth.dbAuth.DbAuthUserPrincipalDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Profile("databaseAuth")
@Configuration
@EnableWebSecurity
public class WebSecurityDatabaseAuthConfig extends WebSecurityConfigurerAdapter {

    private DbAuthUserPrincipalDetailsService dbAuthUserPrincipalDetailsService;

    public WebSecurityDatabaseAuthConfig(DbAuthUserPrincipalDetailsService dbAuthUserPrincipalDetailsService) {
        this.dbAuthUserPrincipalDetailsService = dbAuthUserPrincipalDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/security/admin").hasRole("ADMIN")
                    .antMatchers("/security/any").hasAnyRole("ADMIN", "USER")
//                    .antMatchers("/**").authenticated()
                    .antMatchers("/**").permitAll()
                .and()
                    .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(dbAuthUserPrincipalDetailsService);
        return daoAuthenticationProvider;
    }
}
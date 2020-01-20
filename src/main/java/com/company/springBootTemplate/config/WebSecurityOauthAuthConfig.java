package com.company.springBootTemplate.config;

import com.company.springBootTemplate.domain.GoogleAccountUser;
import com.company.springBootTemplate.repository.OauthUserRepository;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.time.LocalDateTime;

@Profile("oauthAuth")
@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityOauthAuthConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/security/admin").authenticated()
                .antMatchers("/security/any").authenticated()
//                    .antMatchers("/**").authenticated()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }

    @Bean
    public PrincipalExtractor principalExtractor(OauthUserRepository userRepo) {
        return map -> {
            String id = (String) map.get("sub");

            GoogleAccountUser user = userRepo.findById(id).orElseGet(() -> {
                GoogleAccountUser newUser = new GoogleAccountUser();

                newUser.setId(id);
                newUser.setName((String) map.get("name"));
                newUser.setEmail((String) map.get("email"));
                newUser.setGender((String) map.get("gender"));
                newUser.setLocale((String) map.get("locale"));
                newUser.setUserpic((String) map.get("picture"));

                newUser.setLastVisit(LocalDateTime.now());

                return newUser;
            });

            return userRepo.save(user);
        };
    }
}
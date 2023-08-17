package com.rest.webservices.restfulwebservices.security;


import com.rest.webservices.restfulwebservices.UserDaoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                auth-> auth.anyRequest().authenticated()
        );

        http.httpBasic(Customizer.withDefaults());
        http.csrf().disable();
        return http.build();
    }

}

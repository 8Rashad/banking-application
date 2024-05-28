package com.example.bankapplication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) ->
                        requests
                                .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                                .requestMatchers("/api/users/**").authenticated()
                                .requestMatchers("api/bank/**").authenticated()

                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/api/users")
                                .permitAll()
                );
        return http.build();
    }

    /*protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configure authentication provider (e.g., in-memory authentication, JDBC authentication, etc.)
        // For example:
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER");
    }*/
}

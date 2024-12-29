package com.example.demo_gradle.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CorsFilter corsFilter;

    public SecurityConfig(CorsFilter corsFilter) {
        this.corsFilter = corsFilter;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(corsFilter, CorsFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/", "/public/**", "/static/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**","/swagger-ui.html","/auth/**", "/admin/**")
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();

    }
}

package com.simple.presence.infrastrcuture.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import jakarta.servlet.Filter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/users/**").denyAll()
                        .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/users/{id}").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/{id}").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.PATCH, "/api/users/patch").hasRole("STUDENT")
                        .requestMatchers("/api/users/**").denyAll()
                        .requestMatchers(HttpMethod.POST, "/api/courses").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/courses").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/courses/{id}}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/courses/{id}}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/courses/patch/{id}").hasRole("ADMIN")
                        .requestMatchers("/api/courses/**").denyAll()
                        .requestMatchers(HttpMethod.POST, "/api/cohorts").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/cohorts").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/cohorts/{id}}").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.DELETE, "/api/cohorts/{id}}").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.PATCH, "/api/cohorts/patch/{id}").hasRole("ADMIN")
                        .requestMatchers("/api/cohorts/**").denyAll()
                        .requestMatchers(
                                "/api/v3/api-docs/**",
                                "/api/swagger-ui.html",
                                "/api/swagger-ui/**",
                                "/api/docs/**",
                                "/api/attendance/**",
                                "/api/auth/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore((Filter) securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}

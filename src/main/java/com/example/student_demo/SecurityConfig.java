package com.example.student_demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // In-Memory users
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User.builder()
                .username("bhanu")
                .password(encoder.encode("1234"))  // encode the password
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http
                // disable CSRF for simplicity
                .csrf(csrf -> csrf.disable())

                // define authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Allow Swagger URLs
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/students","/students/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/students/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/students/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/students/**").authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
package com.example.student_demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //In-Memory users
    @Bean
    public UserDetailsService
    userDetailsService() {
       UserDetails user= User.withDefaultPasswordEncoder()
                .username("bhanu")
                .password("1234")
                .roles("USER")
                .build();
        return new
                InMemoryUserDetailsManager(user);
        }


    @Bean
    public SecurityFilterChain
    securityFilterChain(HttpSecurity http)
        throws Exception {
        http
                // disable CSRF for simplicity
                .csrf(csrf -> csrf.disable())

                //define authorisation rules
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(HttpMethod.GET, "/students/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/students/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/students/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/students/**").authenticated()
                )
        .httpBasic(Customizer.withDefaults());
        return http.build();
    }

}

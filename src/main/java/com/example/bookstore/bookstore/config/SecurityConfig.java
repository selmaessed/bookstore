package com.example.bookstore.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // Mahdollistaa @PreAuthorize ja sec:authorize käytön
public class SecurityConfig {

        @Bean
        public InMemoryUserDetailsManager userDetailsService() {
                UserDetails user = User.withUsername("user")
                                .password("password")
                                .roles("USER")
                                .build();

                UserDetails admin = User.withUsername("admin")
                                .password("admin123")
                                .roles("ADMIN")
                                .build();

                return new InMemoryUserDetailsManager(user, admin);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return NoOpPasswordEncoder.getInstance();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/books/login", "/h2-console/**").permitAll()
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/books/login")
                                                .defaultSuccessUrl("/books", true)
                                                .permitAll())
                                .logout(logout -> logout.permitAll())
                                .csrf(csrf -> csrf.disable())
                                .headers(headers -> headers.frameOptions().disable());

                return http.build();
        }
}
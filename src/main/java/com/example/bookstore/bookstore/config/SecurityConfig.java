package com.example.bookstore.bookstore.config;

import com.example.bookstore.bookstore.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/books/delete/**").hasAuthority("ADMIN") // vain admin voi poistaa
                .requestMatchers("/books/**").authenticated() // kaikki muut kirjautumisen vaativat
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/books/login") // oma login-sivu
                .defaultSuccessUrl("/books", true) // onnistuneen kirjautumisen jälkeen kirjalista
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout") // POST-pyyntö logoutille
                .logoutSuccessUrl("/books/login?logout") // ohjaa login-sivulle uloskirjautumisen jälkeen
                .permitAll()
                .and()
                .csrf(); // CSRF pitää päällä, logout vaatii POST-pyynnön

        return http.build();
    }
}
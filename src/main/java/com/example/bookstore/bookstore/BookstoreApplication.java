package com.example.bookstore.bookstore;

import com.example.bookstore.bookstore.domain.AppUser;
import com.example.bookstore.bookstore.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    // Lisää testikäyttäjät DB:hen
    @Bean
    public CommandLineRunner dataLoader(AppUserRepository repo, BCryptPasswordEncoder encoder) {
        return args -> {
            repo.save(new AppUser("user", encoder.encode("userpass"), "user@example.com", "USER"));
            repo.save(new AppUser("admin", encoder.encode("adminpass"), "admin@example.com", "ADMIN"));
        };
    }
}
package com.example.restfulapiproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * Конфигурация безопасности приложения.
 * Настраивает пользователей, их роли и правила доступа к эндпоинтам.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Создает сервис для управления пользователями.
     * 
     * @param encoder шифратор паролей
     * @return UserDetailsService с двумя пользователями: admin и user
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("123"))
                .roles("ADMIN", "USER")
                .build();

        UserDetails user = User.withUsername("user")
                .password(encoder.encode("123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    /**
     * Конфигурация безопасности HTTP.
     * 
     * @return SecurityConfigurerAdapter для настройки HttpSecurity
     */
    @Bean
    public SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> securityConfigurerAdapter() {
        return new SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
            @Override
            public void configure(HttpSecurity http) throws Exception {
                http.csrf().disable()
                        .authorizeHttpRequests()
                        .requestMatchers("/auth/welcome").permitAll()
                        .and()
                        .authorizeHttpRequests().requestMatchers("/auth/user/**").authenticated()
                        .and()
                        .authorizeHttpRequests().requestMatchers("/auth/admin/**").authenticated()
                        .and().formLogin()
                        .and().build();
            }
        };
    }

    /**
     * Создает шифратор паролей.
     * 
     * @return PasswordEncoder для шифрования паролей
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

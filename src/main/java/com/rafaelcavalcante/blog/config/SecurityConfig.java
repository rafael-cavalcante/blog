package com.rafaelcavalcante.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] AUTH_LIST = {
            "/",
            "/posts",
            "/posts/{id}"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(AUTH_LIST).permitAll()
                        .requestMatchers("/bootstrap/**").permitAll()
                        .requestMatchers("/style/**").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .permitAll())
                .logout(logout -> logout
                        .permitAll()
                        .logoutUrl("/logout")
                        .addLogoutHandler(new SecurityContextLogoutHandler()));
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("rafael")
                        .password(passwordEncoder().encode("123"))
                        .roles("ADMIN")
                        .build());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

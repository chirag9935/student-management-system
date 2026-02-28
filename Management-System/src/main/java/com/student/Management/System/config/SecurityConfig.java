package com.student.Management.System.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

/**
 * Security configuration for the application.
 *
 * - Uses form-based login with a custom /login page
 * - Uses BCrypt password hashing (industry standard)
 * - Has two in-memory users: admin and user
 * - Permits public access to CSS/JS, login, error pages and the H2 console
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * In-memory user store with two accounts:
     * - admin / admin123 → full access (ADMIN role)
     * - user  / user123  → read/view access (USER role)
     *
     * In production, you'd replace this with a database-backed UserDetailsService.
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    /**
     * Defines which URLs require authentication and which are public.
     * Also configures login/logout and CSRF protection.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Public assets — no login needed
                        .requestMatchers("/login", "/css/**", "/js/**", "/images/**", "/error").permitAll()

                        // H2 console is accessible (only in dev/testing environments)
                        .requestMatchers("/h2-console/**").permitAll()

                        // Everything else requires authentication
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                // Allow H2 console in an iframe (dev only — disable in production!)
                .headers(headers -> headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                );

        return http.build();
    }
}
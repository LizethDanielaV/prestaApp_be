package com.example.prestamos.security;

import com.example.prestamos.security.FirebaseTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final FirebaseTokenFilter firebaseTokenFilter;

    public SecurityConfig(FirebaseTokenFilter firebaseTokenFilter) {
        this.firebaseTokenFilter = firebaseTokenFilter;
    }

    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/**").permitAll()

            // Solo ADMIN puede acceder a estas rutas
            .requestMatchers("/api/admin/**").hasRole("ADMIN")

            // Tanto ADMIN como PRESTAMISTA pueden acceder
            .requestMatchers("/zonas/**").hasAnyRole("ADMIN", "PRESTAMISTA")
            .requestMatchers("/clientes/**").hasAnyRole("ADMIN", "PRESTAMISTA")
            .requestMatchers("/creditos/**").hasAnyRole("ADMIN", "PRESTAMISTA")
            .requestMatchers("/frecuencias-pago/**").hasAnyRole("ADMIN", "PRESTAMISTA")

            .anyRequest().authenticated()
        )
        .addFilterBefore(firebaseTokenFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
}
}

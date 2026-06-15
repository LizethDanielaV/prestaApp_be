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
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/zonas/**").hasAnyRole("ADMIN", "PRESTAMISTA")
                .requestMatchers("/clientes/**").hasAnyRole("ADMIN", "PRESTAMISTA")
                .requestMatchers("/creditos/**").hasAnyRole("ADMIN", "PRESTAMISTA")
                .requestMatchers("/frecuencias-pago/**").hasAnyRole("ADMIN", "PRESTAMISTA")
                .requestMatchers("/pagos/**").hasAnyRole("ADMIN", "PRESTAMISTA")
                .anyRequest().authenticated()
            )
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setContentType("application/json;charset=UTF-8");
                    response.setStatus(401);
                    response.getWriter().write(
                        "{\"error\": \"No autenticado: " + authException.getMessage() + "\"}"
                    );
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setContentType("application/json;charset=UTF-8");
                    response.setStatus(403);
                    response.getWriter().write(
                        "{\"error\": \"Acceso denegado: tu usuario no tiene el rol requerido (ADMIN o PRESTAMISTA) para este endpoint\"}"
                    );
                })
            )
            .addFilterBefore(firebaseTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

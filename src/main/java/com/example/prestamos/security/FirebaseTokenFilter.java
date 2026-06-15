package com.example.prestamos.security;

import com.example.prestamos.model.Usuario;
import com.example.prestamos.service.FirebaseAuthService;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class FirebaseTokenFilter extends OncePerRequestFilter {

    private final FirebaseAuthService firebaseAuthService;

    public FirebaseTokenFilter(FirebaseAuthService firebaseAuthService) {
        this.firebaseAuthService = firebaseAuthService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String idToken = authHeader.substring(7);
            try {
                // 1. Verifica el token con Firebase
                FirebaseToken decoded = firebaseAuthService.verifyToken(idToken);

                // 2. Busca el usuario en la BD para obtener su rol
                Usuario usuario = firebaseAuthService.buscarUsuarioPorCorreo(decoded.getEmail());
                String rol = "ROLE_" + usuario.getRol().getNombre();

                // 3. Registra la autenticación con el rol
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                decoded.getEmail(),
                                null,
                                List.of(new SimpleGrantedAuthority(rol))
                        );
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(
                    "{\"error\": \"Token inválido, expirado o usuario no registrado: " + e.getMessage() + "\"}"
                );
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
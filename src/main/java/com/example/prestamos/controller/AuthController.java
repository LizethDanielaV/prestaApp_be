package com.example.prestamos.controller;

import com.example.prestamos.dto.AuthRequest;
import com.example.prestamos.dto.AuthResponse;
import com.example.prestamos.model.Usuario;
import com.example.prestamos.service.FirebaseAuthService;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final FirebaseAuthService firebaseAuthService;

    public AuthController(FirebaseAuthService firebaseAuthService) {
        this.firebaseAuthService = firebaseAuthService;
    }

    @PostMapping("/google")
    public ResponseEntity<?> authenticateWithGoogle(@RequestBody AuthRequest request) {
        try {
            // 1. Verifica el token con Firebase
            FirebaseToken decoded = firebaseAuthService.verifyToken(request.getIdToken());

            // 2. Busca el correo en la BD
            Usuario usuario = firebaseAuthService.buscarUsuarioPorCorreo(decoded.getEmail());

            // 3. Si existe, responde con sus datos
            return ResponseEntity.ok(new AuthResponse(
                    "Autenticado correctamente",
                    decoded.getUid(),
                    usuario.getCorreo(),
                    usuario.getNombre(),
                    usuario.getRol().getNombre()
            ));

        } catch (RuntimeException e) {
            // Correo no está en la BD
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Acceso denegado: tu correo no está registrado");

        } catch (Exception e) {
            // Token de Firebase inválido o expirado
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Token inválido o expirado");
        }
    }
}
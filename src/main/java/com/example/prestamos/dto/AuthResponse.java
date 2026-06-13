package com.example.prestamos.dto;

public class AuthResponse {
    private String message;
    private String uid;
    private String email;
    private String nombre;
    private String rol;

    public AuthResponse(String message, String uid, String email, String nombre, String rol) {
        this.message = message;
        this.uid = uid;
        this.email = email;
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getMessage() { return message; }
    public String getUid() { return uid; }
    public String getEmail() { return email; }
    public String getNombre() { return nombre; }
    public String getRol() { return rol; }
}
package com.inventario.dto;

// DTO para el cuerpo de la petición de actualización (PUT)
public class UpdateRequestDTO {
    private String email;
    private String password; // Opcional
    private String rol;

    // Getters y Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}

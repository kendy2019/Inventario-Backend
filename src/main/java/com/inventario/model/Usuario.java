package com.inventario.model;



import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role rol; // ADMIN o EMPLOYEE

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

   

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRol() { return rol; }
    public void setRol(Role rol) { this.rol = rol; }
}

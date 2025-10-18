package com.inventario.service;

import com.inventario.dto.RegisterRequestDTO;
import com.inventario.dto.UpdateRequestDTO;
import com.inventario.dto.UserDTO;
import com.inventario.model.Role;
import com.inventario.model.Usuario;
import com.inventario.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrar(RegisterRequestDTO request) {
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        
        // --- AQUÍ OCURRE LA CONVERSIÓN ---
        // Convertimos el String "ADMIN" o "EMPLOYEE" al enum Role.ADMIN o Role.EMPLOYEE
        usuario.setRol(Role.valueOf(request.getRol())); 
        
        return usuarioRepository.save(usuario);
    }

    // Método para listar todos los usuarios como DTOs
    public List<UserDTO> findAll() {
        return usuarioRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    // Método para actualizar un usuario
    public UserDTO updateUser(Long id, UpdateRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id: " + id));
        
        usuario.setEmail(request.getEmail());
        usuario.setRol(Role.valueOf(request.getRol()));

        // Actualiza la contraseña solo si se proporciona una nueva
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        
        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return new UserDTO(usuarioActualizado);
    }

    // Método para eliminar un usuario
    public void deleteUser(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}

package com.inventario.controller;

import com.inventario.dto.UpdateRequestDTO;
import com.inventario.dto.UserDTO;
import com.inventario.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasAuthority('ADMIN')") // Protege toda la clase para que solo Admins puedan acceder
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para obtener la lista de todos los usuarios.
     * Responde a GET /api/users
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    /**
     * Endpoint para actualizar un usuario existente.
     * Responde a PUT /api/users/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UpdateRequestDTO request) {
        return ResponseEntity.ok(usuarioService.updateUser(id, request));
    }

    /**
     * Endpoint para eliminar un usuario.
     * Responde a DELETE /api/users/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usuarioService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

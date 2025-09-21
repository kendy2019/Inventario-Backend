package com.inventario.security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inventario.model.Usuario;
import com.inventario.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

 private final UsuarioRepository usuarioRepository;

 public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
     this.usuarioRepository = usuarioRepository;
 }

 @Override
 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     Usuario usuario = usuarioRepository.findByUsername(username)
             .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

     return org.springframework.security.core.userdetails.User.builder()
             .username(usuario.getUsername())
             .password(usuario.getPassword()) // encriptada con BCrypt
             .roles(usuario.getRol().name()) // ADMIN o EMPLEADO
             .build();
 }
}


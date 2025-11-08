package com.inventario.repository;

import com.inventario.model.Cliente;
import com.inventario.model.TipoCliente;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByTelefono(String telefono);
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);

    // 🔹 Nuevo método opcional para filtrar por tipo de cliente
    List<Cliente> findByTipoCliente(TipoCliente tipoCliente);
}

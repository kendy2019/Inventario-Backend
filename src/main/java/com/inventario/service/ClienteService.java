package com.inventario.service;

import com.inventario.model.Cliente;
import com.inventario.model.TipoCliente;
import com.inventario.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Obtiene todos los clientes de la base de datos.
     */
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    /**
     * Busca un cliente por su ID.
     */
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    /**
     * Guarda un nuevo cliente o actualiza uno existente.
     */
    public Cliente guardar(Cliente cliente) {
        // Si no se especifica tipo, se asigna un valor por defecto
        if (cliente.getTipoCliente() == null) {
            cliente.setTipoCliente(TipoCliente.FINAL);
        }
        return clienteRepository.save(cliente);
    }

    /**
     * Actualiza un cliente existente.
     */
    public Cliente actualizar(Long id, Cliente clienteActualizado) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    clienteExistente.setNombre(clienteActualizado.getNombre());
                    clienteExistente.setTelefono(clienteActualizado.getTelefono());
                    clienteExistente.setEmail(clienteActualizado.getEmail());
                    clienteExistente.setDireccion(clienteActualizado.getDireccion());
                    clienteExistente.setTipoCliente(clienteActualizado.getTipoCliente());
                    return clienteRepository.save(clienteExistente);
                })
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + id));
    }

    /**
     * Elimina un cliente por su ID.
     */
    public void eliminar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Cliente no encontrado con ID: " + id);
        }
        clienteRepository.deleteById(id);
    }

    /**
     * Busca clientes por nombre.
     */
    public List<Cliente> buscarPorNombre(String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }

    /**
     * Busca clientes por tipo (opcional).
     */
    public List<Cliente> buscarPorTipo(TipoCliente tipoCliente) {
        return clienteRepository.findByTipoCliente(tipoCliente);
    }
}

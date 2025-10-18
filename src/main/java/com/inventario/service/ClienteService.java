package com.inventario.service;

import com.inventario.model.Cliente;
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
     * @return Una lista de todos los clientes.
     */
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    /**
     * Busca un cliente por su ID.
     * @param id El ID del cliente.
     * @return Un Optional con el cliente si se encuentra.
     */
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    /**
     * Guarda un nuevo cliente o actualiza uno existente.
     * @param cliente El cliente a guardar.
     * @return El cliente guardado.
     */
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Actualiza un cliente existente.
     * @param id El ID del cliente a actualizar.
     * @param clienteActualizado Los nuevos datos del cliente.
     * @return El cliente actualizado.
     */
    public Cliente actualizar(Long id, Cliente clienteActualizado) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    clienteExistente.setNombre(clienteActualizado.getNombre());
                    clienteExistente.setTelefono(clienteActualizado.getTelefono());
                    return clienteRepository.save(clienteExistente);
                })
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + id));
    }

    /**
     * Elimina un cliente por su ID.
     * @param id El ID del cliente a eliminar.
     */
    public void eliminar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Cliente no encontrado con ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
    
    /**
     * Busca clientes por un término de búsqueda en su nombre.
     * @param nombre El término para buscar en el nombre de los clientes.
     * @return Una lista de clientes que coinciden.
     */
    public List<Cliente> buscarPorNombre(String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }
}

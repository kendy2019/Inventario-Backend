package com.inventario.service;
 

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inventario.dto.ProductoBajoStockDTO;
import com.inventario.model.Producto;
import com.inventario.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto actualizar(Long id, Producto productoActualizado) {
        Producto producto = buscarPorId(id);
        producto.setNombre(productoActualizado.getNombre());
        producto.setDescripcion(productoActualizado.getDescripcion());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setStock(productoActualizado.getStock());
        return productoRepository.save(producto);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }
    public List<ProductoBajoStockDTO> findLowStockProducts(Integer umbral) {
       
        return productoRepository.findProductosConBajoStock(umbral).stream()
                
                .map(p -> new ProductoBajoStockDTO(p.getId(), p.getNombre(), p.getStock()))
               
                .collect(Collectors.toList());
    }
}

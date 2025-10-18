package com.inventario.service;

import com.inventario.dto.VentaRequestDTO;
import com.inventario.dto.VentaItemDTO;
import com.inventario.model.Producto;
import com.inventario.model.Venta;
import com.inventario.model.VentaDetalle;
import com.inventario.repository.ProductoRepository;
import com.inventario.repository.VentaRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.inventario.dto.VentaRecienteDTO;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;

    public VentaService(VentaRepository ventaRepository, ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
    }
    
    public List<VentaRecienteDTO> findRecentSales() {
        return ventaRepository.findTop5ByOrderByFechaDesc().stream()
                .map(VentaRecienteDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Venta crearVenta(VentaRequestDTO request) {
        Venta venta = new Venta();
        // Mapear datos de cotización y venta
        venta.setClienteNombre(request.getCotizacion().getClienteNombre());
        venta.setClienteTelefono(request.getCotizacion().getClienteTelefono());
        // ... (mapear resto de campos de cotización)
        venta.setTotal(request.getTotal());
        venta.setFecha(request.getFecha());
        venta.setRegistradoPor(request.getRegistradoPor());

        for (VentaItemDTO itemDTO : request.getItems()) {
            Producto producto = productoRepository.findById(itemDTO.getProductoId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado: " + itemDTO.getProductoId()));

            if (producto.getStock() < itemDTO.getCantidad()) {
                throw new IllegalStateException("Stock insuficiente para: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - itemDTO.getCantidad());
            productoRepository.save(producto);

            VentaDetalle detalle = new VentaDetalle();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setCantidad(itemDTO.getCantidad());
            detalle.setPrecio(itemDTO.getPrecio());
            venta.getItems().add(detalle);
        }
        return ventaRepository.save(venta);
    }
}
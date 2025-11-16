package com.inventario.service;

import com.inventario.dto.GuiaIngresoDTO;
import com.inventario.dto.GuiaIngresoDetalleDTO;
import com.inventario.model.GuiaIngreso;
import com.inventario.model.GuiaIngresoDetalle;
import com.inventario.model.Producto;
import com.inventario.repository.GuiaIngresoRepository;
import com.inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class GuiaIngresoService {

    private final GuiaIngresoRepository guiaIngresoRepository;
    private final ProductoRepository productoRepository;

    public GuiaIngresoService(GuiaIngresoRepository guiaIngresoRepository, ProductoRepository productoRepository) {
        this.guiaIngresoRepository = guiaIngresoRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public GuiaIngreso procesarGuiaIngreso(GuiaIngresoDTO dto) {
        GuiaIngreso guia = new GuiaIngreso();
        if (dto.getFechaIngreso() != null) {
            guia.setFechaIngreso(LocalDate.parse(dto.getFechaIngreso()));
        } else {
            guia.setFechaIngreso(LocalDate.now());
        }
        guia.setNotas(dto.getNotas());

        // procesar cada detalle: validar existencia del producto y aumentar stock
        if (dto.getDetalle() != null) {
            for (GuiaIngresoDetalleDTO d : dto.getDetalle()) {
                Long productoId = d.getProductoId();
                Integer cantidad = d.getCantidad() == null ? 0 : d.getCantidad();
                if (cantidad <= 0) {
                    throw new IllegalArgumentException("La cantidad debe ser mayor a 0 para el productoId: " + productoId);
                }

                Producto producto = productoRepository.findById(productoId)
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado id=" + productoId));

                // crear detalle
                GuiaIngresoDetalle detalle = new GuiaIngresoDetalle();
                detalle.setCantidad(cantidad);
                detalle.setProducto(producto);

                // añadir al guia (persistido por cascade)
                guia.addDetalle(detalle);

                // actualizar stock del producto
                Integer nuevoStock = (producto.getStock() == null ? 0 : producto.getStock()) + cantidad;
                producto.setStock(nuevoStock);
                productoRepository.save(producto); // se puede omitir si se usa contexto persistente, pero lo dejamos explícito
            }
        }

        return guiaIngresoRepository.save(guia);
    }
}

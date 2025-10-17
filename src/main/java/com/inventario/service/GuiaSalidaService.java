package com.inventario.service;

import com.inventario.dto.GuiaSalidaRequestDTO;
import com.inventario.dto.GuiaSalidaItemDTO;
import com.inventario.model.GuiaSalida;
import com.inventario.model.GuiaSalidaDetalle;
import com.inventario.model.Producto;
import com.inventario.repository.GuiaSalidaRepository;
import com.inventario.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GuiaSalidaService {

    private final GuiaSalidaRepository guiaSalidaRepository;
    private final ProductoRepository productoRepository;

    public GuiaSalidaService(GuiaSalidaRepository guiaSalidaRepository, ProductoRepository productoRepository) {
        this.guiaSalidaRepository = guiaSalidaRepository;
        this.productoRepository = productoRepository;
    }

    /**
     * Crea una guía de salida y descuenta el stock de los productos correspondientes.
     * La anotación @Transactional asegura que toda la operación sea atómica:
     * si algo falla (ej. no hay stock), se revierten todos los cambios en la base de datos.
     *
     * @param requestDTO El DTO con los datos de la guía de salida.
     * @return La entidad GuiaSalida que fue creada y guardada.
     */
    @Transactional
    public GuiaSalida crearGuiaSalida(GuiaSalidaRequestDTO requestDTO) {
        // 1. Crear la cabecera de la guía
        GuiaSalida guia = new GuiaSalida();
        guia.setDestino(requestDTO.getDestino());
        guia.setMotivo(requestDTO.getMotivo());
        guia.setFechaSalida(requestDTO.getFechaSalida());
        guia.setObservaciones(requestDTO.getObservaciones());

        // 2. Procesar cada item (producto) de la guía
        for (GuiaSalidaItemDTO itemDTO : requestDTO.getItems()) {
            // Buscar el producto en la base de datos
            Producto producto = productoRepository.findById(itemDTO.getProductoId())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + itemDTO.getProductoId()));

            // Validar si hay suficiente stock
            if (producto.getStock() < itemDTO.getCantidad()) {
                throw new IllegalStateException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            // Descontar el stock del producto
            producto.setStock(producto.getStock() - itemDTO.getCantidad());
            productoRepository.save(producto); // Actualizar el producto

            // Crear el detalle de la guía
            GuiaSalidaDetalle detalle = new GuiaSalidaDetalle();
            detalle.setProducto(producto);
            detalle.setCantidad(itemDTO.getCantidad());

            // Agregar el detalle a la guía principal
            guia.addItem(detalle);
        }

        // 3. Guardar la guía de salida (y sus detalles en cascada)
        return guiaSalidaRepository.save(guia);
    }
}

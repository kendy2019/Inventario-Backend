package com.inventario.dto;

/**
 * DTO (Data Transfer Object) que representa un item (producto y cantidad)
 * dentro de la solicitud de creación de una guía de salida.
 */
public class GuiaSalidaItemDTO {

    private Long productoId;
    private int cantidad;

    // Getters y Setters
    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

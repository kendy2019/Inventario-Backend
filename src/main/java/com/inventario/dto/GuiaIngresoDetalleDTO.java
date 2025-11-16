package com.inventario.dto;

public class GuiaIngresoDetalleDTO {
    private Long productoId;
    private Integer cantidad;

    // getters / setters
    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}

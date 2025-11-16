package com.inventario.model;

import jakarta.persistence.*;

@Entity
@Table(name = "guia_ingreso_detalle")
public class GuiaIngresoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guia_ingreso_id")
    private GuiaIngreso guiaIngreso;

    // getters / setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public GuiaIngreso getGuiaIngreso() { return guiaIngreso; }
    public void setGuiaIngreso(GuiaIngreso guiaIngreso) { this.guiaIngreso = guiaIngreso; }
}

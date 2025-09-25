package com.inventario.model;


import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "venta_items")
public class VentaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con venta
    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    // Relación con producto
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
                                             
    private Integer cantidad;
    private BigDecimal precioUnitario;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
}

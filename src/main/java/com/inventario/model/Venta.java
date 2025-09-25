package com.inventario.model;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_venta", unique = true, nullable = false)
    private String codigoVenta;

    @Column(name = "cliente_nombre", nullable = false)
    private String clienteNombre;

    private String marcaCelular;
    private String detalleCelular;  

    private LocalDateTime fechaIngresado;

    private BigDecimal total;

    // Relacion con USUARIO,l quien registro la venta
    @ManyToOne
    @JoinColumn(name = "registrado_por")
    private Usuario registradoPor;

    // Relación con los ítems de la venta
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaItem> items;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoVenta() { return codigoVenta; }
    public void setCodigoVenta(String codigoVenta) { this.codigoVenta = codigoVenta; }
 
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }

    public String getMarcaCelular() { return marcaCelular; }
    public void setMarcaCelular(String marcaCelular) { this.marcaCelular = marcaCelular; }

    public String getDetalleCelular() { return detalleCelular; }
    public void setDetalleCelular(String detalleCelular) { this.detalleCelular = detalleCelular; }

    public LocalDateTime getFechaIngresado() { return fechaIngresado; }
    public void setFechaIngresado(LocalDateTime fechaIngresado) { this.fechaIngresado = fechaIngresado; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public Usuario getRegistradoPor() { return registradoPor; }
    public void setRegistradoPor(Usuario registradoPor) { this.registradoPor = registradoPor; }

    public List<VentaItem> getItems() { return items; }
    public void setItems(List<VentaItem> items) { this.items = items; }
}

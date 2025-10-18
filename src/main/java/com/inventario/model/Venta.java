package com.inventario.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    private String clienteNombre;
    private String clienteTelefono;
    private String marcaCelular;
    private String detalleCelular;
    private String observaciones;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private ZonedDateTime fecha;

    private String registradoPor;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaDetalle> items = new ArrayList<>();

    // Getters y Setters para todos los campos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }
    public String getClienteTelefono() { return clienteTelefono; }
    public void setClienteTelefono(String clienteTelefono) { this.clienteTelefono = clienteTelefono; }
    public String getMarcaCelular() { return marcaCelular; }
    public void setMarcaCelular(String marcaCelular) { this.marcaCelular = marcaCelular; }
    public String getDetalleCelular() { return detalleCelular; }
    public void setDetalleCelular(String detalleCelular) { this.detalleCelular = detalleCelular; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public ZonedDateTime getFecha() { return fecha; }
    public void setFecha(ZonedDateTime fecha) { this.fecha = fecha; }
    public String getRegistradoPor() { return registradoPor; }
    public void setRegistradoPor(String registradoPor) { this.registradoPor = registradoPor; }
    public List<VentaDetalle> getItems() { return items; }
    public void setItems(List<VentaDetalle> items) { this.items = items; }
}
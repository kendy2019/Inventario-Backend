package com.inventario.dto;

import com.inventario.model.Venta;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;

public class VentaRecienteDTO {
    private Long id;
    private String clienteNombre;
    private BigDecimal total;
    private ZonedDateTime fecha;
    
    public VentaRecienteDTO(Venta venta) {
        this.id = venta.getId();
        this.clienteNombre = venta.getClienteNombre();
        this.total = venta.getTotal();
        this.fecha = venta.getFecha();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClienteNombre() {
		return clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public ZonedDateTime getFecha() {
		return fecha;
	}

	public void setFecha(ZonedDateTime fecha) {
		this.fecha = fecha;
	}

    
    
    
}
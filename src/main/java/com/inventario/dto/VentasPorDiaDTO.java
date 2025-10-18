package com.inventario.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VentasPorDiaDTO {
    private LocalDate fecha;
    private BigDecimal total;

    public VentasPorDiaDTO(LocalDate fecha, BigDecimal total) {
        this.fecha = fecha;
        this.total = total;
    }

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

    
    
}

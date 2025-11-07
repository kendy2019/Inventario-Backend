package com.inventario.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public class VentasPorDiaDTO {
    private Instant fecha;
    private BigDecimal total;

    public VentasPorDiaDTO(Instant fecha, BigDecimal total) {
        this.fecha = fecha;
        this.total = total;
    }

	public Instant getFecha() {
		return fecha;
	}

	public void setFecha(Instant fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

    
    
}

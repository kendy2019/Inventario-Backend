package com.inventario.dto;

import java.math.BigDecimal;

public class ReporteStatsDTO {
    private BigDecimal totalVentas;
    private Long numeroOrdenes;
    private Long clientesActivos;
    private BigDecimal ticketPromedio;
    
    
    
   
    
    
	public ReporteStatsDTO() {
		
	}
	
	public ReporteStatsDTO(BigDecimal totalVentas, Long numeroOrdenes, Long clientesActivos,
			BigDecimal ticketPromedio) {
		this.totalVentas = totalVentas;
		this.numeroOrdenes = numeroOrdenes;
		this.clientesActivos = clientesActivos;
		this.ticketPromedio = ticketPromedio;
	}


	public BigDecimal getTotalVentas() {
		return totalVentas;
	}
	public void setTotalVentas(BigDecimal totalVentas) {
		this.totalVentas = totalVentas;
	}
	public Long getNumeroOrdenes() {
		return numeroOrdenes;
	}
	public void setNumeroOrdenes(Long numeroOrdenes) {
		this.numeroOrdenes = numeroOrdenes;
	}
	public Long getClientesActivos() {
		return clientesActivos;
	}
	public void setClientesActivos(Long clientesActivos) {
		this.clientesActivos = clientesActivos;
	}
	public BigDecimal getTicketPromedio() {
		return ticketPromedio;
	}
	public void setTicketPromedio(BigDecimal ticketPromedio) {
		this.ticketPromedio = ticketPromedio;
	}

    
}
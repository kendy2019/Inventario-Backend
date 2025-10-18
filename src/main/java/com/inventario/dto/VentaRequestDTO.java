package com.inventario.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public class VentaRequestDTO {
    private CotizacionDTO cotizacion;
    private List<VentaItemDTO> items;
    private BigDecimal total;
    private ZonedDateTime fecha;
    private String registradoPor;
	public CotizacionDTO getCotizacion() {
		return cotizacion;
	}
	public void setCotizacion(CotizacionDTO cotizacion) {
		this.cotizacion = cotizacion;
	}
	public List<VentaItemDTO> getItems() {
		return items;
	}
	public void setItems(List<VentaItemDTO> items) {
		this.items = items;
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
	public String getRegistradoPor() {
		return registradoPor;
	}
	public void setRegistradoPor(String registradoPor) {
		this.registradoPor = registradoPor;
	}

    
    
    
    
}

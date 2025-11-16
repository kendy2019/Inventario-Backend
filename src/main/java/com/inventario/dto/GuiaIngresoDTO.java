package com.inventario.dto;

import java.util.List;

public class GuiaIngresoDTO {
    private String fechaIngreso; // "yyyy-MM-dd"
    private String notas;
    private List<GuiaIngresoDetalleDTO> detalle;

    
    public String getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(String fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }
    public List<GuiaIngresoDetalleDTO> getDetalle() { return detalle; }
    public void setDetalle(List<GuiaIngresoDetalleDTO> detalle) { this.detalle = detalle; }
}

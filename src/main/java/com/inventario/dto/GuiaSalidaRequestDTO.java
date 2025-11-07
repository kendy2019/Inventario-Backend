package com.inventario.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO que mapea el JSON de la solicitud del frontend para crear una Guía de Salida.
 */
public class GuiaSalidaRequestDTO {

    private String destino;
    private String motivo;
    private String observaciones;
    private Instant fechaSalida;
    private List<GuiaSalidaItemDTO> items;

    // Getters y Setters
    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Instant getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Instant fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public List<GuiaSalidaItemDTO> getItems() {
        return items;
    }

    public void setItems(List<GuiaSalidaItemDTO> items) {
        this.items = items;
    }
}

package com.inventario.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa la cabecera de una guía de salida de productos.
 * Contiene la información general del movimiento, como el destino, motivo y fecha.
 */
@Entity
@Table(name = "guias_salida")
public class GuiaSalida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    private String motivo;

    @Column(name = "fecha_salida", nullable = false)
    private Instant fechaSalida;

    private String observaciones;

    /**
     * Relación uno-a-muchos con los detalles (productos) de la guía.
     * - mappedBy: Indica que la entidad GuiaSalidaDetalle es la dueña de la relación.
     * - cascade = CascadeType.ALL: Cualquier cambio en la GuiaSalida (guardar, eliminar) se propaga a sus detalles.
     * - orphanRemoval = true: Si se elimina un detalle de esta lista, también se eliminará de la base de datos.
     */
    @OneToMany(
        mappedBy = "guiaSalida",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<GuiaSalidaDetalle> items = new ArrayList<>();

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Instant getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Instant instant) {
        this.fechaSalida = instant;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<GuiaSalidaDetalle> getItems() {
        return items;
    }

    public void setItems(List<GuiaSalidaDetalle> items) {
        this.items = items;
    }

    // --- Métodos de ayuda para sincronizar la relación ---

    /**
     * Agrega un item (detalle) a la guía, estableciendo la relación bidireccional.
     * @param item El detalle del producto a agregar.
     */
    public void addItem(GuiaSalidaDetalle item) {
        items.add(item);
        item.setGuiaSalida(this);
    }

    /**
     * Remueve un item (detalle) de la guía.
     * @param item El detalle del producto a remover.
     */
    public void removeItem(GuiaSalidaDetalle item) {
        items.remove(item);
        item.setGuiaSalida(null);
    }
}

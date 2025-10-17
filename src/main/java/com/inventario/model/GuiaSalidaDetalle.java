package com.inventario.model;

import jakarta.persistence.*;

/**
 * Representa una línea de detalle dentro de una Guía de Salida.
 * Cada instancia de esta clase corresponde a un producto específico y la cantidad
 * que se está retirando del inventario como parte de una guía de salida.
 */
@Entity
@Table(name = "guias_salida_detalle")
public class GuiaSalidaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Relación muchos-a-uno con la Guía de Salida a la que pertenece este detalle.
     * Esta es la entidad "dueña" de la relación, por lo que contiene la llave foránea.
     * - @JoinColumn: Especifica la columna de la llave foránea (guia_salida_id).
     * - nullable = false: Un detalle siempre debe pertenecer a una guía.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guia_salida_id", nullable = false)
    private GuiaSalida guiaSalida;

    /**
     * Relación muchos-a-uno con el Producto que se está retirando.
     * - @JoinColumn: Especifica la columna de la llave foránea (producto_id).
     * - nullable = false: Un detalle siempre debe estar asociado a un producto.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private int cantidad;

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GuiaSalida getGuiaSalida() {
        return guiaSalida;
    }

    public void setGuiaSalida(GuiaSalida guiaSalida) {
        this.guiaSalida = guiaSalida;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

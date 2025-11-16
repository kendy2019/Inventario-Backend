package com.inventario.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "guias_ingreso")
public class GuiaIngreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="fecha_ingreso")
    private LocalDate fechaIngreso;

    private String notas;

    @OneToMany(mappedBy = "guiaIngreso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GuiaIngresoDetalle> detalles = new ArrayList<>();

    // getters / setters

    public void addDetalle(GuiaIngresoDetalle detalle){
        detalle.setGuiaIngreso(this);
        this.detalles.add(detalle);
    }

    // ... getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }
    public List<GuiaIngresoDetalle> getDetalles() { return detalles; }
    public void setDetalles(List<GuiaIngresoDetalle> detalles) { this.detalles = detalles; }
}

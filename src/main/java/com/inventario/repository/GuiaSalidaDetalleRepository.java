package com.inventario.repository;

import com.inventario.model.GuiaSalidaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GuiaSalidaDetalleRepository extends JpaRepository<GuiaSalidaDetalle, Long> {
}

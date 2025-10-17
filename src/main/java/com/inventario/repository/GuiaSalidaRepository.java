package com.inventario.repository;

import com.inventario.model.GuiaSalida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuiaSalidaRepository extends JpaRepository<GuiaSalida, Long> {
}

package com.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.model.Producto;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

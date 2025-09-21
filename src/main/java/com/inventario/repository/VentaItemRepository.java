package com.inventario.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.model.VentaItem;
@Repository
public interface VentaItemRepository extends JpaRepository<VentaItem, Long> {
}

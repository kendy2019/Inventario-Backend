package com.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.model.Producto;


import org.springframework.data.jpa.repository.Query;   
import org.springframework.data.repository.query.Param; 

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	List<Producto> findByNombreContainingIgnoreCase(String nombre);

	
	@Query("SELECT p FROM Producto p WHERE p.stock <= :umbral ORDER BY p.stock ASC")
   List<Producto> findProductosConBajoStock(@Param("umbral") Integer umbral);
  
}

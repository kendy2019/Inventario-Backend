package com.inventario.repository;

import com.inventario.dto.VentasPorDiaDTO;
import com.inventario.model.Venta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
	
	
	 // --- MÉTODOS PARA ESTADÍSTICAS ---
    @Query("SELECT COALESCE(SUM(v.total), 0) FROM Venta v")
    Double findTotalVentas();

    @Query("SELECT COUNT(v) FROM Venta v")
    Long countNumeroOrdenes();

    @Query("SELECT COUNT(DISTINCT v.cliente.id) FROM Venta v")
    Long countClientesActivos();

    @Query("SELECT COALESCE(AVG(v.total), 0) FROM Venta v")
    Double findTicketPromedio();

    // --- MÉTODO PARA GRÁFICO ---
    @Query("SELECT new com.inventario.dto.VentasPorDiaDTO(CAST(v.fecha AS java.time.LocalDate), SUM(v.total)) " +
           "FROM Venta v GROUP BY CAST(v.fecha AS java.time.LocalDate) ORDER BY CAST(v.fecha AS java.time.LocalDate) ASC")
    List<VentasPorDiaDTO> findVentasAgrupadasPorDia();
    
    // --- MÉTODO PARA VENTAS RECIENTES ---
    List<Venta> findTop5ByOrderByFechaDesc();
}

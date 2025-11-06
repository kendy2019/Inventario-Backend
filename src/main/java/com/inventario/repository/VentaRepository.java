package com.inventario.repository;

import com.inventario.dto.VentasPorDiaDTO; 
import com.inventario.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

   
    @Query("SELECT COALESCE(SUM(v.total), 0) FROM Venta v")
    Double findTotalVentasGlobal(); 

    @Query("SELECT COUNT(v) FROM Venta v")
    Long countNumeroOrdenesGlobal(); 

    @Query("SELECT COUNT(DISTINCT v.cliente.id) FROM Venta v")
    Long countClientesActivos();

    @Query("SELECT COALESCE(AVG(v.total), 0) FROM Venta v")
    Double findTicketPromedioGlobal(); 
    
    @Query("SELECT COALESCE(SUM(v.total), 0.0) FROM Venta v WHERE v.fecha >= :inicioMes AND v.fecha < :inicioMesSiguiente")
    Double findTotalVentasMes(@Param("inicioMes") Instant inicioMes, @Param("inicioMesSiguiente") Instant inicioMesSiguiente);

    
    @Query("SELECT new com.inventario.dto.VentasPorDiaDTO(CAST(v.fecha AS java.time.LocalDate), SUM(v.total)) " +
           "FROM Venta v " +
           "WHERE v.fecha >= :fechaInicio " + 
           "GROUP BY CAST(v.fecha AS java.time.LocalDate) ORDER BY CAST(v.fecha AS java.time.LocalDate) ASC")
    List<VentasPorDiaDTO> findVentasAgrupadasPorDia(@Param("fechaInicio") Instant fechaInicio); // Este es el método correcto

  
    List<Venta> findTop5ByOrderByFechaDesc();

   
}
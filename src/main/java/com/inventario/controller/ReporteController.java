package com.inventario.controller;

import com.inventario.dto.ReporteStatsDTO;
import com.inventario.dto.VentasPorDiaDTO;
import com.inventario.service.ReporteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/stats")
    public ResponseEntity<ReporteStatsDTO> getStats() {
        return ResponseEntity.ok(reporteService.getStats());
    }

    @GetMapping("/ventas-por-dia")
    public ResponseEntity<List<VentasPorDiaDTO>> getVentasPorDia() {
        return ResponseEntity.ok(reporteService.getVentasPorDia());
    }
}
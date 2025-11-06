package com.inventario.controller;

import com.inventario.dto.ProductoBajoStockDTO;
import com.inventario.dto.ReporteStatsDTO;
import com.inventario.dto.VentasPorDiaDTO;
import com.inventario.service.ProductoService;
import com.inventario.service.ReporteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
public class ReporteController {

	private final ReporteService reporteService;
	private final ProductoService productoService;

	public ReporteController(ReporteService reporteService, ProductoService productoService) {
		this.reporteService = reporteService;
		this.productoService = productoService;
	}

	@GetMapping("/stats")
	public ResponseEntity<ReporteStatsDTO> getStats() {
		return ResponseEntity.ok(reporteService.getStats());
	}

	@GetMapping("/ventas-por-dia")
	public ResponseEntity<List<VentasPorDiaDTO>> getVentasPorDia() {
		return ResponseEntity.ok(reporteService.getVentasPorDia());
	}

	@GetMapping("/bajo-stock")
	@PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
	public ResponseEntity<List<ProductoBajoStockDTO>> getProductosBajoStock(
			@RequestParam(defaultValue = "5") Integer umbral) {
		return ResponseEntity.ok(productoService.findLowStockProducts(umbral));
	}

}
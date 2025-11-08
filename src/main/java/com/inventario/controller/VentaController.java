package com.inventario.controller;

import com.inventario.dto.VentaRequestDTO;
import com.inventario.dto.VentaRecienteDTO;
import com.inventario.model.Venta;
import com.inventario.service.VentaService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
@CrossOrigin(origins = "*") // Permitir acceso desde tu frontend React
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    // 🔹 Crear nueva venta
    @PostMapping
    public ResponseEntity<?> crearVenta(@RequestBody VentaRequestDTO request) {
        try {
            Venta nuevaVenta = ventaService.crearVenta(request);
            return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado al procesar la venta: " + e.getMessage());
        }
    }

    // 🔹 Obtener las 5 ventas más recientes
    @GetMapping("/recientes")
    public ResponseEntity<List<VentaRecienteDTO>> getRecentSales() {
        return ResponseEntity.ok(ventaService.findRecentSales());
    }

    // 🔹 Total de ventas del mes actual
    @GetMapping("/total-mes")
    public ResponseEntity<Map<String, Double>> getTotalVentasMes() {
        Double totalMes = ventaService.obtenerTotalVentasMesActual();
        return ResponseEntity.ok(Collections.singletonMap("totalMes", totalMes));
    }


    // 🔹 Total global de ventas (todas las ventas)
    @GetMapping("/total-global")
    public ResponseEntity<Double> getTotalGlobal() {
        return ResponseEntity.ok(ventaService.obtenerTotalGlobal());
    }

    // 🔹 Número de clientes que han hecho al menos una compra
    @GetMapping("/clientes-activos")
    public ResponseEntity<Long> getClientesActivos() {
        return ResponseEntity.ok(ventaService.obtenerClientesActivos());
    }
}

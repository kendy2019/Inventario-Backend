package com.inventario.controller;

import com.inventario.dto.VentaRequestDTO;
import com.inventario.model.Venta;
import com.inventario.service.VentaService;

import jakarta.persistence.EntityNotFoundException;
import com.inventario.dto.VentaRecienteDTO;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
@PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }


    

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
    
    @GetMapping("/recientes")
    public ResponseEntity<List<VentaRecienteDTO>> getRecentSales() {
        return ResponseEntity.ok(ventaService.findRecentSales());
    }
}
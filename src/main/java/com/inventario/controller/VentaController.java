package com.inventario.controller;


import org.springframework.web.bind.annotation.*;

import com.inventario.model.Venta;
import com.inventario.service.VentaService;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public Venta registrar(@RequestBody Venta venta) {
        return ventaService.registrarVenta(venta);
    }

    @GetMapping
    public List<Venta> listar() {
        return ventaService.listarVentas();
    }

    @GetMapping("/{id}")
    public Venta buscar(@PathVariable Long id) {
        return ventaService.buscarPorId(id);
    }
}


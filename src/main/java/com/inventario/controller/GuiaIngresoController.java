package com.inventario.controller;

import com.inventario.dto.GuiaIngresoDTO;
import com.inventario.model.GuiaIngreso;
import com.inventario.service.GuiaIngresoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guias-ingreso")
public class GuiaIngresoController {

    private final GuiaIngresoService guiaIngresoService;

    public GuiaIngresoController(GuiaIngresoService guiaIngresoService) {
        this.guiaIngresoService = guiaIngresoService;
    }

    @PostMapping
    public ResponseEntity<?> crearGuiaIngreso(@RequestBody GuiaIngresoDTO dto) {
        try {
            GuiaIngreso creada = guiaIngresoService.procesarGuiaIngreso(dto);
            return ResponseEntity.status(201).body(creada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}

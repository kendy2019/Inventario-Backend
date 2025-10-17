package com.inventario.controller;

import com.inventario.dto.GuiaSalidaRequestDTO;
import com.inventario.model.GuiaSalida;
import com.inventario.service.GuiaSalidaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guias-salida")
public class GuiaSalidaController {

    private final GuiaSalidaService guiaSalidaService;

    public GuiaSalidaController(GuiaSalidaService guiaSalidaService) {
        this.guiaSalidaService = guiaSalidaService;
    }

    @PostMapping
    public ResponseEntity<?> crearGuiaSalida(@RequestBody GuiaSalidaRequestDTO requestDTO) {
        try {
            GuiaSalida nuevaGuia = guiaSalidaService.crearGuiaSalida(requestDTO);
            return new ResponseEntity<>(nuevaGuia, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            // Error si un producto no se encuentra
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            // Error si no hay stock suficiente
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Capturar cualquier otro error inesperado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado al procesar la solicitud.");
        }
    }
}

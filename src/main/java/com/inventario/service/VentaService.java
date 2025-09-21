package com.inventario.service;




import org.springframework.stereotype.Service;

import com.inventario.model.Venta;
import com.inventario.repository.VentaRepository;

import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public Venta registrarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    public Venta buscarPorId(Long id) {
        return ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }
}

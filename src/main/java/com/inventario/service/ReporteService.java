package com.inventario.service;

import com.inventario.dto.ReporteStatsDTO;
import com.inventario.dto.VentasPorDiaDTO;
import com.inventario.repository.VentaRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ReporteService {

    private final VentaRepository ventaRepository;

    public ReporteService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public ReporteStatsDTO getStats() {
        ReporteStatsDTO stats = new ReporteStatsDTO();
        stats.setTotalVentas(BigDecimal.valueOf(ventaRepository.findTotalVentas()));
        stats.setNumeroOrdenes(ventaRepository.countNumeroOrdenes());
        stats.setClientesActivos(ventaRepository.countClientesActivos());
        stats.setTicketPromedio(BigDecimal.valueOf(ventaRepository.findTicketPromedio()));
        return stats;
    }

    public List<VentasPorDiaDTO> getVentasPorDia() {
        return ventaRepository.findVentasAgrupadasPorDia();
    }
}

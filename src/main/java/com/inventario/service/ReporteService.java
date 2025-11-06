package com.inventario.service;

import com.inventario.dto.ReporteStatsDTO;
import com.inventario.dto.VentasPorDiaDTO;
import com.inventario.repository.ProductoRepository;
import com.inventario.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class ReporteService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;

    public ReporteService(VentaRepository ventaRepository, ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
    }

    public ReporteStatsDTO getStats() {
        ReporteStatsDTO stats = new ReporteStatsDTO();

        // --- Cálculos existentes (mantener) ---
        stats.setTotalVentas(BigDecimal.valueOf(ventaRepository.findTotalVentasGlobal()));
        stats.setNumeroOrdenes(ventaRepository.countNumeroOrdenesGlobal());
        stats.setClientesActivos(ventaRepository.countClientesActivos());
        stats.setTicketPromedio(BigDecimal.valueOf(ventaRepository.findTicketPromedioGlobal()));
        stats.setTotalProductos(productoRepository.count());

        // --- Calcular inicio y fin del mes actual usando Instant ---
        YearMonth currentMonth = YearMonth.now();
        Instant inicioMes = currentMonth.atDay(1).atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant inicioMesSiguiente = currentMonth.plusMonths(1).atDay(1).atStartOfDay().toInstant(ZoneOffset.UTC);

        stats.setVentasMesActual(BigDecimal.valueOf(
            ventaRepository.findTotalVentasMes(inicioMes, inicioMesSiguiente)
        ));

        return stats;
    }

    public List<VentasPorDiaDTO> getVentasPorDia() {
        // Últimos 30 días en Instant
        Instant hace30Dias = Instant.now().minus(30, java.time.temporal.ChronoUnit.DAYS);
        return ventaRepository.findVentasAgrupadasPorDia(hace30Dias);
    }
}

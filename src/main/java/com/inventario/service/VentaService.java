package com.inventario.service;

import com.inventario.dto.VentaRequestDTO;
import com.inventario.dto.VentaItemDTO;
import com.inventario.dto.VentaRecienteDTO;
import com.inventario.model.Producto;
import com.inventario.model.Venta;
import com.inventario.model.VentaDetalle;
import com.inventario.repository.ProductoRepository;
import com.inventario.repository.VentaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;

    public VentaService(VentaRepository ventaRepository, ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
    }

    /**
     * 🔹 Retorna las últimas 5 ventas registradas.
     */
    public List<VentaRecienteDTO> findRecentSales() {
        return ventaRepository.findTop5ByOrderByFechaDesc().stream()
                .map(VentaRecienteDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * 🔹 Crea una nueva venta y actualiza el stock de los productos.
     */
    @Transactional
    public Venta crearVenta(VentaRequestDTO request) {
        Venta venta = new Venta();

        // Mapear datos de cotización y venta
        venta.setClienteNombre(request.getCotizacion().getClienteNombre());
        venta.setClienteTelefono(request.getCotizacion().getClienteTelefono());
        venta.setTotal(request.getTotal());
        venta.setFecha(request.getFecha());
        venta.setRegistradoPor(request.getRegistradoPor());

        // Crear los detalles de la venta
        for (VentaItemDTO itemDTO : request.getItems()) {
            Producto producto = productoRepository.findById(itemDTO.getProductoId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado: " + itemDTO.getProductoId()));

            if (producto.getStock() < itemDTO.getCantidad()) {
                throw new IllegalStateException("Stock insuficiente para: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - itemDTO.getCantidad());
            productoRepository.save(producto);

            VentaDetalle detalle = new VentaDetalle();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setCantidad(itemDTO.getCantidad());
            detalle.setPrecio(itemDTO.getPrecio());
            venta.getItems().add(detalle);
        }

        return ventaRepository.save(venta);
    }

    public Double obtenerTotalVentasMesActual() {
        // Obtener primer día del mes actual
        LocalDate primerDiaMes = LocalDate.now().withDayOfMonth(1);
        Instant inicioMes = primerDiaMes.atStartOfDay(ZoneId.systemDefault()).toInstant();

        // Primer día del siguiente mes
        Instant inicioMesSiguiente = primerDiaMes.plusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant();

        Double totalMes = ventaRepository.findTotalVentasMes(inicioMes, inicioMesSiguiente);
        return totalMes != null ? totalMes : 0.0;
    }

    /**
     * 🔹 Retorna el número de clientes activos (que han comprado algo).
     */
    public Long obtenerClientesActivos() {
        return ventaRepository.countClientesActivos();
    }

    /**
     * 🔹 Retorna el total global de ventas (todas las ventas).
     */
    public Double obtenerTotalGlobal() {
        return ventaRepository.findTotalVentasGlobal();
    }
}

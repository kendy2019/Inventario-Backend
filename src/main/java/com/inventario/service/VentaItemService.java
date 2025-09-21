package com.inventario.service;


import org.springframework.stereotype.Service;

import com.inventario.model.VentaItem;
import com.inventario.repository.VentaItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VentaItemService {
 private final VentaItemRepository ventaItemRepository;

 public VentaItemService(VentaItemRepository ventaItemRepository) {
     this.ventaItemRepository = ventaItemRepository;
 }

 public List<VentaItem> listarItems() {
     return ventaItemRepository.findAll();
 }

 public Optional<VentaItem> buscarPorId(Long id) {
     return ventaItemRepository.findById(id);
 }

 public VentaItem guardarItem(VentaItem ventaItem) {
     return ventaItemRepository.save(ventaItem);
 }

 public void eliminarItem(Long id) {
     ventaItemRepository.deleteById(id);
 }
}

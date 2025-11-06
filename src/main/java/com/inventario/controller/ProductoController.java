	package com.inventario.controller;
	
	import com.inventario.dto.ProductoBajoStockDTO;
import com.inventario.model.Producto;
	import com.inventario.service.ProductoService;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
	
	import java.util.List;
	import java.util.Optional;
	
	@RestController
	@RequestMapping("/api/productos")
	public class ProductoController {
	
	    private final ProductoService productoService;
	
	    public ProductoController(ProductoService productoService) {
	        this.productoService = productoService;
	    }
	
	    @PostMapping
	    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
	        Producto nuevoProducto = productoService.guardar(producto);
	        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
	    }
	
	    @GetMapping
	    public List<Producto> listar() {
	        return productoService.listarTodos();
	    }
	
	    @GetMapping("/{id}")
	    public ResponseEntity<Producto> buscar(@PathVariable Long id) {
	        // Usando Optional para manejar el caso de que no se encuentre
	        return productoService.buscarPorId(id)
	                .map(producto -> ResponseEntity.ok(producto))
	                .orElse(ResponseEntity.notFound().build());
	    }
	    @GetMapping("/buscar")
	    public ResponseEntity<List<Producto>> buscarPorNombre(@RequestParam String nombre) {
	        List<Producto> productos = productoService.buscarPorNombre(nombre);
	        return ResponseEntity.ok(productos);
	    }
	    @GetMapping("/bajo-stock")
	    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')") // O solo ADMIN si prefieres
	    public ResponseEntity<List<ProductoBajoStockDTO>> getProductosBajoStock(
	            @RequestParam(defaultValue = "5") Integer umbral) { // Puedes ajustar el umbral por defecto
	        // Asegúrate de que tu ProductoService tenga el método findLowStockProducts
	        return ResponseEntity.ok(productoService.findLowStockProducts(umbral));
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
	        try {
	            Producto productoActualizado = productoService.actualizar(id, producto);
	            return ResponseEntity.ok(productoActualizado);
	        } catch (RuntimeException e) { // Asumiendo que el servicio lanza una excepción si no lo encuentra
	            return ResponseEntity.notFound().build();
	        }
	    }
	
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
	        try {
	            productoService.eliminar(id);
	            return ResponseEntity.noContent().build();
	        } catch (RuntimeException e) {
	             return ResponseEntity.notFound().build();
	        }
	    }
	}

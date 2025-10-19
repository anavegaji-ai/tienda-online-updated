
package com.example.tienda.controller;

import com.example.tienda.dto.ProductoCreateDTO;
import com.example.tienda.entity.Producto;
import com.example.tienda.service.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService productoService;
    public ProductoController(ProductoService productoService){ this.productoService = productoService; }

    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody ProductoCreateDTO dto){
        Producto p = productoService.createProducto(dto);
        return ResponseEntity.ok(p);
    }

    @GetMapping
    public ResponseEntity<Page<Producto>> list(@RequestParam(required=false) String categoria,
                                              @RequestParam(defaultValue="0") int page,
                                              @RequestParam(defaultValue="10") int size){
        if(categoria==null) return ResponseEntity.ok(productoService.findByCategoria("", PageRequest.of(page,size)));
        return ResponseEntity.ok(productoService.findByCategoria(categoria, PageRequest.of(page,size)));
    }
}

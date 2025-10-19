
package com.example.tienda.service;

import com.example.tienda.dto.ProductoCreateDTO;
import com.example.tienda.entity.Categoria;
import com.example.tienda.entity.Producto;
import com.example.tienda.repository.CategoriaRepository;
import com.example.tienda.repository.ProductoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository){
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public Producto createProducto(ProductoCreateDTO dto){
        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());
        if(dto.getCategorias()!=null){
            Set<Categoria> cats = new HashSet<>();
            for(String nombre : dto.getCategorias()){
                Categoria c = categoriaRepository.findByNombre(nombre).orElseGet(()->{ Categoria nc = new Categoria(); nc.setNombre(nombre); return categoriaRepository.save(nc); });
                cats.add(c);
            }
            p.getCategorias().addAll(cats);
        }
        return productoRepository.save(p);
    }

    public Page<Producto> findByCategoria(String categoria, Pageable pageable){
        return productoRepository.findByCategoriasNombre(categoria, pageable);
    }
}

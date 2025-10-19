
package com.example.tienda.repository;

import com.example.tienda.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Page<Producto> findByCategoriasNombre(String nombre, Pageable pageable);
}

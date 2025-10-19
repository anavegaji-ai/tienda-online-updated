
package com.example.tienda.repository;

import com.example.tienda.entity.Pedido;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @EntityGraph(attributePaths = {"items", "items.producto"})
    Optional<Pedido> findWithItemsById(Long id);
}

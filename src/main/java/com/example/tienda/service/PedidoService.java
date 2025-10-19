
package com.example.tienda.service;

import com.example.tienda.dto.ItemPedidoCreateDTO;
import com.example.tienda.dto.PedidoCreateDTO;
import com.example.tienda.entity.ItemPedido;
import com.example.tienda.entity.Pedido;
import com.example.tienda.entity.Producto;
import com.example.tienda.entity.Cliente;
import com.example.tienda.exception.BadRequestException;
import com.example.tienda.exception.NotFoundException;
import com.example.tienda.repository.PedidoRepository;
import com.example.tienda.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProductoRepository productoRepository){
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public Pedido createPedido(Cliente cliente, PedidoCreateDTO dto){
        if(dto == null || dto.getItems() == null || dto.getItems().isEmpty()) throw new BadRequestException("Pedido debe tener al menos un item");

        // Validate duplicates
        Set<Long> seen = new HashSet<>();
        for(ItemPedidoCreateDTO it : dto.getItems()){
            if(!seen.add(it.getProductoId())) throw new BadRequestException("El mismo producto está duplicado en el pedido.");
        }

        Pedido pedido = new Pedido();
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado("NUEVO");
        pedido.setTotal(BigDecimal.ZERO);
        pedido.setCliente(cliente);

        BigDecimal total = BigDecimal.ZERO;
        for(ItemPedidoCreateDTO itdto : dto.getItems()){
            Producto p = productoRepository.findById(itdto.getProductoId()).orElseThrow(()-> new NotFoundException("Producto no encontrado: " + itdto.getProductoId()));
            if(p.getStock() < itdto.getCantidad()) throw new BadRequestException("Stock insuficiente para producto: " + p.getNombre());
            // decrement stock
            p.setStock(p.getStock() - itdto.getCantidad());
            productoRepository.save(p);

            ItemPedido ip = new ItemPedido();
            ip.setProducto(p);
            ip.setCantidad(itdto.getCantidad());
            ip.setPrecioUnitario(p.getPrecio());
            pedido.addItem(ip);

            total = total.add(p.getPrecio().multiply(new BigDecimal(itdto.getCantidad())));
        }
        pedido.setTotal(total);
        return pedidoRepository.save(pedido);
    }
}

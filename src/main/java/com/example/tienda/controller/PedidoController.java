
package com.example.tienda.controller;

import com.example.tienda.dto.PedidoCreateDTO;
import com.example.tienda.dto.PedidoDTO;
import com.example.tienda.dto.ItemPedidoDTO;
import com.example.tienda.entity.Pedido;
import com.example.tienda.entity.ItemPedido;
import com.example.tienda.repository.ClienteRepository;
import com.example.tienda.service.PedidoService;
import com.example.tienda.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes/{clienteId}/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;
    private final ClienteRepository clienteRepository;

    public PedidoController(PedidoService pedidoService, ClienteRepository clienteRepository){
        this.pedidoService = pedidoService;
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public ResponseEntity<?> create(@PathVariable Long clienteId, @RequestBody PedidoCreateDTO dto){
        var cliente = clienteRepository.findById(clienteId).orElseThrow(()-> new NotFoundException("Cliente no encontrado"));
        Pedido p = pedidoService.createPedido(cliente, dto);
        return ResponseEntity.ok(toDTO(p));
    }

    private PedidoDTO toDTO(Pedido p){
        PedidoDTO dto = new PedidoDTO();
        dto.setId(p.getId());
        dto.setFecha(p.getFecha());
        dto.setEstado(p.getEstado());
        dto.setTotal(p.getTotal());
        var items = p.getItems().stream().map(this::toItemDto).collect(Collectors.toList());
        dto.setItems(items);
        return dto;
    }

    private ItemPedidoDTO toItemDto(ItemPedido it){
        ItemPedidoDTO idto = new ItemPedidoDTO();
        idto.setProductoId(it.getProducto().getId());
        idto.setNombreProducto(it.getProducto().getNombre());
        idto.setCantidad(it.getCantidad());
        idto.setPrecioUnitario(it.getPrecioUnitario());
        return idto;
    }
}


package com.example.tienda.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTO {
    private Long id;
    private LocalDateTime fecha;
    private String estado;
    private BigDecimal total;
    private List<ItemPedidoDTO> items;

    // getters/setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public LocalDateTime getFecha(){return fecha;}
    public void setFecha(LocalDateTime fecha){this.fecha = fecha;}
    public String getEstado(){return estado;}
    public void setEstado(String estado){this.estado = estado;}
    public BigDecimal getTotal(){return total;}
    public void setTotal(BigDecimal total){this.total = total;}
    public List<ItemPedidoDTO> getItems(){return items;}
    public void setItems(List<ItemPedidoDTO> items){this.items = items;}
}

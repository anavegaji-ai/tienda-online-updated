
package com.example.tienda.dto;

import java.util.List;

public class PedidoCreateDTO {
    private java.util.List<ItemPedidoCreateDTO> items;

    public java.util.List<ItemPedidoCreateDTO> getItems(){return items;}
    public void setItems(java.util.List<ItemPedidoCreateDTO> items){this.items = items;}
}

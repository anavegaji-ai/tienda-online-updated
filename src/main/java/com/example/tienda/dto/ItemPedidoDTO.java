
package com.example.tienda.dto;

import java.math.BigDecimal;

public class ItemPedidoDTO {
    private Long productoId;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;

    // getters/setters
    public Long getProductoId(){return productoId;}
    public void setProductoId(Long productoId){this.productoId = productoId;}
    public String getNombreProducto(){return nombreProducto;}
    public void setNombreProducto(String nombreProducto){this.nombreProducto = nombreProducto;}
    public Integer getCantidad(){return cantidad;}
    public void setCantidad(Integer cantidad){this.cantidad = cantidad;}
    public BigDecimal getPrecioUnitario(){return precioUnitario;}
    public void setPrecioUnitario(java.math.BigDecimal precioUnitario){this.precioUnitario = precioUnitario;}
}

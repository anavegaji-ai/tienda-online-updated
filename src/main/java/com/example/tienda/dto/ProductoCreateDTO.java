
package com.example.tienda.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductoCreateDTO {
    private String nombre;
    private BigDecimal precio;
    private Integer stock;
    private List<String> categorias;

    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public BigDecimal getPrecio(){return precio;}
    public void setPrecio(BigDecimal precio){this.precio = precio;}
    public Integer getStock(){return stock;}
    public void setStock(Integer stock){this.stock = stock;}
    public List<String> getCategorias(){return categorias;}
    public void setCategorias(List<String> categorias){this.categorias = categorias;}
}

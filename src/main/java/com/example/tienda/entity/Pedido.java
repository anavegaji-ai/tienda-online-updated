
package com.example.tienda.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    @Column(nullable=false)
    private String estado; // NUEVO, PAGADO, ENVIADO, CANCELADO

    @Column(nullable=false)
    private BigDecimal total = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> items = new ArrayList<>();

    public void addItem(ItemPedido it){ items.add(it); it.setPedido(this); }
    public void removeItem(ItemPedido it){ items.remove(it); it.setPedido(null); }

    // getters/setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public LocalDateTime getFecha(){return fecha;}
    public void setFecha(LocalDateTime fecha){this.fecha = fecha;}
    public String getEstado(){return estado;}
    public void setEstado(String estado){this.estado = estado;}
    public BigDecimal getTotal(){return total;}
    public void setTotal(BigDecimal total){this.total = total;}
    public Cliente getCliente(){return cliente;}
    public void setCliente(Cliente cliente){this.cliente = cliente;}
    public List<ItemPedido> getItems(){return items;}
}

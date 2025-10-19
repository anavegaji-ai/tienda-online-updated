
package com.example.tienda.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nombre;

    @Column(nullable=false, unique=true)
    private String email;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Direccion direccion;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Pedido> pedidos = new ArrayList<>();

    public void setDireccion(Direccion d){ this.direccion = d; if (d != null) d.setCliente(this); }
    public void addPedido(Pedido p){ this.pedidos.add(p); p.setCliente(this); }
    public void removePedido(Pedido p){ this.pedidos.remove(p); p.setCliente(null); }

    // getters and setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
    public Direccion getDireccion(){return direccion;}
    public List<Pedido> getPedidos(){return pedidos;}
}

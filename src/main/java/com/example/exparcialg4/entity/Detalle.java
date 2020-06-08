package com.example.exparcialg4.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle")
public class Detalle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetalle")
    private int id;

    @Column(nullable = false)
    private int cantidad;

    @Digits(integer = 10, fraction = 2)
    private BigDecimal preciototal;

    @ManyToOne
    @JoinColumn(name = "pedidos_idpedidos")
    private Pedidos pedidos;


    @ManyToOne
    @JoinColumn(name = "productos_idproductos")
    private Productos productos;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPreciototal() {
        return preciototal;
    }

    public void setPreciototal(BigDecimal preciototal) {
        this.preciototal = preciototal;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }
}

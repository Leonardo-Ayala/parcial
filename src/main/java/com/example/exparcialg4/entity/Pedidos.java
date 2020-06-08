package com.example.exparcialg4.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Pedidos")
public class Pedidos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpedidos")
    private int idpedidos;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "usuarios_idusuarios")
    private Usuarios usuarios;

    public int getIdpedidos() {
        return idpedidos;
    }

    public void setIdpedidos(int idpedidos) {
        this.idpedidos = idpedidos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public com.example.exparcialg4.entity.Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(com.example.exparcialg4.entity.Usuarios usuarios) {
        this.usuarios = usuarios;
    }
}

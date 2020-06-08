package com.example.exparcialg4.entity;

import javax.persistence.*;

@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcarrito")
    private int idcarrito;

    public int getIdcarrito() {
        return idcarrito;
    }

    public void setIdcarrito(int idcarrito) {
        this.idcarrito = idcarrito;
    }

    public com.example.exparcialg4.entity.Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(com.example.exparcialg4.entity.Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    @OneToOne
    @JoinColumn(name = "usuarios_idusuarios")
    private Usuarios usuarios;
}

package com.example.exparcialg4.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Pedidos")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpedidos")
    private int idpedidos;

    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "usuarios_idusuarios")
    private Usuarios Usuarios;

}

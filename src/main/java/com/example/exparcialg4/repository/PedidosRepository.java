package com.example.exparcialg4.repository;

import com.example.exparcialg4.entity.Detalle;
import com.example.exparcialg4.entity.Pedidos;
import com.example.exparcialg4.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosRepository extends JpaRepository<Pedidos,Integer> {

    public List<Pedidos> findByUsuarios(Usuarios usuarios);
}

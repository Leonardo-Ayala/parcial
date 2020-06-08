package com.example.exparcialg4.repository;

import com.example.exparcialg4.entity.Carrito;
import com.example.exparcialg4.entity.Detalle;
import com.example.exparcialg4.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle,Integer> {

}

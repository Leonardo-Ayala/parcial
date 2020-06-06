package com.example.exparcialg4.repository;

import com.example.exparcialg4.entity.productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends JpaRepository<productos,Integer> {
}

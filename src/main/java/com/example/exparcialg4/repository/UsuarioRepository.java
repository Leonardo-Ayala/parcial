package com.example.exparcialg4.repository;


import com.example.exparcialg4.entity.usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<usuarios,Integer> {
}

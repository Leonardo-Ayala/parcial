package com.example.exparcialg4.repository;


import com.example.exparcialg4.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios,Integer> {
    public Usuarios findByEmail(String email);
}

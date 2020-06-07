package com.example.exparcialg4.controller.Administrador;

import com.example.exparcialg4.entity.Usuarios;
import com.example.exparcialg4.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UsuarioRepository usuarioRepository;


    @GetMapping(value = {"/listaGestores", ""})
    public String listaGestores(Model model){
        model.addAttribute("listaGestores",usuarioRepository.findAll());
        //model.addAttribute("listaGestores",usuarioRepository.findByRol("gestor"));
        return "Administrador/listaGestores";
    }

    @GetMapping("/nuevoGestor")
    public String nuevoGestor(@ModelAttribute("usuarios") Usuarios usuarios){
        return "Administrador/nuevoGestor";
    }

    @PostMapping("/guardarGestor")
    public String guardarGestor(@ModelAttribute("usuarios") Usuarios usuarios, Model model, RedirectAttributes attr){

        if(usuarios.getIdusuarios() == 0){
            usuarios.setRol("gestor");
            usuarios.setPwd("12345678");
            usuarios.setActivo(1);
            usuarioRepository.save(usuarios);
            attr.addFlashAttribute("msg","Gestor creado exitosamente");
            return "redirect:/admin/listaGestores";
        } else if (usuarios.getIdusuarios() != 0) {
           //   usuarios.setRol("gestor");
           // usuarios.setPwd("12345678");
          //  usuarios.setActivo(1);
            usuarioRepository.save(usuarios);
            attr.addFlashAttribute("msg", "Gestor actualizado exitosamente");
            return "redirect:/admin/listaGestores";
        } else {
            model.addAttribute("errorGestor", "Los datos ingresados ya existen");
            return "Administrador/nuevoGestor";
        }

    }

    @GetMapping("/editarGestor")
    public String editarGestor(@ModelAttribute("usuarios") Usuarios usuarios, Model model,
                               @RequestParam("idusuarios") int idusuarios ){

        Optional<Usuarios> optUsuarios   = usuarioRepository.findById(idusuarios);
        if (optUsuarios.isPresent()){
            usuarios = optUsuarios.get();
            model.addAttribute("usuarios",usuarios);
            return"Administrador/nuevoGestor";
        } else {
            return "redirect:/admin/listaGestores";
        }

    }


    @GetMapping("borrarGestor")
    public String borrarGestor(Model model,@RequestParam("idusuarios") int idusuarios,
                               RedirectAttributes attr) {

        Optional<Usuarios> optUsuarios = usuarioRepository.findById(idusuarios);
        if (optUsuarios.isPresent()) {
            usuarioRepository.deleteById(idusuarios);
            attr.addFlashAttribute("msg", "Gestor borrado correctamente");
        }
        return "redirect:/admin/listaGestores";
    }

}


package com.example.exparcialg4.controller.Registrado;


import com.example.exparcialg4.entity.Usuarios;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @GetMapping(value={"","/"})
    public String listarCarrito(Model model, HttpSession session){
        Usuarios usuarios = (Usuarios) session.getAttribute("usuario");
        int id=usuarios.getIdusuarios();

    }

}

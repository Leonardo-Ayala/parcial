package com.example.exparcialg4.controller.Registrado;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/carrito")
public class CarritoController {

    @GetMapping(value={"","/"})
    public String listarcarrito(){
        return "Registrado/Carrito";
    }
}

package com.example.exparcialg4.controller.Gestor;

import com.example.exparcialg4.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductosController {
    @Autowired
    ProductosRepository productosRepository;

    @GetMapping(value={"","/"})
    public String listarProductos(Model model){
        model.addAttribute("listaProductos",productosRepository.findAll());
        return "Gestor/productosLista";
    }



}

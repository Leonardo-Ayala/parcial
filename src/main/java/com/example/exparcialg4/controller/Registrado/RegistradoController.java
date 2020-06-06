package com.example.exparcialg4.controller.Registrado;


import com.example.exparcialg4.entity.Productos;
import com.example.exparcialg4.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class RegistradoController {

    @Autowired
    ProductosRepository productosRepository;
    @GetMapping(value={"","/"})
    public String listarProductos(Model model){
        model.addAttribute("listaProductos",productosRepository.findAll());
        return "Registrado/listarProductos";
    }

    @GetMapping(value={"/verMas"})
    public String verMasProductos(Model model,@RequestParam("id") int id){
        Optional<Productos> opt = productosRepository.findById(id);
        if(opt.isPresent()){
            model.addAttribute("productos", opt.get());
            return "Registrado/verMas";
        }else{
            return "redirect:/";
        }
    }
}

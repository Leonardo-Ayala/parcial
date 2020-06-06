package com.example.exparcialg4.controller.Gestor;

import com.example.exparcialg4.entity.Productos;
import com.example.exparcialg4.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

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

    @GetMapping(value="/nuevo")
    public String nuevoProducto(){
        return "Gestor/nuevoProducto";
    }

    @PostMapping(value = "/guardar")
    public String guardarProducto(Productos productos){
        productosRepository.save(productos);
        return "redirect:/productos";
    }

    @GetMapping(value = "/editar")
    public String editarProducto(Model model,@RequestParam("id") int id){
        Optional<Productos> opt = productosRepository.findById(id);
        if(opt.isPresent()){
            model.addAttribute("productos", opt.get());
            return "Gestor/editarProducto";
        }else{
            return "redirect:/productos";
        }
    }

    @GetMapping(value ="eliminar")
    public String eliminarProducto(@RequestParam("id") int id){
        Optional<Productos> opt = productosRepository.findById(id);
        if(opt.isPresent()) {
            productosRepository.deleteById(id);
        }
        return "redirect:/productos";
    }

}

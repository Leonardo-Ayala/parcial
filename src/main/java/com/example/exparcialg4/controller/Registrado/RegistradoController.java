package com.example.exparcialg4.controller.Registrado;


import com.example.exparcialg4.entity.Detalle;
import com.example.exparcialg4.entity.Productos;
import com.example.exparcialg4.entity.Usuarios;
import com.example.exparcialg4.repository.PedidosRepository;
import com.example.exparcialg4.repository.ProductosRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class RegistradoController {

    @Autowired
    ProductosRepository productosRepository;

    @Autowired
    PedidosRepository pedidosRepository;

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

    @GetMapping(value = "/FormCarrito")
    public String CantidaddeProductos(Model model,@RequestParam("id") int id) {
        Optional<Productos> opt = productosRepository.findById(id);
        if(opt.isPresent()){
            model.addAttribute("productos", opt.get());
            return "Registrado/FormCarrito";
        }else{
            return "redirect:/";
        }
    }

    @PostMapping(value = "/agregarAlCarrito")
    public String agregaralcarrito(HttpSession session, @RequestParam("idproductos") int id, @RequestParam("cantidad") int cantidad){

        Optional<Productos> opt = productosRepository.findById(id);
        if(opt.isPresent()){
            Detalle detalle  = new Detalle();
            detalle.setCantidad(cantidad);
            Productos productos = opt.get();
            detalle.setProductos(productos);
            BigDecimal p = productos.getPrecio();
            detalle.setPreciototal(p.multiply(BigDecimal.valueOf(cantidad)));
            ArrayList<Detalle> listadetalle= (ArrayList<Detalle>) session.getAttribute("listadetalle");
            listadetalle.add(detalle);
            BigDecimal PrecioTotal = BigDecimal.valueOf(0);
            for (Detalle d:listadetalle){
                PrecioTotal = PrecioTotal.add(d.getPreciototal());
            }
            session.setAttribute("listadetalle",listadetalle);
            session.setAttribute("precioTotal",PrecioTotal);
        }
        return "Registrado/Carrito";
    }

    @GetMapping(value = "/pedidos")
    public String ListarPedidos(Model model,HttpSession session) {
        Usuarios usuarios = (Usuarios) session.getAttribute("usuario");
        model.addAttribute("listaPedidos",pedidosRepository.findByUsuarios(usuarios));
        return "Registrado/Pedidos";
    }

    @GetMapping(value = "/Pagar")
    public String vistapagar(){
        return "Registrado/vistaPagar";
    }

    @PostMapping(value = "/ConfirmarPago")
    public String confirmarpago(HttpSession session, @RequestParam("tarjeta") Long tarjeta, RedirectAttributes attr){
        String dig = Long.toString(tarjeta);

        if(dig.length() == 16){

           int suma = 0;
           int[] digitos = new int[16];
        for(int i = 0;i <16;i++){
           digitos[i]= (int) (tarjeta%10);
            if(i%2==1){
                digitos[i]=digitos[i]*2;
            }
            if(digitos[i]>9){
                digitos[i]=digitos[i]-9;
            }
            tarjeta=tarjeta/10;
            if(i != 0){
               suma = suma +digitos[i];
            }
        }
        int vericador =(10-(suma%10))%10;
        System.out.println(vericador);
        System.out.println(digitos[0]);
            if(vericador == digitos[0]){
                //guardar en Base de datos y vaciar carrito
                ArrayList<Detalle> listadetalle = new ArrayList<>() ;
                // se sobre escribe el carrito
                session.setAttribute("listadetalle",listadetalle);
                BigDecimal PrecioTotal = BigDecimal.valueOf(0);
                session.setAttribute("precioTotal",PrecioTotal);

                return "redirect:/";
            }
            attr.addFlashAttribute("msg", "Tarjeta Invalida");
            return "redirect:/Pagar";
        }else{
            attr.addFlashAttribute("msg", "Tarjeta debe tener 16 digitos");
            return "redirect:/Pagar";
        }
    }

}

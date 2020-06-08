package com.example.exparcialg4.controller.Login;

import com.example.exparcialg4.entity.Detalle;
import com.example.exparcialg4.entity.Usuarios;
import com.example.exparcialg4.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;

@Controller

public class loginController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(value = "/loginForm")
    public String loginForm(){
        return "Login/loginForm";
    }


    @GetMapping(value = "/redirectByRole")
    public String rediredirectByRole(Authentication authentication, HttpSession session){
        String rol = "";
        for (GrantedAuthority role : authentication.getAuthorities()) {
            rol = role.getAuthority();
            break;
        }

        ArrayList<Detalle> listadetalle = new ArrayList<>() ;
        String username = authentication.getName();
        Usuarios usuario = usuarioRepository.findByEmail(username);
        session.setAttribute("usuario",usuario);
        session.setAttribute("listadetalle",listadetalle);
        BigDecimal PrecioTotal = BigDecimal.valueOf(0);
        session.setAttribute("precioTotal",PrecioTotal);


        if (rol.equals("admin")) {
            return "redirect:/usuarios/";
        } else if(rol.equals("gestor")){
            return "redirect:/productos/";
        }else{
            return "redirect:/";
        }

    }
}

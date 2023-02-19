/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.eggNews.controladores;

import com.egg.eggNews.excepciones.MiException;
import com.egg.eggNews.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author cesar
 */
@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    @GetMapping
    public String index(){
        return "index.html";
        
    }
    @GetMapping("/registrar")
    public String registrar(){
        return "registro.html";
    }
    @PostMapping("/registros")
    
    public String registros(@RequestParam String nombre, @RequestParam String email, @RequestParam String password,
            @RequestParam String password2, ModelMap modelo) throws Exception{
        
        try {
            
            usuarioServicio.registrar(nombre, email, password, password2);
            modelo.put("exito", "Usuario registrado correctamente");
            
            return "index.html";
        } catch (MiException ex) {
            
            modelo.put("error", ex.getMessage());
            return "registro.html";
        }
        
    }
    
    @GetMapping("/login")
    public String logear(@RequestParam (required = false) String error, ModelMap modelo){
        
        if (error != null) {
            modelo.put("error", "Usuario o Contraseña incorrecta");
            
        }
        return "login.html";
    }
    @GetMapping("/inicio")
    public String inicio(){
        
        return "inicio.html";
    }
}

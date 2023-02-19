/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.eggNews.controladores;

import com.egg.eggNews.entidades.Noticia;
import com.egg.eggNews.excepciones.MiException;
import com.egg.eggNews.servicios.NoticiaServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author cesar
 */

@Controller
@RequestMapping("/crear") //localhost:8080/crear
public class CrearControlador {
    
    @Autowired
    private NoticiaServicio noticiaServ;
    @GetMapping("/registrar")
    public String registrar(){
        return "crearNoticia.html";
    }
    @PostMapping("/registro")
    public String registro(@RequestParam (required=false) Long id,@RequestParam String titulo,@RequestParam String cuerpo,
            @RequestParam String foto, ModelMap modelo){
        try {
            System.out.println("Nombre: " +titulo);
            noticiaServ.crearNoticia(id, titulo, cuerpo, foto);
           
            modelo.put("exito", "La noticia se cargo con exito");
            
        } catch (MiException ex) {
        modelo.put("error", ex.getMessage());
        return "crearNoticia.html";
        }
        return "crearNoticia.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List<Noticia> noticias = noticiaServ.listarNoticias();
        
        modelo.addAttribute("noticias", noticias);
        
        return "listadoNoticias.html";
    }
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, ModelMap modelo){
        modelo.put("noticia", noticiaServ.getOne(id));
        
        return "modNoticia.html";
        
    }
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, String titulo,@RequestParam String cuerpo,
            @RequestParam String foto, ModelMap modelo){
        
        try{
        noticiaServ.modificarNoticia(id, titulo, cuerpo, foto);
        modelo.put("exito", "La noticia se modifico con exito");
        return "redirect:../lista";
        } catch (MiException ex){
        modelo.put("error", ex.getMessage());
        
        return "modNoticia.html";
        }
    
        
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.eggNews.servicios;
import com.egg.eggNews.entidades.Noticia;
import com.egg.eggNews.excepciones.MiException;
import com.egg.eggNews.repositorios.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author cesar
 */
@Service
public class NoticiaServicio {
    
    @Autowired
    private NoticiaRepositorio noticiaRepositorio;
    
    @Transactional
    public void crearNoticia(Long id, String titulo, String cuerpo, String foto) throws MiException{
        
       validar(id);
        Noticia nuevaNoticia = new Noticia();
        
        nuevaNoticia.setId(id);
        nuevaNoticia.setTitulo(titulo);
        nuevaNoticia.setCuerpo(cuerpo);
        nuevaNoticia.setFoto(foto);
        noticiaRepositorio.save(nuevaNoticia);
    }
    @Transactional
    public List<Noticia> listarNoticias(){
        List<Noticia> noticias = new ArrayList();
        
        noticias = noticiaRepositorio.findAll();
        return noticias;
    }
    public void modificarNoticia(Long id, String titulo, String cuerpo, String foto)throws MiException{
        
        
        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);

        if (respuesta.isPresent()) {
            
            Noticia noticia = respuesta.get();
            noticia.setId(id);
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            noticia.setFoto(foto);
            
        }
        
    }
    public Noticia getOne(Long id){
        return noticiaRepositorio.getOne(id);
    }
    public void validar(Long id) throws MiException{
         if (id==null) { 
            throw new MiException("el id no puede ser nulo");
            
        }
        //if (titulo.isEmpty() || titulo ==null) { 
          //  throw new MiException("el titulo no puede ser nulo");
            
        //}
        
    }
}

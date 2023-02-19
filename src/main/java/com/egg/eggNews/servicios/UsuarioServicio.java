/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.eggNews.servicios;

import com.egg.eggNews.entidades.Usuario;
import com.egg.eggNews.enumeraciones.Rol;
import com.egg.eggNews.excepciones.MiException;
import com.egg.eggNews.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

/**
 *
 * @author cesar
 */
@Service
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional
    public void registrar(String nombre, String email, String password, String password2)throws Exception{
        
        validar(nombre,email,password,password2);
        
        Usuario user = new Usuario();
        
        user.setNombre(nombre);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        
        user.setRol(Rol.USER);
        
        usuarioRepositorio.save(user);
        
        
    }
    
    
    private void validar(String nombre, String email, String password, String password2) throws MiException{
        
        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("el nomber no puede estar vacio");
        }
        if (email == null || email.isEmpty()) {
            throw new MiException("el email no puede estar vacio");
            
        }
        if (password.isEmpty()|| password ==null) {
            throw new MiException("debe ingresar una contraseña");
            
        }
        if (!password.equals(password2)) {
            throw new MiException("las contraseñas no coinciden");
                    
        }
    
}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
            if (usuario != null) {
                
                List <GrantedAuthority> permisos= new ArrayList();
                
                
                GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+ usuario.getRol().toString());
                        
                permisos.add(p);
                
                return new User(usuario.getEmail(), usuario.getPassword(),permisos);
                
                 
        }else{
                return null;
            }
    }
}

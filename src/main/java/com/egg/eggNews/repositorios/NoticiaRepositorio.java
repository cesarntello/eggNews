/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.eggNews.repositorios;

import com.egg.eggNews.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cesar
 */
@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, Long>{

    @Query("SELECT n FROM Noticia n WHERE n.titulo = :titulo")
    public Noticia buscarPorTitulo (@Param("titulo") String titulo);
    
    @Query("SELECT n FROM Noticia n WHERE n.id = :id")
    public Noticia buscarPorId(@Param("id") String id);
}

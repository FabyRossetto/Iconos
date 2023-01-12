/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.Servicios;

import com.alkemy.alkemyProject.DTO.IconoDTO;
import com.alkemy.alkemyProject.entidades.Paises;
import java.time.LocalDate;
import java.util.List;


/**
 *
 * @author Fabi
 */
public interface IconosService {
    
     IconoDTO save(IconoDTO dto);
     
    List<IconoDTO>getAllIconos();
    
    void remove(Long idIcono,Long idPais);
    
    void delete(Long id);
    
    IconoDTO modificarIcono(Long id,String nombre,String historia,String imagen,Long altura,LocalDate fc);
    
    IconoDTO addPais(Long id,Paises pais);
}

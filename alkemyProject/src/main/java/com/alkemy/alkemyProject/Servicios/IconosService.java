/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.Servicios;

import com.alkemy.alkemyProject.DTO.IconoBasicoDTO;
import com.alkemy.alkemyProject.DTO.IconoDTO;
import com.alkemy.alkemyProject.entidades.Paises;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


/**
 *
 * @author Fabi
 */
public interface IconosService {
    
     IconoDTO save(IconoDTO dto,boolean cargarPaises);
     
    List<IconoDTO>getAllIconos(boolean cargarPaises);
    
  List<IconoDTO> getByFilters(String name,String date,Set<Long>cities,String order);
    
//    void remove(Long idIcono,Long idPais);
    
    void delete(Long id);
    
    IconoDTO modificarIcono(Long id,IconoDTO DTO,boolean cargarPaises);
    
    IconoDTO addPais(Long idIcono,List<Long>idPaises,boolean cargarPaises);
}

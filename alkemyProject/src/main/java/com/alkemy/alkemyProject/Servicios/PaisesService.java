/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.Servicios;

import com.alkemy.alkemyProject.DTO.PaisDTO;
import com.alkemy.alkemyProject.entidades.Continente;
import com.alkemy.alkemyProject.entidades.Paises;
import java.util.List;



/**
 *
 * @author Fabi
 */
public interface PaisesService {
    
     PaisDTO save(PaisDTO dto,boolean cargarIconos);
     PaisDTO modificarPais(Long id,PaisDTO dto,boolean cargarIconos);
     
    List<PaisDTO>getAllPaises(boolean cargarIconos);
    
    void delete(Long id);
    
    List<Paises> getAllById(List<Long> paises);
}

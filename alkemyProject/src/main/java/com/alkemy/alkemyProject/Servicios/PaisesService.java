/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.Servicios;

import com.alkemy.alkemyProject.DTO.PaisDTO;
import com.alkemy.alkemyProject.entidades.Continente;
import java.util.List;



/**
 *
 * @author Fabi
 */
public interface PaisesService {
    
     PaisDTO save(PaisDTO dto);
    List<PaisDTO>getAllPaises();
    
    void remove(Long idIcono,Long idPais);
    PaisDTO modificarPais(Long id,String imagen,String nombre,Long ch,Long superficie,Continente continente,Long ContId);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.Servicios;

import com.alkemy.alkemyProject.DTO.continenteDTO;
import java.util.List;

/**
 *
 * @author Fabi
 */
public interface ContinenteService {
    
   continenteDTO save(continenteDTO dto);
   
    List<continenteDTO>getAllContinentes();
    
    continenteDTO modificarContinente(Long id,continenteDTO dto);

    
    void delete(Long id);//soft delete
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.Mapper;

import com.alkemy.alkemyProject.DTO.continenteDTO;
import com.alkemy.alkemyProject.entidades.Continente;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Fabi
 */
@Component //esto me permite inyectarlo en la clase
public class ContinenteMapper {

   
    
    public Continente DtoAEntidad(continenteDTO dto){
        Continente cont=new Continente();
        cont.setImagen(dto.getImagen());//le seteo por la imagen que traiga el dto
        cont.setNombre(dto.getNombre());//le seteo por el nombre que traiga el dto
        return cont;
    }
    
    public continenteDTO EntidadADto (Continente entidad){
        continenteDTO dto= new continenteDTO();
        dto.setId(entidad.getId());
        dto.setImagen(entidad.getImagen());
        dto.setNombre(entidad.getNombre());
        return dto;
    }
    public List<continenteDTO> EntidadListADtoList (List<Continente> entidades){
        List<continenteDTO> dtos= new ArrayList();
        for(Continente entidad:entidades){
            dtos.add(this.EntidadADto(entidad));
        }
        return dtos;
    }
    public List<Continente> DTOListAEntidadList (List<continenteDTO> dtos){
        List<Continente> cont= new ArrayList();
        for(continenteDTO dto:dtos){
            cont.add(this.DtoAEntidad(dto));
        }
        return cont;
    }
}

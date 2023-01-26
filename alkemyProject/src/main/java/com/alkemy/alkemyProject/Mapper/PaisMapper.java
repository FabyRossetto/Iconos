/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.Mapper;


import com.alkemy.alkemyProject.DTO.PaisDTO;
import com.alkemy.alkemyProject.entidades.Paises;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

/**
 *
 * @author Fabi
 */
@Component
public class PaisMapper {
   
     @Autowired
    IconoMapper im;
     
    public Paises DtoAEntidad(PaisDTO dto){
        Paises p =new Paises();
        p.setImagen(dto.getImagen());//le seteo por la imagen que traiga el dto
        p.setNombre(dto.getNombre());//le seteo por el nombre que traiga el dto
        p.setCantidadHabitantes(dto.getCantidadHabitantes());
        p.setSuperficie(dto.getSuperficie());
        p.setContinente(dto.getContinente());
        p.setContinenteId(dto.getContinenteId());
        return p;
    }
    
    public PaisDTO EntidadADto (Paises entidad,boolean cargarIcono){
    
        PaisDTO dto= new PaisDTO();
        dto.setId(entidad.getId());
        dto.setImagen(entidad.getImagen());
        dto.setNombre(entidad.getNombre());
        dto.setCantidadHabitantes(dto.getCantidadHabitantes());
        dto.setSuperficie(dto.getSuperficie());
        dto.setContinente(dto.getContinente());
        dto.setContinenteId(dto.getContinenteId());
        
//        if(cargarIcono){ // esto es para saber si hay que cargar los ICONOS o no. Por defecto no los carga.
//            List<IconoDTO>IconosDTO = this.im.EntidadListADtoList(entidad.getIconos(),Boolean.FALSE);
//            dto.setIconos(IconosDTO);
//        }
        return dto;
    }
    
    public List<PaisDTO> EntidadListADtoList (List<Paises> entidades,boolean cargarIconos){
        List<PaisDTO> dtos= new ArrayList();
        for(Paises entidad:entidades){
            dtos.add(this.EntidadADto(entidad,cargarIconos));
        }
        return dtos;
    }
     public List<Paises> DTOListAEntidadList (List<PaisDTO> dtos){
        List<Paises> paises= new ArrayList();
        for(PaisDTO dto:dtos){
            paises.add(this.DtoAEntidad(dto));
        }
        return paises;
    }
     
}

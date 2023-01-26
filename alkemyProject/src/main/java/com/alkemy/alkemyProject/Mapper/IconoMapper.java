/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.Mapper;

import com.alkemy.alkemyProject.DTO.IconoBasicoDTO;
import com.alkemy.alkemyProject.DTO.IconoDTO;
import com.alkemy.alkemyProject.Repository.IconosRepository;
import com.alkemy.alkemyProject.Repository.PaisesRepository;

import com.alkemy.alkemyProject.entidades.Iconos;
import com.alkemy.alkemyProject.entidades.Paises;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;


import org.springframework.stereotype.Component;

/**
 *
 * @author Fabi
 */
@Component
public class IconoMapper {
    
     @Autowired
    @Lazy
    private PaisMapper pm;
    @Autowired
    private PaisesRepository pr;

    @Autowired
    private IconosRepository ir;

    
     public Iconos DtoAEntidad(IconoDTO dto){
        Iconos i=new Iconos();
        i.setImagen(dto.getImagen());//le seteo por la imagen que traiga el dto
        i.setNombre(dto.getNombre());//le seteo por el nombre que traiga el dto
        i.setFechaCreacion(dto.getFechaCreacion());
        i.setAltura(dto.getAltura());
        i.setHistoria(dto.getHistoria());
        return i;
    }
     
    
    public IconoDTO EntidadADto (Iconos entidad,boolean cargarPaises){
    
        IconoDTO dto= new IconoDTO();
        dto.setId(entidad.getId());
        dto.setImagen(entidad.getImagen());
        dto.setNombre(entidad.getNombre());
        dto.setFechaCreacion(entidad.getFechaCreacion());
        dto.setAltura(entidad.getAltura());
        dto.setHistoria(entidad.getHistoria());
       if(cargarPaises){ // esto es para saber si hay que cargar los paises o no. Por defecto no los carga.
            dto.setPaises(entidad.getPaises().stream().map(Paises::getId).collect(Collectors.toList()));
        }
        return dto;
    }
    
//    private LocalDate stringALocalDate(String stringDate){
//        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-mm-dd");
//        LocalDate date=LocalDate.parse(stringDate, formatter);
//        return date;
//    }
    
    public List<IconoBasicoDTO> IconosListAIconoBasicoDTOList(List<Iconos>entidades){
        List<IconoBasicoDTO>dtos=new ArrayList<>();
        IconoBasicoDTO bd;
        for(Iconos entidad: entidades){
            bd = new IconoBasicoDTO();
            bd.setId(entidad.getId());
            bd.setImagen(entidad.getImagen());
            bd.setNombre(entidad.getNombre());
            dtos.add(bd);
        }
        return dtos;
    }
     public List<IconoDTO> EntidadListADtoList (List<Iconos> entidades,boolean cargarPaises){
        List<IconoDTO> dtos= new ArrayList();
        for(Iconos entidad:entidades){
            dtos.add(this.EntidadADto(entidad,cargarPaises));
        }
        return dtos;
    }
     public List<Iconos> DTOListAEntidadList (List<IconoDTO> dtos){
        List<Iconos> icons= new ArrayList();
        for(IconoDTO dto:dtos){
            icons.add(this.DtoAEntidad(dto));
        }
        return icons;
    }
    
}

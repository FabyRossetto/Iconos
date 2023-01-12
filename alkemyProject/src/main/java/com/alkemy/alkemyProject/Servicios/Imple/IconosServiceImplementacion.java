/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.Servicios.Imple;

import com.alkemy.alkemyProject.DTO.IconoDTO;
import com.alkemy.alkemyProject.Mapper.IconoMapper;
import com.alkemy.alkemyProject.Repository.IconosRepository;
import com.alkemy.alkemyProject.Servicios.IconosService;
import com.alkemy.alkemyProject.entidades.Iconos;
import com.alkemy.alkemyProject.entidades.Paises;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Fabi
 */
@Service
public class IconosServiceImplementacion implements IconosService{
    
    @Autowired
    IconosRepository ir;
     @Autowired
    IconoMapper im;
    
   
    
   @Override
    public IconoDTO save(IconoDTO dto){
        Iconos entidad= im.DtoAEntidad(dto);
        Iconos entidadGuardada = ir.save(entidad);//esto me devuelve la entidad guardada
        IconoDTO result= im.EntidadADto(entidadGuardada,false);//la convierto en DTO
        
        return result;//lo devuelvo como dto
    }
    
    @Override
    public IconoDTO modificarIcono(Long id,String nombre,String historia,String imagen,Long altura,LocalDate fc) {

        //optional es una clase que puede o no puede contener un valor, se usa por las dudas que el dato ingresado sea nulo
        Optional<Iconos> respuesta = ir.findById(id);
       
            Iconos icon = respuesta.get();
            
            icon.setAltura(altura);
            icon.setFechaCreacion(fc);
            icon.setNombre(nombre);
            icon.setImagen(imagen);
            icon.setHistoria(historia);

            ir.save(icon);
            
          IconoDTO result=im.EntidadADto(icon, true);
        
        
        return result;

    }
    
  
    
    @Override
     public List<IconoDTO> getAllIconos() {//me trae todos los iconos,sin los paises.
        List<Iconos>entidades=this.ir.findAll();
        List<IconoDTO>result=this.im.EntidadListADtoList(entidades,Boolean.FALSE);
        return result;
    }
    
    @Override
     public void delete(Long id){
         this.ir.deleteById(id);
     }
     
    
     
    @Override
    public void remove(Long idIcono,Long idPais){//borra los iconos
        
        this.ir.deleteById(idIcono);
        this.ir.deleteById(idPais);
    }

    @Override
    public IconoDTO addPais(Long id, Paises pais) {//el id es del icono al que le quiero agregar el pais
        Optional<Iconos>respuesta=ir.findById(id);
        Iconos icon=respuesta.get();
        icon.agregarPais(pais);
        ir.save(icon);
        
        IconoDTO result=im.EntidadADto(icon, true);
        return result;
    }

    

   
    
}

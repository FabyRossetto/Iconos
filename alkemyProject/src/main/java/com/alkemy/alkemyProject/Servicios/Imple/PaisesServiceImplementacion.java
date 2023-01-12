/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.Servicios.Imple;

import com.alkemy.alkemyProject.DTO.PaisDTO;
import com.alkemy.alkemyProject.Mapper.PaisMapper;
import com.alkemy.alkemyProject.Repository.PaisesRepository;
import com.alkemy.alkemyProject.Servicios.PaisesService;
import com.alkemy.alkemyProject.entidades.Continente;
import com.alkemy.alkemyProject.entidades.Paises;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Fabi
 */
@Service
public class PaisesServiceImplementacion implements PaisesService{
    @Autowired
    PaisesRepository pr;
     @Autowired
    PaisMapper pm;
   
    
    @Override
    public PaisDTO save(PaisDTO dto){
        Paises entidad= pm.DtoAEntidad(dto);
        Paises entidadGuardada = pr.save(entidad);//esto me devuelve la entidad guardada
        PaisDTO result= pm.EntidadADto(entidadGuardada,false);//la convierto en DTO
        
        return result;//lo devuelvo como dto
    }
    @Override
    public PaisDTO modificarPais(Long id,String imagen,String nombre,Long ch,Long superficie,Continente continente,Long ContId) {

        //optional es una clase que puede o no puede contener un valor, se usa por las dudas que el dato ingresado sea nulo
        Optional<Paises> respuesta = pr.findById(id);
       
            Paises pais = respuesta.get();
           
            pais.setImagen(imagen);
            pais.setNombre(nombre);
            pais.setCantidadHabitantes(ch);
            pais.setSuperficie(superficie);
            
            pais.setContinente(continente);
            pais.setContinenteId(ContId);
            
            pr.save(pais);
            
          PaisDTO result=pm.EntidadADto(pais, true);
        
        
        return result;

    }
    
    @Override
     public List<PaisDTO> getAllPaises() {//me trae todos los paises,sin los iconos.
        List<Paises>entidades=this.pr.findAll();
        List<PaisDTO>result=this.pm.EntidadListADtoList(entidades,Boolean.FALSE);
        return result;
    }
    
    @Override
    public void remove(Long idIcono,Long idPais){//borra los paises y los iconos
        this.pr.deleteById(idIcono);
        this.pr.deleteById(idPais);
    }

   
    
}

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

    public PaisesServiceImplementacion(PaisesRepository pr, PaisMapper pm) {
        this.pr = pr;
        this.pm = pm;
    }
   
    
    @Override
    public PaisDTO save(PaisDTO dto,boolean cargarIconos){
        Paises entidad= pm.DtoAEntidad(dto);
        Paises entidadGuardada = pr.save(entidad);//esto me devuelve la entidad guardada
        PaisDTO result= pm.EntidadADto(entidadGuardada,cargarIconos);//la convierto en DTO
        
        return result;//lo devuelvo como dto
    }
    
    
    public PaisDTO modificarPais(Long id,PaisDTO dto,boolean cargarIconos) {

        //optional es una clase que puede o no puede contener un valor, se usa por las dudas que el dato ingresado sea nulo
        Optional<Paises> respuesta = pr.findById(id);
       
            Paises pais = respuesta.get();
           
            pais.setImagen(dto.getImagen());
            pais.setNombre(dto.getNombre());
            pais.setCantidadHabitantes(dto.getCantidadHabitantes());
            pais.setSuperficie(dto.getSuperficie());
            
            pais.setContinente(dto.getContinente());
            pais.setContinenteId(dto.getContinenteId());
            
            pr.save(pais);
            
          PaisDTO result=pm.EntidadADto(pais,cargarIconos);
        
        
        return result;

    }
    
    @Override
     public List<PaisDTO> getAllPaises(boolean cargarIconos) {//me trae todos los paises,sin los iconos.
        List<Paises>entidades=this.pr.findAll();
        List<PaisDTO>result=this.pm.EntidadListADtoList(entidades,cargarIconos);
        return result;
    }
    
    @Override
    public void delete(Long id){
        this.pr.deleteById(id);
       
    }

   
    @Override
    public List<Paises> getAllById(List<Long> paises) {
        return null;    }

   
    
}

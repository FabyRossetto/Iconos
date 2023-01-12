/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.Servicios.Imple;

import com.alkemy.alkemyProject.DTO.continenteDTO;
import com.alkemy.alkemyProject.Mapper.ContinenteMapper;
import com.alkemy.alkemyProject.Repository.ContinenteRepository;
import com.alkemy.alkemyProject.Servicios.ContinenteService;
import com.alkemy.alkemyProject.entidades.Continente;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Fabi
 */
@Service
public class ContinenteServiceImplementacion implements ContinenteService{
    @Autowired
    ContinenteMapper cm;
    @Autowired
    ContinenteRepository cr;
    
    @Override
    public continenteDTO save(continenteDTO dto){
        Continente entidad= cm.DtoAEntidad(dto);
        Continente entidadGuardada = cr.save(entidad);//esto me devuelve la entidad guardada
        continenteDTO result= cm.EntidadADto(entidadGuardada);//la convierto en DTO
        
        return result;
    }
    @Override
    public continenteDTO modificarContinente(Long id,String nombre,String imagen) {

        //optional es una clase que puede o no puede contener un valor, se usa por las dudas que el dato ingresado sea nulo
        Optional<Continente> respuesta = cr.findById(id);
       
            Continente con = respuesta.get();
            
            con.setNombre(nombre);
            con.setImagen(imagen);

            cr.save(con);
            
          continenteDTO result=cm.EntidadADto(con);
        
        
        return result;

    }
   
    @Override
    public List<continenteDTO> getAllContinentes() {
        List<Continente>entidades=this.cr.findAll();
        List<continenteDTO>result=this.cm.EntidadListADtoList(entidades);
        return result;
    }
    
     @Override
    public void delete(Long id){//borra los continentes
        this.cr.deleteById(id);
    }
    
    
    
}

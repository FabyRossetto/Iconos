/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.controladores;

import com.alkemy.alkemyProject.DTO.PaisDTO;
import com.alkemy.alkemyProject.Servicios.PaisesService;
import com.alkemy.alkemyProject.entidades.Continente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fabi
 */
@RestController
@RequestMapping("paises")
public class PaisesController {
     @Autowired
    PaisesService ps;
    
    @PostMapping("/{id}")
    public ResponseEntity<PaisDTO>save(@RequestBody PaisDTO dto){
      PaisDTO result= this.ps.save(dto);
       return ResponseEntity.ok().body(result);
  }
    
   @PutMapping("/{id}")
    public ResponseEntity<PaisDTO>update(@PathVariable Long id,@RequestBody String imagen,@RequestBody String nombre,@RequestBody Long ch,@RequestBody Long superficie,@RequestBody Continente continente,Long contId){
      PaisDTO result= this.ps.modificarPais(id, imagen, nombre, ch, superficie, continente, contId);
       return ResponseEntity.ok().body(result);
  }
    
   
 
     @DeleteMapping("/{id}/pais/{idPais}")
    public ResponseEntity<Void> removePais(@PathVariable Long id,@PathVariable Long idPais){
        this.ps.remove(id,idPais);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
     @GetMapping
    public ResponseEntity <List<PaisDTO>> getAll(){
        List<PaisDTO>paises=ps.getAllPaises();
        return ResponseEntity.ok().body(paises);
    }
    
}

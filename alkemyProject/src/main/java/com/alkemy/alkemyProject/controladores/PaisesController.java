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
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @PostMapping
    public ResponseEntity<PaisDTO>save(@RequestBody PaisDTO dto,@RequestParam(value = "cargarIconos", required = false)
                                          boolean cargarIconos){
      PaisDTO paisGuardado= this.ps.save(dto,cargarIconos);
       return ResponseEntity.status(HttpStatus.CREATED).body(paisGuardado);
  }
     @GetMapping
    public ResponseEntity <List<PaisDTO>> getAll(@RequestParam(value = "cargarIconos", required = false)
                                                      boolean cargarIconos){
        List<PaisDTO>paises=ps.getAllPaises(cargarIconos);
        return ResponseEntity.ok().body(paises);
    }
    
   @PutMapping("/{id}")
    public ResponseEntity<PaisDTO>update(@PathVariable Long id,@RequestBody PaisDTO dto,@RequestParam(value = "cargarIconos", required = false)
                                                boolean cargarIconos){
      PaisDTO paisModif= this.ps.modificarPais(id, dto,cargarIconos);
       return ResponseEntity.ok().body(paisModif);
  }
    
   
 
     @DeleteMapping("/{id}/pais/{idPais}")
    public ResponseEntity<Void> delete(@PathVariable Long id){//soft delete
        this.ps.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    
}

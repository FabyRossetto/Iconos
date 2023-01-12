/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.controladores;

import com.alkemy.alkemyProject.DTO.IconoDTO;
import com.alkemy.alkemyProject.Servicios.IconosService;
import com.alkemy.alkemyProject.entidades.Paises;
import java.time.LocalDate;
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
@RequestMapping("iconos")
public class IconosController {
    @Autowired
    IconosService is;
    
    @PostMapping("/{id}")
    public ResponseEntity<IconoDTO>save(@RequestBody IconoDTO dto){
      IconoDTO result= this.is.save(dto);
       return ResponseEntity.ok().body(result);
  }
    
   @PutMapping("/{id}")
    public ResponseEntity<IconoDTO>update(@PathVariable Long id,@RequestBody String nombre,@RequestBody String historia,@RequestBody String imagen,@RequestBody Long altura,@RequestBody LocalDate fc){
      IconoDTO result= this.is.modificarIcono(id, nombre, historia,imagen,altura,fc);
       return ResponseEntity.ok().body(result);
  }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.is.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
 @PostMapping("/{id}/pais/{idPais}")
   public ResponseEntity<Void>addPais(@PathVariable Long id,@RequestBody Paises pais){//el id es el de icono
    this.is.addPais(id,pais);
     return ResponseEntity.status(HttpStatus.CREATED).build();   }
    
     @DeleteMapping("/{id}/pais/{idPais}")
    public ResponseEntity<Void> removePais(@PathVariable Long id,@PathVariable Long idPais){
        this.is.remove(id,idPais);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
     @GetMapping
    public ResponseEntity <List<IconoDTO>> getAll(){
        List<IconoDTO>iconos=is.getAllIconos();
        return ResponseEntity.ok().body(iconos);
    }
    
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.controladores;


import com.alkemy.alkemyProject.DTO.continenteDTO;
import com.alkemy.alkemyProject.Servicios.ContinenteService;
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
@RequestMapping("continentes")
public class continenteController {
    @Autowired
    ContinenteService cs;
    
    @GetMapping
    public ResponseEntity <List<continenteDTO>> getAll(){
        List<continenteDTO>continentes=cs.getAllContinentes();
        return ResponseEntity.ok().body(continentes);
    }
    @PostMapping //este es el verbo
    //este metodo para guardar el continente es un EndPoint
    public ResponseEntity<continenteDTO> save(@RequestBody continenteDTO continente){
        //el ResponseEntity es un tipo de dato que nos ayuda a manejar el response. Y se hace con continente DTO para que sea generico
        continenteDTO ContinenteGuardado=cs.save(continente);
        return ResponseEntity.status(HttpStatus.CREATED).body(ContinenteGuardado);
                //el created devuelve un 200,codigo de exito y crea el continente
    }
    @PutMapping("/{id}")
    public ResponseEntity<continenteDTO>update(@PathVariable Long id,@RequestBody String nombre,@RequestBody String imagen){
      continenteDTO result= this.cs.modificarContinente(id, nombre, imagen);
       return ResponseEntity.ok().body(result);
  }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.cs.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}

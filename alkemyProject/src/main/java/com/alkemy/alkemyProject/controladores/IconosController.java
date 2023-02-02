/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.controladores;

import com.alkemy.alkemyProject.DTO.IconoBasicoDTO;
import com.alkemy.alkemyProject.DTO.IconoDTO;
import com.alkemy.alkemyProject.Servicios.IconosService;
import com.alkemy.alkemyProject.entidades.Paises;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
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
@RequestMapping("iconos")
public class IconosController {
    @Autowired
    IconosService is;
    
    @PostMapping()
    public ResponseEntity<IconoDTO>save(@RequestBody IconoDTO dto,@RequestParam(value = "cargarPaises", required = false, defaultValue = "true") boolean cargarPaises){
      IconoDTO result= this.is.save(dto,cargarPaises);
       return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }
    @GetMapping//trae todos los iconos con la info basica
    public ResponseEntity <List<IconoDTO>> getAll(@RequestParam(value = "cargarPaises", required = false) boolean cargarPaises){
        List<IconoDTO>iconos=is.getAllIconos(cargarPaises);
        return ResponseEntity.ok().body(iconos);
    }
    
   @PutMapping("/{id}")
    public ResponseEntity<IconoDTO>update(@PathVariable Long id, @RequestBody IconoDTO DTO,boolean cargarPaises){
      IconoDTO iconoModif= this.is.modificarIcono(id, DTO,cargarPaises);
       return ResponseEntity.ok().body(iconoModif);
  }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.is.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
 @PutMapping("/{id}/pais/{idPais}")
   public ResponseEntity<IconoDTO>addPais(@PathVariable Long idIcono,@RequestBody List<Long> idPais,@RequestParam(value = "cargarPaises", required = false)
                                                     boolean cargarPaises){//el id es el de icono
    IconoDTO DTO=this.is.addPais(idIcono,idPais,cargarPaises);
     return ResponseEntity.ok().body(DTO);   }
    
//     @DeleteMapping("/{id}/pais/{idPais}")
//    public ResponseEntity<Void> removePais(@PathVariable Long id,@PathVariable Long idPais){
//        this.is.remove(id,idPais);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
     
//    @GetMapping("/{id}")//metodo para traer un solo icono
//    public ResponseEntity<IconoDTO> getDetallePorId(@PathVariable Long id){
//        IconoDTO icono=this.is.getDetailsById(id);//hacer este metodo en el servicio
//        return ResponseEntity.ok(icono);
//    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<IconoDTO>> getByFilters(
    @RequestParam(required= false)String name,
    @RequestParam(required=false)String date,
    @RequestParam(required=false)Set<Long>cities,
    @RequestParam(required=false,defaultValue="ASC")String order
    ){
       List<IconoDTO> iconos=this.is.getByFilters(name,date,cities,order);//crear este metodo en el servicio
       return ResponseEntity.ok(iconos);
    }
//    
//    
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.DTO;

import com.alkemy.alkemyProject.entidades.Continente;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Fabi
 */
@Getter
@Setter
public class PaisDTO {
    private Long id;
    private String imagen;
    private String nombre;

    private Long cantidadHabitantes;

    private Long superficie; //m2
    private Continente continente;

    private Long continenteId;
    private List<IconoDTO> iconos;
    
}

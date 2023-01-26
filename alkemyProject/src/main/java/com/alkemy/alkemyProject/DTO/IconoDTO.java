/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.DTO;

import com.alkemy.alkemyProject.entidades.Paises;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Fabi
 */
@Getter
@Setter
public class IconoDTO {
    private Long id;
    private String imagen;
    private String nombre;
    private LocalDate fechaCreacion;

    private Long altura;
    private String historia;
  private List<Long> paises;

}

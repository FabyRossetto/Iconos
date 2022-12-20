/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="continenete") //asi se va a llamar la tabla
@Getter //Lombok crea getters y setters para no codearlos
@Setter
public class Continente {
    @Id
   
    @GeneratedValue(strategy=GenerationType.SEQUENCE)//esto es para que se cree el id secuencialmente
    private Long id;
    private String imagen;
    private String nombre;
    
}

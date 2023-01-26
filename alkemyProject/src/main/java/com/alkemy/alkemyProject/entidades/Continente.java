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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="continenete") //asi se va a llamar la tabla
@Getter //Lombok crea getters y setters para no codearlos
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql= "UPDATE continente SET deleted=true WHERE id=?")//soft delete
@Where(clause="delete=false")//con esto se diferencia los que fueron borrados de los que no
public class Continente {
    @Id
   
    @GeneratedValue(strategy=GenerationType.SEQUENCE)//esto es para que se cree el id secuencialmente
    private Long id;
    private String imagen;
    private String nombre;

    
    
    
    
}

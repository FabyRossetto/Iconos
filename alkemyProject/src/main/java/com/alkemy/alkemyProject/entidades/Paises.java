/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.entidades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 *
 * @author Fabi
 */
@Entity
@Table(name="pais") //asi se va a llamar la tabla
@Getter //Lombok crea getters y setters para no codearlos
@Setter
@SQLDelete(sql= "UPDATE pais SET deleted=true WHERE id=?")//soft delete
@Where(clause="delete=false")//con esto se diferencia los que fueron borrados de los que no
public class Paises {
    @Id
    
    @GeneratedValue(strategy=GenerationType.SEQUENCE)//esto es para que se cree el id secuencialmente
    private Long id;
    private String imagen;
    private String nombre;
    
    @Column(name="habitantes")// con column se elije el nombre que va a tener la columna de la tabla
    private Long cantidadHabitantes;
    
    private Long superficie; //m2
    
    //EAGER=la inilizacion es de tipo temprana, cuando pida un dato de tipo Pais me va a venir con su continente
    //CASCADE.ALL=Para que las operaciones sean consecuentes con el continente,por ej, si hago un delete que tambien me borre el continenete
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
    @JoinColumn(name="continente_id", insertable=false, updatable=false)//como quiero unir las tablas, a traves del "continente-id"
    private Continente continente;
    //es insertable and updatable false porque lo uso solo para obtener informacion
    //aca va a venir el continente entero,solo busco informacion,no envio nada
    
    @Column(name="continente_id", nullable= false)
    private Long continenteId;
    //aca puedo actualizar o borrar el id del continente,lo guardo
    
    
    //se aplica el la definicion del many to many de este lado de paises, porque paises engloba a iconos
    @ManyToMany(
           
         cascade={
        CascadeType.PERSIST,//guarda la info
        CascadeType.MERGE //y la une
        
    })
    //cuando creo un pais le paso la lista de iconos
   
    @JoinTable(
            name="icono_pais",//como quiero llamar la tabla intermedia
            joinColumns=@JoinColumn(name="pais_id"),
            inverseJoinColumns=@JoinColumn(name="icono_id"))//es del otro lado hacia aca
            private List <Iconos> iconos= new ArrayList<>();
    
    
    @Override
    public boolean equals(Object obj){
        if(obj==null)
            return false;
        if(getClass()!=obj.getClass())
            return false;
        final Paises other = (Paises)obj;
        return other.id == this.id;
    }
    
    
    
    
    
}


package com.alkemy.alkemyProject.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="icono") //asi se va a llamar la tabla
@Getter //Lombok crea getters y setters para no codearlos
@Setter
public class Iconos {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.SEQUENCE)//esto es para que se cree el id secuencialmente
    private Long id;
    private String imagen;
    private String nombre;
    
   @Column(name= "fecha_creacion")
   @DateTimeFormat(pattern="yyy/mm/dd")//asi me llega el formato
    private LocalDate fechaCreacion;
   
   private Long altura;
   private String historia;
   
   //cuando creo un icono no puedo pasarle una lista de paises para que los cree
     @ManyToMany(mappedBy ="iconos", cascade= CascadeType.ALL)//el mapeo se hace con el atributo del hashSet de la otra clase
     private List<Paises> paises=new ArrayList<>();
     
     public void agregarPais(Paises pais){
         this.paises.add(pais);
     }
     
     public void sacarPais(Paises pais){
         this.paises.remove(pais);
     }
}
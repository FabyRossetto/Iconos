
package com.alkemy.alkemyProject.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="icono") //asi se va a llamar la tabla
@Getter //Lombok crea getters y setters para no codearlos
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql= "UPDATE icon SET deleted=true WHERE id=?")//soft delete
@Where(clause="delete=false")//con esto se diferencia los que fueron borrados de los que no
public class Iconos {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.SEQUENCE)//esto es para que se cree el id secuencialmente
    private Long id;
    
    private String imagen;
    
    private String nombre;
    
   @Column(name= "fecha_creacion",nullable=false)
   @JsonFormat(pattern="yyyy-MM-dd")//asi me llega el formato
    private LocalDate fechaCreacion;
   
   private Long altura;
   
   private String historia;
   
   private boolean deleted;
   
   //cuando creo un icono no puedo pasarle una lista de paises para que los cree
     @ManyToMany(mappedBy ="iconos", cascade= CascadeType.ALL)//el mapeo se hace con el atributo del hashSet de la otra clase
     private List<Paises> paises;

     
    
     public void agregarPais(Paises pais){
         this.paises.add(pais);
     }
     
     public void sacarPais(Paises pais){
         this.paises.remove(pais);
     }
}

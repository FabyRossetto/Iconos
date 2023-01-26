/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.Repository.Especificaciones;

import com.alkemy.alkemyProject.DTO.FiltrosIconoDTO;
import com.alkemy.alkemyProject.entidades.Iconos;
import com.alkemy.alkemyProject.entidades.Paises;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 *
 * las especificaciones se hacen para crear sql avanzadas de forma dinamica,no para consultas que se puedan hacer con el JPA repository
 */
@Component
public class IconoEspecificacion {
    
    public Specification<Iconos> getByFilters(FiltrosIconoDTO filtrosDTO){
        return(root,query,criteriaBuilder)->{
            List<Predicate>predicates=new ArrayList<>();
            
            if(StringUtils.hasLength(filtrosDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nombre")),
                        "%"+filtrosDTO.getName().toLowerCase()+"%"
                )
                        );
            }
            if(StringUtils.hasLength(filtrosDTO.getDate())){
                DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date=LocalDate.parse(filtrosDTO.getDate(),formatter);
                predicates.add(
                criteriaBuilder.equal(root.<LocalDate> get("fechaCreacion"),date)
                );
            }
             if(!CollectionUtils.isEmpty(filtrosDTO.getCities())){
                 Join<Paises,Iconos>join=root.join("pais",JoinType.INNER);
                 Expression<String> citiesId=join.get("id");
                 predicates.add(citiesId.in(filtrosDTO.getCities()));
             }
             
             //eliminar duplicados
             query.distinct(true);
             
             //order resolver
             String orderByField="nombre";
             query.orderBy(
                     filtrosDTO.isASC()?
                             criteriaBuilder.asc(root.get(orderByField)):
                             criteriaBuilder.desc(root.get(orderByField))
             );
             return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            
        };
        
    };
}
   
    


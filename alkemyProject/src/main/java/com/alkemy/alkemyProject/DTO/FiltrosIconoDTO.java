/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.DTO;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Fabi
 */
@Getter
@Setter
public class FiltrosIconoDTO {
    private String name;
    private String date;
    private Set<Long> cities;
    private String order;

    public FiltrosIconoDTO(String name, String date, Set<Long> cities, String order) {
        this.name = name;
        this.date = date;
        this.cities = cities;
        this.order = order;
    }
    
    public boolean isASC(){
        return this.order.compareToIgnoreCase("ASC")==0;
    }
    public boolean isDESC(){
        return this.order.compareToIgnoreCase("DESC")==0;
    }
    
}

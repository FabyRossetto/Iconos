/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.excepciones;

/**
 *
 * @author Fabi
 */
public class ParametroNoEncontrado extends RuntimeException{
    
    public ParametroNoEncontrado(String error){
        super(error);
    }
}

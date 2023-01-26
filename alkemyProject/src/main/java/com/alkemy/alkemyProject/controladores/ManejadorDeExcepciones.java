/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.controladores;

import com.alkemy.alkemyProject.DTO.ApiErrorDTO;
import com.alkemy.alkemyProject.excepciones.ParametroNoEncontrado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



/**
 *
 * @author Fabi
 */
@ControllerAdvice
public class ManejadorDeExcepciones extends ResponseEntityExceptionHandler{
    
   @ExceptionHandler(value={ParametroNoEncontrado.class})
    protected ResponseEntity<Object>ManejadorDeParametroNoEncontrado(RuntimeException ex,WebRequest req){
        ApiErrorDTO errorDTO = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Arrays.asList("Parametro no Encontrado"));
        return handleExceptionInternal(ex,errorDTO,new HttpHeaders(),HttpStatus.BAD_REQUEST,req);
    }


//para manejar cuando no funcionan las anotaciones valid

protected ResponseEntity<Object>ManejadorDeArgumentosNoValidos(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest req){
    List <String> errores= new ArrayList<>();
    for(FieldError error:ex.getBindingResult().getFieldErrors()){
        errores.add(error.getField()+":"+ error.getDefaultMessage());
    }
    for(ObjectError error:ex.getBindingResult().getGlobalErrors()){
        errores.add(error.getObjectName()+ ":" +error.getDefaultMessage());
    }
    ApiErrorDTO apiError= new ApiErrorDTO(HttpStatus.BAD_REQUEST,ex.getLocalizedMessage(),errores);
    return handleExceptionInternal(
            ex,apiError,headers,apiError.getStatus(),req);
    
}

@ExceptionHandler(value={IllegalArgumentException.class,IllegalStateException.class})
public ResponseEntity<Object>handleConflict(RuntimeException ex,WebRequest request){
    String bodyOfResponse="Esto deberia ser una aplicacion especifica";
    return handleExceptionInternal(ex,bodyOfResponse,new HttpHeaders(),HttpStatus.CONFLICT,request);
}




    
        }

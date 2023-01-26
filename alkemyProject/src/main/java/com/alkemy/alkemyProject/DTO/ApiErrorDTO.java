/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.DTO;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Fabi
 */
@Data
@AllArgsConstructor
public class ApiErrorDTO {
     private HttpStatus status;
     private String message;
     private List<String>errors;
}

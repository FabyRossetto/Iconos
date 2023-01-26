/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.Repository;

import com.alkemy.alkemyProject.entidades.Iconos;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fabi
 */
@Repository
public interface IconosRepository extends JpaRepository<Iconos,Long>,JpaSpecificationExecutor<Iconos>{
  
    @Override
    List<Iconos>findAll(Specification<Iconos> spec);
}

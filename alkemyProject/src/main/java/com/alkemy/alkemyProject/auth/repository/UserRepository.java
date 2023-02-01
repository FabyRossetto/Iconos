/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.auth.repository;

import com.alkemy.alkemyProject.auth.Entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fabi
 */

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

   UserEntity findByUsername(String username);
    
}


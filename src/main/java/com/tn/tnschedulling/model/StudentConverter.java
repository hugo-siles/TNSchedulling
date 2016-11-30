/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tn.tnschedulling.model;

import com.tn.tnschedulling.entities.JpaClasses;
import com.tn.tnschedulling.entities.JpaStudents;

/**
 *
 * @author hugo.siles
 */
public class StudentConverter {
    
    public Students convertJpaToModel(JpaStudents jpaStudent){        
        Students result = new Students();
        result.setId(jpaStudent.getId());
        result.setFirstName(jpaStudent.getFirstname());
        result.setLastName(jpaStudent.getLastname());
        
        for(JpaClasses jpaClass : jpaStudent.getClassesCollection()) {
            Classes aClass = new Classes();
            aClass.setCode(jpaClass.getCode());
            aClass.setTitle(jpaClass.getTitle());            
            aClass.setDescription(jpaClass.getDescription());
            
            result.enrollToClass(aClass);
        }
        
        return result;
    }
    
    public JpaStudents convertModelToJpa(Students aStudent){
        
        JpaStudents result = new JpaStudents();
        result.setId(aStudent.getId());
        result.setFirstname(aStudent.getFirstName());
        result.setLastname(aStudent.getLastName());

        for (Classes aClass : aStudent.getEnrolledClasses()) {
            JpaClasses jpaClass = new JpaClasses();
            jpaClass.setCode(aClass.getCode());
            jpaClass.setTitle(aClass.getTitle());
            jpaClass.setDescription(aClass.getDescription());

            result.getClassesCollection().add(jpaClass);
        }

        return result;
    }
    
}

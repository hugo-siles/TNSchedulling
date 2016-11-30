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
public class ClassConverter {
    
    public Classes convertJpaToModel(JpaClasses jpaClass){        
        Classes result = new Classes();
        result.setCode(jpaClass.getCode());
        result.setTitle(jpaClass.getTitle());
        result.setDescription(jpaClass.getDescription());
        
        for(JpaStudents jpaStudents : jpaClass.getStudentsCollection()){
            Students newStudent = new Students();
            newStudent.setId(jpaStudents.getId());
            newStudent.setFirstName(jpaStudents.getFirstname());
            newStudent.setLastName(jpaStudents.getLastname());
            result.registerStudent(newStudent);            
        }
        
        return result;
    }
    
    public JpaClasses convertModelToJpa(Classes aClass){
        
        JpaClasses result = new JpaClasses();
        result.setCode(aClass.getCode());
        result.setTitle(aClass.getTitle());
        result.setDescription(aClass.getDescription());
        
        for(Students students : aClass.getRegisteredStudents()){
            JpaStudents jpaStudent = new JpaStudents();
            jpaStudent.setId(students.getId());
            jpaStudent.setFirstname(students.getFirstName());
            jpaStudent.setLastname(students.getLastName());
            result.getStudentsCollection().add(jpaStudent);            
        }
        
        return result;
    }
    
}

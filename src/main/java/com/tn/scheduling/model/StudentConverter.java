/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tn.scheduling.model;

import com.tn.scheduling.entities.JpaClass;
import com.tn.scheduling.entities.JpaStudent;

/**
 *
 * @author hugo.siles
 */
public class StudentConverter {
    
    public Student convertJpaToModel(JpaStudent jpaStudent){        
        Student result = new Student();
        result.setId(jpaStudent.getId());
        result.setFirstName(jpaStudent.getFirstname());
        result.setLastName(jpaStudent.getLastname());

        if (jpaStudent.getClassesCollection() != null) {

            for (JpaClass jpaClass : jpaStudent.getClassesCollection()) {
                Class aClass = new Class();
                aClass.setCode(jpaClass.getCode());
                aClass.setTitle(jpaClass.getTitle());
                aClass.setDescription(jpaClass.getDescription());

                result.enrollToClass(aClass);
            }
        }

        return result;
    }
    
    public JpaStudent convertModelToJpa(Student aStudent){
        
        JpaStudent result = new JpaStudent();
        result.setId(aStudent.getId());
        result.setFirstname(aStudent.getFirstName());
        result.setLastname(aStudent.getLastName());

        for (Class aClass : aStudent.getEnrolledClasses()) {
            JpaClass jpaClass = new JpaClass();
            jpaClass.setCode(aClass.getCode());
            jpaClass.setTitle(aClass.getTitle());
            jpaClass.setDescription(aClass.getDescription());

            result.getClassesCollection().add(jpaClass);
        }

        return result;
    }
    
}

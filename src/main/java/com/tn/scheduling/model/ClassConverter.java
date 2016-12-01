/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tn.scheduling.model;

import com.tn.scheduling.entities.JpaClass;
import com.tn.scheduling.entities.JpaStudent;
import com.tn.scheduling.exceptions.ProcessException;

/**
 *
 * @author hugo.siles
 */
public class ClassConverter {

    public Class convertJpaToModel(JpaClass jpaClass) throws ProcessException {

        try {
            Class result = new Class();
            result.setCode(jpaClass.getCode());
            result.setTitle(jpaClass.getTitle());
            result.setDescription(jpaClass.getDescription());
            
            if (jpaClass.getStudentsCollection() != null) {

                for (JpaStudent jpaStudents : jpaClass.getStudentsCollection()) {
                    Student newStudent = new Student();
                    newStudent.setId(jpaStudents.getId());
                    newStudent.setFirstName(jpaStudents.getFirstname());
                    newStudent.setLastName(jpaStudents.getLastname());
                    result.registerStudent(newStudent);
                }
            }

            return result;
        } catch (Exception ex) {
            throw new ProcessException("Exception while converting a class from JPA to Model " + ex.getMessage());

        }
    }

    public JpaClass convertModelToJpa(Class aClass) throws ProcessException {

        try {

            JpaClass result = new JpaClass();
            result.setCode(aClass.getCode());
            result.setTitle(aClass.getTitle());
            result.setDescription(aClass.getDescription());

            for (Student students : aClass.getRegisteredStudents()) {
                JpaStudent jpaStudent = new JpaStudent();
                jpaStudent.setId(students.getId());
                jpaStudent.setFirstname(students.getFirstName());
                jpaStudent.setLastname(students.getLastName());
                result.getStudentsCollection().add(jpaStudent);
            }

            return result;
        } catch (Exception ex) {
            throw new ProcessException("Exception while converting a class from Model to JPA " + ex.getMessage());

        }
    }

}

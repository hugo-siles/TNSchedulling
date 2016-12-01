/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tn.tnscheduling.services;

import com.tn.tnscheduling.entities.JpaClass;
import com.tn.tnscheduling.entities.JpaStudent;
import com.tn.tnscheduling.exceptions.DaoException;
import com.tn.tnscheduling.exceptions.ProcessException;
import com.tn.tnscheduling.model.ClassConverter;
import com.tn.tnscheduling.model.Class;
import com.tn.tnscheduling.model.StudentConverter;
import com.tn.tnscheduling.model.Student;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 *
 * @author hugo.siles
 */
@Stateless
public class SchedulingStudentService extends AbstracService {
    
    @PersistenceContext(unitName = "TNSchedulingPU")
    private EntityManager em;

    public SchedulingStudentService() {
        super(JpaStudent.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public void createStudent(Student entity) 
            throws DaoException {
        try {
            JpaStudent jpatudent = new StudentConverter().convertModelToJpa(entity);
            super.create(jpatudent);
        } catch (PersistenceException pex){
            throw new DaoException("Object already exists in data base " + pex.getMessage());
        }
    }

    public void editStudent(Integer id, Student entity) 
            throws DaoException {
        try {
            JpaStudent jpaClass = new StudentConverter().convertModelToJpa(entity);
            super.edit(jpaClass);
        } catch (PersistenceException pex){
            throw new DaoException("Object already exists in data base " + pex.getMessage());
        }
    }

    public void removeStudent(Integer id) 
            throws DaoException {
        try {
            super.remove(super.find(id));
        } catch (PersistenceException pex){
            throw new DaoException("Object already exists in data base " + pex.getMessage());
        }
    }
    
    public Student findStudentById(Integer code) 
            throws DaoException {
        
        JpaStudent jpaStudent = (JpaStudent) super.find(code);        
        if (jpaStudent == null){
            throw new DaoException("entity not found in DB");
        }        
        
        return new StudentConverter().convertJpaToModel(jpaStudent);
    }

    public List<Student> findAllStudents() {
        List<Student> result = new ArrayList<>();
        
        List<JpaStudent> existingClasses = super.findAll();
        for(JpaStudent jpaClass : existingClasses){
            result.add(new StudentConverter().convertJpaToModel(jpaClass));
        }
        
        return result;       
        
    }

    public List<Class> findClassesForStudent(Integer id) 
            throws DaoException, ProcessException {
        
        List<Class> result = new ArrayList<>();
        
        JpaStudent jpaStudent = retrieveStudentById(id); 
        
        for (JpaClass jpaclass : jpaStudent.getClassesCollection()){
            result.add(new ClassConverter().convertJpaToModel(jpaclass));                    
        }
        
        return result;        
    }    

    public void registerStudentInClass(Integer id, String code) 
            throws DaoException, ProcessException {
        
        JpaStudent jpaStudent = retrieveStudentById(id);
        
        JpaClass jpaClass = retrieveClassByCode(code);
        
        if (!jpaStudent.getClassesCollection().contains(jpaClass)){
            jpaStudent.getClassesCollection().add(jpaClass);
            super.edit(jpaClass);            
        }
        
    }

    public void removeStudentFromClass(Integer id, String code) 
            throws DaoException, ProcessException{
        JpaStudent jpaStudent = retrieveStudentById(id);
        
        JpaClass jpaClass = retrieveClassByCode(code);
        
        if (jpaStudent.getClassesCollection().contains(jpaClass)){
            jpaStudent.getClassesCollection().remove(jpaClass);
            super.edit(jpaClass);            
        }
    }
    
    private JpaStudent retrieveStudentById(Integer id) throws DaoException {
        
        JpaStudent jpaStudent = (JpaStudent) super.find(id);        
        if (jpaStudent == null){
            throw new DaoException("Student not found in DB");
        }
        return jpaStudent;
    }
    
    private JpaClass retrieveClassByCode(String code) throws DaoException {
        JpaClass jpaClass = (JpaClass) getEntityManager().find(JpaClass.class, code);
        if (jpaClass == null){
            throw new DaoException("class not found in DB");
        }
        return jpaClass;
    }
}

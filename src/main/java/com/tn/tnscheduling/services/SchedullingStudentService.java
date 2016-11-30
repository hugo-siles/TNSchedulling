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
public class SchedullingStudentService extends AbstracService {
    
    @PersistenceContext(unitName = "TNSchedullingPU")
    private EntityManager em;

    public SchedullingStudentService() {
        super(JpaStudent.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public void createStudent(Student entity) throws DaoException {
        try {
            JpaStudent jpatudent = new StudentConverter().convertModelToJpa(entity);
            super.create(jpatudent);
        } catch (PersistenceException pex){
            throw new DaoException("Object already exists in data base");
        }
    }

    public void editStudent(Integer id, Student entity) throws DaoException {
        try {
            JpaStudent jpaClass = new StudentConverter().convertModelToJpa(entity);
            super.edit(jpaClass);
        } catch (PersistenceException pex){
            throw new DaoException("Object already exists in data base");
        }
    }

    public void removeStudent(Integer id) throws DaoException {
        try {
            super.remove(id);
        } catch (PersistenceException pex){
            throw new DaoException("Object already exists in data base");
        }
    }
    
    public Student findStudentById(Integer code) throws DaoException {
        
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

    public List<Class> findClassesForStudent(Integer id) throws DaoException, ProcessException {
        
        List<Class> result = new ArrayList<>();
        
        JpaStudent jpaStudent = (JpaStudent) super.find(id);        
        if (jpaStudent == null){
            throw new DaoException("entity not found in DB");
        } 
        
        for (JpaClass jpaclass : jpaStudent.getClassesCollection()){
            result.add(new ClassConverter().convertJpaToModel(jpaclass));
                    
        }
        
        return result;
 
        
    }
}

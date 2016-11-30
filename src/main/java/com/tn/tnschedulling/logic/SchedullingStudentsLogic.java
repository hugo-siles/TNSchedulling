/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tn.tnschedulling.logic;

import com.tn.tnschedulling.entities.JpaClasses;
import com.tn.tnschedulling.entities.JpaStudents;
import com.tn.tnschedulling.exceptions.DaoException;
import com.tn.tnschedulling.model.ClassConverter;
import com.tn.tnschedulling.model.Classes;
import com.tn.tnschedulling.model.StudentConverter;
import com.tn.tnschedulling.model.Students;
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
public class SchedullingStudentsLogic extends AbstracLogic {
    
    @PersistenceContext(unitName = "TNSchedullingPU")
    private EntityManager em;

    public SchedullingStudentsLogic() {
        super(JpaStudents.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public void createStudent(Students entity) throws DaoException {
        try {
            JpaStudents jpatudent = new StudentConverter().convertModelToJpa(entity);
            super.create(jpatudent);
        } catch (PersistenceException pex){
            throw new DaoException("Object already exists in data base");
        }
    }

    public void editStudent(Integer id, Students entity) throws DaoException {
        try {
            JpaStudents jpaClass = new StudentConverter().convertModelToJpa(entity);
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
    
    public Students findStudentById(Integer code) throws DaoException {
        
        JpaStudents jpaStudent = (JpaStudents) super.find(code);        
        if (jpaStudent == null){
            throw new DaoException("entity not found in DB");
        }        
        
        return new StudentConverter().convertJpaToModel(jpaStudent);
    }

    public List<Students> findAllStudents() {
        List<Students> result = new ArrayList<>();
        
        List<JpaStudents> existingClasses = super.findAll();        
        for(JpaStudents jpaClass : existingClasses){
            result.add(new StudentConverter().convertJpaToModel(jpaClass));
        }
        
        return result;       
        
    }

    public List<Classes> findClassesForStudent(Integer id) throws DaoException {
        
        List<Classes> result = new ArrayList<>();
        
        JpaStudents jpaStudent = (JpaStudents) super.find(id);        
        if (jpaStudent == null){
            throw new DaoException("entity not found in DB");
        } 
        
        for (JpaClasses jpaclass : jpaStudent.getClassesCollection()){
            result.add(new ClassConverter().convertJpaToModel(jpaclass));
                    
        }
        
        return result;
 
        
    }
}

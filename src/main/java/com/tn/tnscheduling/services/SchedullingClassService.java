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
import javax.persistence.Query;

/**
 *
 * @author hugo.siles
 */
@Stateless
public class SchedullingClassService extends AbstracService {
    
    @PersistenceContext(unitName = "TNSchedullingPU")
    private EntityManager em;

    public SchedullingClassService() {
        super(JpaClass.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public void createClass(Class entity) throws DaoException, ProcessException {
        try {
            JpaClass jpaClass = new ClassConverter().convertModelToJpa(entity);
            super.create(jpaClass);
        } catch (PersistenceException pex){
            throw new DaoException("Object already exists in data base");
        }
    }

    public void editClass(String id, Class entity) throws DaoException, ProcessException {
        try {
            JpaClass jpaClass = new ClassConverter().convertModelToJpa(entity);
            super.edit(jpaClass);
        } catch (PersistenceException pex){
            throw new DaoException("Object already exists in data base");
        }
    }

    public void removeClass(String id) throws DaoException {
        try {
            super.remove(id);
        } catch (PersistenceException pex){
            throw new DaoException("Object already exists in data base");
        }
    }
    
    public Class findClassByCode(String code) throws DaoException, ProcessException {
        
        JpaClass jpaClass = (JpaClass) super.find(code);        
        if (jpaClass == null){
            throw new DaoException("entity not found in DB");
        }        
        
        return new ClassConverter().convertJpaToModel(jpaClass);
    }

    public List<Class> findAllClasses() throws ProcessException {
        List<Class> result = new ArrayList<>();
        
        List<JpaClass> existingClasses = super.findAll();        
        for(JpaClass jpaClass : existingClasses){
            result.add(new ClassConverter().convertJpaToModel(jpaClass));
        }
        
        return result;       
        
    }

    public List<Student> findStudentsInClass(String code) throws DaoException {
        
        List<Student> result = new ArrayList<>();
        
        Query selectQuery = getEntityManager().createNativeQuery("select s.* from students s, students_classes sc "
                + "where sc.class_code = ?1 and s.id = sc.student_id", JpaStudent.class);
        
        selectQuery.setParameter(1, code);
        List<JpaStudent> studentsInDB = selectQuery.getResultList();
        
        for (JpaStudent jpaStudent : studentsInDB){
            result.add(new StudentConverter().convertJpaToModel(jpaStudent));
                    
        }
        
        return result;
 
        
    }
}

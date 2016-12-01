/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tn.scheduling.services;

import com.tn.scheduling.entities.JpaClass;
import com.tn.scheduling.entities.JpaStudent;
import com.tn.scheduling.exceptions.DaoException;
import com.tn.scheduling.exceptions.ProcessException;
import com.tn.scheduling.model.ClassConverter;
import com.tn.scheduling.model.Class;
import com.tn.scheduling.model.StudentConverter;
import com.tn.scheduling.model.Student;
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
public class SchedulingClassService extends AbstracService {
    
    @PersistenceContext(unitName = "TNSchedulingPU")
    private EntityManager em;

    public SchedulingClassService() {
        super(JpaClass.class);
    }

    SchedulingClassService(EntityManager em) {
        super(JpaClass.class);
        this.em = em;
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
            throw new DaoException("Object already exists in data base " + pex.getMessage());
        }
    }

    public void editClass(String id, Class entity) throws DaoException, ProcessException {
        try {
            JpaClass jpaClass = new ClassConverter().convertModelToJpa(entity);
            super.edit(jpaClass);
        } catch (PersistenceException pex){
            throw new DaoException("Exception while saving class in DB " + pex.getMessage());
        }
    }

    public void removeClass(String id) throws DaoException {
        try {
            super.remove(super.find(id));
        } catch (PersistenceException pex){
            throw new DaoException("Exception while removing object from DB " + pex.getMessage());
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

        try {
            String query = "select s.* from students s, students_classes sc "
                    + "where sc.class_code = ?1 and s.id = sc.student_id";
            Query selectQuery = getEntityManager().createNativeQuery(query, JpaStudent.class);

            selectQuery.setParameter(1, code);
            List<JpaStudent> studentsInDB = selectQuery.getResultList();

            for (JpaStudent jpaStudent : studentsInDB) {
                result.add(new StudentConverter().convertJpaToModel(jpaStudent));
            }

        } catch (PersistenceException pex) {
            throw new DaoException("Exception while retrieving objects from DB " + pex.getMessage());
        }

        return result;

        
    }
}

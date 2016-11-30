/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tn.tnschedulling.logic;

import com.tn.tnschedulling.entities.JpaClasses;
import com.tn.tnschedulling.exceptions.DaoException;
import com.tn.tnschedulling.model.ClassConverter;
import com.tn.tnschedulling.model.Classes;
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
public class SchedullingClassesLogic extends AbstracLogic {
    
    @PersistenceContext(unitName = "TNSchedullingPU")
    private EntityManager em;

    public SchedullingClassesLogic() {
        super(JpaClasses.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public void createClass(Classes entity) throws DaoException {
        try {
            JpaClasses jpaClass = new ClassConverter().convertModelToJpa(entity);
            super.create(jpaClass);
        } catch (PersistenceException pex){
            throw new DaoException("Object already exists in data base");
        }
    }

    public void editClass(String id, Classes entity) throws DaoException {
        try {
            JpaClasses jpaClass = new ClassConverter().convertModelToJpa(entity);
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
    
    public Classes findClassByCode(String code) throws DaoException {
        
        JpaClasses jpaClass = (JpaClasses) super.find(code);        
        if (jpaClass == null){
            throw new DaoException("entity not found in DB");
        }        
        
        return new ClassConverter().convertJpaToModel(jpaClass);
    }

    public List<Classes> findAllClasses() {
        List<Classes> result = new ArrayList<>();
        
        List<JpaClasses> existingClasses = super.findAll();        
        for(JpaClasses jpaClass : existingClasses){
            result.add(new ClassConverter().convertJpaToModel(jpaClass));
        }
        
        return result;       
        
    }
}

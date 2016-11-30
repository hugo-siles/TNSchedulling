/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tn.tnschedulling.service;

import com.tn.tnschedulling.exceptions.DaoException;
import com.tn.tnschedulling.logic.SchedullingClassesLogic;
import com.tn.tnschedulling.model.Classes;
import com.tn.tnschedulling.model.Students;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author hugo.siles
 */
@Stateless
@Path("classes")
public class ClassesResource {
    
    @EJB
    SchedullingClassesLogic logic;

    public ClassesResource() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Classes entity) {
        try {
            logic.createClass(entity);
        } catch (DaoException ex) {
             Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while saving new class", ex);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(@PathParam("id") String id, Classes entity) {
        try {
            logic.editClass(id, entity);
        } catch (DaoException ex) {
             Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while editing existing class", ex);
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        try {
            logic.removeClass(id);
        } catch (DaoException ex) {
             Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while removing class with code: " + id, ex);
        }
    }

    @GET
    @Path("{code}")
    @Produces({MediaType.APPLICATION_XML})
    public Classes find(@PathParam("code") String code) {
        Classes result = null;
        try {
            result = logic.findClassByCode(code);
        } catch (DaoException ex) {
            Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while retrieving information", ex);
        }
        
        return result;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Classes> findAll() {
        return logic.findAllClasses();
    }
    
    @GET
    @Path("findStudentsInClass/{id}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Students> findStudentsInClass(@PathParam("id") Integer id) {
        
        return null;
    }

   
}

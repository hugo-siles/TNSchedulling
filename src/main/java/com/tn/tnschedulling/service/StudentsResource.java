/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tn.tnschedulling.service;

import com.tn.tnschedulling.exceptions.DaoException;
import com.tn.tnschedulling.logic.SchedullingStudentsLogic;
import com.tn.tnschedulling.model.Classes;
import com.tn.tnschedulling.model.Students;
import java.util.ArrayList;
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
@Path("students")
public class StudentsResource {
    
    @EJB
    SchedullingStudentsLogic logic;


    public StudentsResource() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void create(Students entity) {
        try {
            logic.createStudent(entity);
        } catch (DaoException ex) {
             Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while saving new class", ex);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(@PathParam("id") Integer id, Students entity) {
        try {
            logic.editStudent(id, entity);
        } catch (DaoException ex) {
             Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while editing existing class", ex);
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            logic.removeStudent(id);
        } catch (DaoException ex) {
             Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while removing class with code: " + id, ex);
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Students find(@PathParam("id") Integer id) {
        Students result = null;
        try {
            result = logic.findStudentById(id);
        } catch (DaoException ex) {
            Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while retrieving information", ex);
        }
        
        return result;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Students> findAll() {
        return logic.findAllStudents();
    }

    @GET
    @Path("classesForStudent/{id}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Classes> findClassesForStudent(@PathParam("id") Integer id) {
        
        List<Classes> result = new ArrayList<>();
        try {
            result = logic.findClassesForStudent(id);
        } catch (DaoException ex) {
            Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while retrieving information", ex);
        }
        
        return result;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tn.tnschedulling.webResources;

import com.tn.tnschedulling.exceptions.DaoException;
import com.tn.tnschedulling.exceptions.ProcessException;
import com.tn.tnschedulling.services.SchedullingStudentService;
import com.tn.tnschedulling.model.Class;
import com.tn.tnschedulling.model.Student;
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
import javax.ws.rs.core.Response;

/**
 *
 * @author hugo.siles
 */
@Stateless
@Path("students")
public class StudentsResource {
    
    @EJB
    SchedullingStudentService logic;


    public StudentsResource() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public Response create(Student entity) {
        
        Response result = Response.notModified().build();
        try {
            logic.createStudent(entity);
            result = Response.ok().build();
        } catch (DaoException ex) {
             Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while saving new class", ex);
        }
        
        return result;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public Response edit(@PathParam("id") Integer id, Student entity) {
        Response result = Response.notModified().build();
        try {
            logic.editStudent(id, entity);
            result = Response.ok().build();
        } catch (DaoException ex) {
             Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while editing existing class", ex);
        }
        
        return result;
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        Response result = Response.notModified().build();
        try {
            logic.removeStudent(id);
            result = Response.ok().build();
        } catch (DaoException ex) {
             Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while removing class with code: " + id, ex);
        }
        
        return result;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public Student find(@PathParam("id") Integer id) {
        Student result = null;
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
    public List<Student> findAll() {
        return logic.findAllStudents();
    }

    @GET
    @Path("classesForStudent/{id}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Class> findClassesForStudent(@PathParam("id") Integer id) {
        
        List<Class> result = new ArrayList<>();
        try {
            result = logic.findClassesForStudent(id);
        } catch (DaoException ex) {
            Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while retrieving information", ex);
        } catch (ProcessException ex) {
            Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while processing the information obtained ", ex);
        }
        
        return result;
    }

}

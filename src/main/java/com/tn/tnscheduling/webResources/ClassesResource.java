/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tn.tnscheduling.webResources;

import com.tn.tnscheduling.exceptions.DaoException;
import com.tn.tnscheduling.exceptions.ProcessException;
import com.tn.tnscheduling.services.SchedulingClassService;
import com.tn.tnscheduling.model.Class;
import com.tn.tnscheduling.model.Student;
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
@Path("classes")
public class ClassesResource {
    
    @EJB
    SchedulingClassService logic;

    public ClassesResource() {
    }
 
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public Response create(Class entity) {
        Response result = Response.status(Response.Status.NOT_MODIFIED).build();
        try {
            logic.createClass(entity);
            result = Response.status(Response.Status.CREATED).build();
        } catch (DaoException ex) {
             Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while saving new class", ex);
        } catch (ProcessException ex) {
            Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while processing the information obtained ", ex);
        }
        
        return result;
    }

    @PUT
    @Path("{code}")
    @Consumes({MediaType.APPLICATION_XML})
    public Response edit(@PathParam("code") String code, Class entity) {
        
        Response result = Response.status(Response.Status.NOT_MODIFIED).build();
        
        try {
            logic.editClass(code, entity);
            result = Response.status(Response.Status.NO_CONTENT).build();
        } catch (DaoException ex) {
             Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while editing existing class", ex);
        } catch (ProcessException ex) {
            Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while processing the information obtained ", ex);
        }
        
        return result;
    }

    @DELETE
    @Path("{code}")
    public Response remove(@PathParam("code") String code) {
        
        Response result = Response.status(Response.Status.NO_CONTENT).build();
        try {
            logic.removeClass(code);
            result = Response.status(Response.Status.OK).build();
        } catch (DaoException ex) {
             Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while removing class with code: " + code, ex);
        }
        return result;
    }

    @GET
    @Path("{code}")
    @Produces({MediaType.APPLICATION_XML})
    public Class find(@PathParam("code") String code) {
        Class result = null;
        try {
            result = logic.findClassByCode(code);
        } catch (DaoException ex) {
            Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while retrieving information ", ex);
        } catch (ProcessException ex) {
            Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while processing the information obtained ", ex);
        }
        
        return result;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<Class> findAll() {
        List<Class> result = new ArrayList<>();
        try {
            result = logic.findAllClasses();
        } catch (ProcessException ex) {
            Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while processing the information obtained ", ex);
        }
        
        return result;
    }
    
    @GET
    @Path("{code}/student")
    @Produces({MediaType.APPLICATION_XML})
    public List<Student> findStudentsInClass(@PathParam("code") String code) {
        
        List<Student> result = new ArrayList<>();
        try {
            result = logic.findStudentsInClass(code);
        } catch (DaoException ex) {
            Logger.getLogger(ClassesResource.class.getName()).log(Level.SEVERE, 
                    "Exception while retrieving information ", ex);
        }
        
        return result;
 
    }

   
}

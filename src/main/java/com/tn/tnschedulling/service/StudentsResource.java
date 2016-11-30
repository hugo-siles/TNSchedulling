/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tn.tnschedulling.service;

import com.tn.tnschedulling.entities.JpaStudents;
import com.tn.tnschedulling.model.Classes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext(unitName = "TNSchedullingPU")
    private EntityManager em;

    public StudentsResource() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public void create(JpaStudents entity) {
        //super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void edit(@PathParam("id") Integer id, JpaStudents entity) {
        //super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        //super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public JpaStudents find(@PathParam("id") Integer id) {
        //return super.find(id);
        return null;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML})
    public List<JpaStudents> findAll() {
        //return super.findAll();
        return null;
    }

    @GET
    @Path("findClassesForStudent/{id}")
    @Produces({MediaType.APPLICATION_XML})
    public List<Classes> findClassesForStudent(@PathParam("id") Integer id) {
        
        return null;
    }

}

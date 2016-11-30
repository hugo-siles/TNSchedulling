/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tn.tnschedulling.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hugo.siles
 */
@XmlRootElement
public class Classes {
    
    private String code;
    private String title;
    private String description;
    private List<Students> registeredStudents;

    public Classes() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void registerStudent(Students newStudent){
        
        if (registeredStudents.contains(newStudent)){
            return;
        }
        
        registeredStudents.add(newStudent);
    }

    public List<Students> getRegisteredStudents() {
        return Collections.unmodifiableList(registeredStudents);
    }      

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Classes other = (Classes) obj;
        
        return Objects.equals(this.code, other.code);
    }
    
    @Override
    public String toString() {
        return "Classes{" + "code=" + code + ", title=" + title + ", description=" + description + '}';
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tn.tnschedulling.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hugo.siles
 */
@XmlRootElement
public class Students {
    
    private int id;
    private String firstName;
    private String lastName;
    private List<Classes> enrolledClasses = new ArrayList<>();

    public Students() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void enrollToClass(Classes newClass) {
        if (enrolledClasses.contains(newClass)) {
            return;
        }
        
        enrolledClasses.add(newClass);        
    }

    public List<Classes> getEnrolledClasses() {
        return Collections.unmodifiableList(enrolledClasses);
    }
 
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
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
        final Students other = (Students) obj;
        return this.id == other.id;
    }
  
    @Override
    public String toString() {
        return "Students{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
}

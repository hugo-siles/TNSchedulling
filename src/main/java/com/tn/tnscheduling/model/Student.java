/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tn.tnscheduling.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hugo.siles
 */
@XmlRootElement
public class Student {
    
    private int id;
    private String firstName;
    private String lastName;
    private List<Class> enrolledClasses = new ArrayList<>();

    public Student() {
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
    
    public void enrollToClass(Class newClass) {
        if (enrolledClasses.contains(newClass)) {
            return;
        }
        
        enrolledClasses.add(newClass);        
    }

    public List<Class> getEnrolledClasses() {
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
        final Student other = (Student) obj;
        return this.id == other.id;
    }
  
    @Override
    public String toString() {
        return "Students{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }
}

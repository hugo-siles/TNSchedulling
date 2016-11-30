/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tn.tnschedulling.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hugo.siles
 */
@Entity
@Table(name = "students")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaStudents.findAll", query = "SELECT s FROM JpaStudents s")
    , @NamedQuery(name = "JpaStudents.findById", query = "SELECT s FROM JpaStudents s WHERE s.id = :id")
    , @NamedQuery(name = "JpaStudents.findByLastname", query = "SELECT s FROM JpaStudents s WHERE s.lastname = :lastname")
    , @NamedQuery(name = "JpaStudents.findByFirstname", query = "SELECT s FROM JpaStudents s WHERE s.firstname = :firstname")})
public class JpaStudents implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "firstname")
    private String firstname;
    @JoinTable(name = "students_classes", joinColumns = {
        @JoinColumn(name = "student_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "class_code", referencedColumnName = "code")})
    @ManyToMany
    private Collection<JpaClasses> classesCollection;

    public JpaStudents() {
    }

    public JpaStudents(Integer id) {
        this.id = id;
    }

    public JpaStudents(Integer id, String lastname, String firstname) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @XmlTransient
    public Collection<JpaClasses> getClassesCollection() {
        return classesCollection;
    }

    public void setClassesCollection(Collection<JpaClasses> classesCollection) {
        this.classesCollection = classesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JpaStudents)) {
            return false;
        }
        JpaStudents other = (JpaStudents) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.tn.tnschedulling.entities.JpaStudents[ id=" + id + " ]";
    }
    
}

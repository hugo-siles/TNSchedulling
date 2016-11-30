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
@Table(name = "classes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaClasses.findAll", query = "SELECT c FROM JpaClasses c")
    , @NamedQuery(name = "JpaClasses.findByCode", query = "SELECT c FROM JpaClasses c WHERE c.code = :code")
    , @NamedQuery(name = "JpaClasses.findByTitle", query = "SELECT c FROM JpaClasses c WHERE c.title = :title")
    , @NamedQuery(name = "JpaClasses.findByDescription", query = "SELECT c FROM JpaClasses c WHERE c.description = :description")})
public class JpaClasses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "description")
    private String description;
    @ManyToMany(mappedBy = "classesCollection")
    private Collection<JpaStudents> studentsCollection;

    public JpaClasses() {
    }

    public JpaClasses(String code) {
        this.code = code;
    }

    public JpaClasses(String code, String title, String description) {
        this.code = code;
        this.title = title;
        this.description = description;
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

    @XmlTransient
    public Collection<JpaStudents> getStudentsCollection() {
        return studentsCollection;
    }

    public void setStudentsCollection(Collection<JpaStudents> studentsCollection) {
        this.studentsCollection = studentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JpaClasses)) {
            return false;
        }
        JpaClasses other = (JpaClasses) object;
        return !((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code)));
    }

    @Override
    public String toString() {
        return "com.tn.tnschedulling.entities.JpaClasses[ code=" + code + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//by teng in August
package service;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "restolocation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restolocation.findAll", query = "SELECT r FROM Restolocation r")
    , @NamedQuery(name = "Restolocation.findByIdrestoLocation", query = "SELECT r FROM Restolocation r WHERE r.idrestoLocation = :idrestoLocation")
    , @NamedQuery(name = "Restolocation.findByAddress", query = "SELECT r FROM Restolocation r WHERE r.address = :address")})
public class Restolocation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrestoLocation")
    private Integer idrestoLocation;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;

    public Restolocation() {
    }

    public Restolocation(Integer idrestoLocation) {
        this.idrestoLocation = idrestoLocation;
    }

    public Restolocation(Integer idrestoLocation, String address) {
        this.idrestoLocation = idrestoLocation;
        this.address = address;
    }

    public Integer getIdrestoLocation() {
        return idrestoLocation;
    }

    public void setIdrestoLocation(Integer idrestoLocation) {
        this.idrestoLocation = idrestoLocation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrestoLocation != null ? idrestoLocation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restolocation)) {
            return false;
        }
        Restolocation other = (Restolocation) object;
        if ((this.idrestoLocation == null && other.idrestoLocation != null) || (this.idrestoLocation != null && !this.idrestoLocation.equals(other.idrestoLocation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "service.Restolocation[ idrestoLocation=" + idrestoLocation + " ]";
    }
    
}

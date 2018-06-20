/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.entities;

import belly.interfaces.CourseLocalInterface;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author toon1
 */
@Entity
@Table(name = "course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c")
    , @NamedQuery(name = "Course.findById", query = "SELECT c FROM Course c WHERE c.id = :id")
    , @NamedQuery(name = "Course.findByName", query = "SELECT c FROM Course c WHERE c.name = :name")
    , @NamedQuery(name = "Course.findByMaximumPreptime", query = "SELECT c FROM Course c WHERE c.preptime < :preptime")
    , @NamedQuery(name = "Course.findByMaximumPrice", query = "SELECT c FROM Course c WHERE c.price < :price")})
public class Course implements CourseLocalInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "preptime")
    private int preptime;
    @Basic(optional = false)
    @Column(name = "price")
    private int price;
    @Column(name = "picture")
    private String picture;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<OrderCourse> orderCourseList;

    public Course() {
    }

    public Course(Integer id) {
        this.id = id;
    }

    public Course(Integer id, String name, int preptime, int price) {
        this.id = id;
        this.name = name;
        this.preptime = preptime;
        this.price = price;
        this.picture = "IMAGES/default.jpg";
    }

    @Override
    public Integer getId() {return id;}
    @Override
    public void setId(Integer id) {this.id = id;}
    @Override
    public String getName() {return name;}
    @Override
    public void setName(String name) {this.name = name;}
    @Override
    public int getPreptime() {return preptime;}
    @Override
    public void setPreptime(int preptime) {this.preptime = preptime;}
    @Override
    public int getPrice() {return price;}
    @Override
    public void setPrice(int price) {this.price = price;}
    @Override
    public String getPicture() {return picture;}
    @Override
    public void setPicture(String picture) {this.picture = picture;}

    @XmlTransient
    @Override
    public List<OrderCourse> getOrderCourseList() {
        return orderCourseList;
    }

    @Override
    public void setOrderCourseList(List<OrderCourse> orderCourseList) {
        this.orderCourseList = orderCourseList;
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
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name+"(CourseId= " + id + ")";
    }
    
}

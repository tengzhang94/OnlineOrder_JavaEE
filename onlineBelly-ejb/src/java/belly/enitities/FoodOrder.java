/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author toon1
 */
@Entity
@Table(name = "FOODORDER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foodorder.findAll", query = "SELECT f FROM FoodOrder f")
    , @NamedQuery(name = "Foodorder.findByPerson", query = "SELECT f FROM FoodOrder f WHERE f.personid = :personid")
    , @NamedQuery(name = "Foodorder.findByPersonOpen", query = "SELECT f FROM FoodOrder f WHERE f.personid = :personid AND f.complete = FALSE")
    , @NamedQuery(name = "Foodorder.findById", query = "SELECT f FROM FoodOrder f WHERE f.id = :id")
    , @NamedQuery(name = "Foodorder.findByTimeSince", query = "SELECT f FROM FoodOrder f WHERE f.orderTime > :startTime")
    , @NamedQuery(name = "Foodorder.findByComplete", query = "SELECT f FROM FoodOrder f WHERE f.complete = :complete")})
public class FoodOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID",nullable =false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ORDERTIME")
    @Temporal(TemporalType.DATE)
    private Date orderTime;
    @Basic(optional = false)
    @Column(name = "COMPLETE")
    private Boolean complete;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodorder")
    private List<OrderCourse> orderCourseList;
    @JoinColumn(name = "PERSONID", referencedColumnName = "ID")
    @ManyToOne
    private Person personid;

    public FoodOrder() {
    }

    public FoodOrder(Person customer) {
        this.personid = customer;
        this.orderTime = new Date();
        this.orderCourseList = new ArrayList<>();
        this.complete = false;
    }
    
    public void addCourse(Course course)
    {
        Optional<OrderCourse> orderCourse;        
        
        orderCourse = orderCourseList.stream().filter((oc) ->(oc.getCourse().equals(course))).findFirst();
        if (!orderCourse.isPresent())
        {
            orderCourseList.add(new OrderCourse(id,course.getId()));
        }
        else
        {
            orderCourse.get().increaseCount();
        }
    }
    public void removeCourse(Course course)
    {
        Optional<OrderCourse> orderCourse;
        
        orderCourse = orderCourseList.stream().filter((oc) ->(oc.getCourse().equals(course))).findFirst();
        if (orderCourse.isPresent() & (orderCourse.get().decreaseCount()==0))       //case course is no more desired
        {
            orderCourseList.remove(orderCourse.get());
        }
    }
    
    public Integer getId() {return id;}
    public Date getOrderTime() {return orderTime;}
    public void setOrderTime(Date orderTime) {this.orderTime = orderTime;}
    public Boolean getComplete() {return complete;}
    public void setComplete(Boolean complete) {this.complete = complete;}
    @XmlTransient
    public List<OrderCourse> getOrderCourseList() {return orderCourseList;}
    public void setOrderCourseList(List<OrderCourse> orderCourseList) {this.orderCourseList = orderCourseList;}
    public Person getPersonid() {return personid;}
    public void setPersonid(Person personid) {this.personid = personid;}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FoodOrder)) {
            return false;
        }
        FoodOrder other = (FoodOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order " + id;
    }
    
}

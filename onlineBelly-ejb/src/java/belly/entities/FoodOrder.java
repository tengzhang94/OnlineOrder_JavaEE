/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.entities;

import belly.interfaces.CourseLocalInterface;
import belly.interfaces.FoodOrderLocalInterface;
import belly.interfaces.OrderCourseLocalInterface;
import belly.interfaces.PersonLocalInterface;
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
@Table(name = "foodorder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FoodOrder.findAll", query = "SELECT f FROM FoodOrder f")
    , @NamedQuery(name = "FoodOrder.findByPerson", query = "SELECT f FROM FoodOrder f WHERE f.personID = :personID")
    , @NamedQuery(name = "FoodOrder.findByPersonOpen", query = "SELECT f FROM FoodOrder f WHERE f.personID = :personID AND f.complete = 0")
    , @NamedQuery(name = "FoodOrder.findById", query = "SELECT f FROM FoodOrder f WHERE f.id = :id")
    , @NamedQuery(name = "FoodOrder.findByTimeSince", query = "SELECT f FROM FoodOrder f WHERE f.ordertime > :startTime")
    , @NamedQuery(name = "FoodOrder.findByComplete", query = "SELECT f FROM FoodOrder f WHERE f.complete = :complete")})
public class FoodOrder implements FoodOrderLocalInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ordertime")
    @Temporal(TemporalType.DATE)
    private Date ordertime;
    @Basic(optional = false)
    @Column(name = "complete")
    private short complete;
    @JoinColumn(name = "personID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Person personID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodOrder")
    private List<OrderCourse> orderCourseList;

    public FoodOrder() {
    }

    public FoodOrder(Person customer) {
        this.personID = customer;
        this.ordertime = new Date();
        this.orderCourseList = new ArrayList<>();
        this.complete = 0;
    }
    
    @Override
    public void addCourse(Course course)
    {
        Optional<OrderCourse> orderCourse;        
        
        orderCourse = orderCourseList.stream().filter((oc) ->(oc.getCourse().equals(course))).findFirst();
        //System.out.println("add: "+course);
        if (!orderCourse.isPresent())
        {
            orderCourseList.add((OrderCourse) new OrderCourse(this,course));
        }
        else
        {
            orderCourse.get().increaseCount();            
        }
    }
    @Override
    public void removeCourse(Course course)
    {
        Optional<OrderCourse> orderCourse;
        
        orderCourse = orderCourseList.stream().filter((oc) ->(oc.getCourse().equals(course))).findFirst();
        //System.out.println("remove: "+course);
        if (orderCourse.isPresent())       
        {
            if (orderCourse.get().decreaseCount()==0) {
                orderCourseList.remove(orderCourse.get());
            }
        }
        //case course is no more desired
    }

    @Override
    public Integer getId() {return id;}
    @Override
    public Date getOrdertime() {return ordertime;}
    @Override
    public void setOrdertime(Date ordertime) {this.ordertime = ordertime;}
    @Override
    public short getComplete() {return complete;}
    @Override
    public void setComplete(short complete) {this.complete = complete;}
    @Override
    public Person getPersonID() {return personID;}
    @Override
    public void setPersonID(Person personID) {this.personID = personID;}

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

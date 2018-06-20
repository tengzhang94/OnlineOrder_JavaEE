/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.entities;

import belly.interfaces.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author toon1
 */
@Entity
@Table(name = "ordercourse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderCourse.findAll", query = "SELECT o FROM OrderCourse o")
    , @NamedQuery(name = "OrderCourse.findByOrderId", query = "SELECT o FROM OrderCourse o WHERE o.orderCoursePK.orderId = :orderId")
    , @NamedQuery(name = "OrderCourse.findByCourseId", query = "SELECT o FROM OrderCourse o WHERE o.orderCoursePK.courseId = :courseId")})
public class OrderCourse implements OrderCourseLocalInterface {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderCoursePKLocalInterface orderCoursePK;
    @Basic(optional = false)
    @Column(name = "count")
    private int count;
    @JoinColumn(name = "courseId", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CourseLocalInterface course;
    @JoinColumn(name = "orderId", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FoodOrderLocalInterface foodOrder;

    public OrderCourse() {
    }

    public OrderCourse(OrderCoursePKLocalInterface orderCoursePK) {
        this.orderCoursePK = orderCoursePK;
        this.count = 1;
    }

    public OrderCourse(int orderid, int courseid) {
        this.orderCoursePK = new OrderCoursePK(orderid, courseid);
        this.count = 1;
        this.course = new Course(orderid, "koe",15,3);
    }
    
    
    @Override
    public int increaseCount(){return ++this.count;}
    @Override
    public int decreaseCount(){return --this.count;}
    @Override
    public OrderCoursePK getOrderCoursePK() {return orderCoursePK;}
    @Override
    public void setOrderCoursePK(OrderCoursePK orderCoursePK) {this.orderCoursePK = orderCoursePK;}
    @Override
    public int getCount() {return count;}
    @Override
    public void setCount(int count) {this.count = count;}
    @Override
    public Course getCourse() {return course;}
    @Override
    public void setCourse(Course course) {this.course = course;}
    @Override
    public FoodOrder getFoodOrder() {return foodOrder;}
    @Override
    public void setFoodOrder(FoodOrder foodOrder) {this.foodOrder = foodOrder;}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderCoursePK != null ? orderCoursePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderCourse)) {
            return false;
        }
        OrderCourse other = (OrderCourse) object;
        if ((this.orderCoursePK == null && other.orderCoursePK != null) || (this.orderCoursePK != null && !this.orderCoursePK.equals(other.orderCoursePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderCourse " + orderCoursePK;
    }

    
}

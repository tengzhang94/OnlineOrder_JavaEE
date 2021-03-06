/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import belly.interfaces.OrderCoursePKLocalInterface;

/**
 *
 * @author toon1
 */
@Embeddable
public class OrderCoursePK implements OrderCoursePKLocalInterface {

    @Basic(optional = false)
    @Column(name = "orderId")
    private int orderId;
    @Basic(optional = false)
    @Column(name = "courseId")
    private int courseId;

    public OrderCoursePK() {
    }

    public OrderCoursePK(int orderId, int courseId) {
        this.orderId = orderId;
        this.courseId = courseId;
    }

    @Override
    public int getOrderId() {
        return orderId;
    }

    @Override
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public int getCourseId() {
        return courseId;
    }

    @Override
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) orderId;
        hash += (int) courseId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderCoursePK)) {
            return false;
        }
        OrderCoursePK other = (OrderCoursePK) object;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.courseId != other.courseId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderCoursePK[ orderId=" + orderId + ", courseId=" + courseId + " ]";
    }
    
}

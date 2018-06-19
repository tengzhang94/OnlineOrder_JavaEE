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

/**
 *
 * @author toon1
 */
@Embeddable
public class OrderCoursePK implements Serializable {

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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCourseId() {
        return courseId;
    }

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
        return "belly.entities.OrderCoursePK[ orderId=" + orderId + ", courseId=" + courseId + " ]";
    }
    
}

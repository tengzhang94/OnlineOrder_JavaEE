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
    @Column(name = "ORDERID")
    private int orderid;
    @Basic(optional = false)
    @Column(name = "COURSEID")
    private int courseid;

    public OrderCoursePK() {
    }

    public OrderCoursePK(int orderid, int courseid) {
        this.orderid = orderid;
        this.courseid = courseid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) orderid;
        hash += (int) courseid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderCoursePK)) {
            return false;
        }
        OrderCoursePK other = (OrderCoursePK) object;
        if (this.orderid != other.orderid) {
            return false;
        }
        if (this.courseid != other.courseid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ orderid=" + orderid + ", courseid=" + courseid + " ]";
    }
    
}

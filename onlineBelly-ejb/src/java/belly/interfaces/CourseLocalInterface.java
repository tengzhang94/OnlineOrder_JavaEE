/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.interfaces;

import belly.entities.OrderCourse;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author toon1
 */
public interface CourseLocalInterface extends Serializable {

    Integer getId();
    String getName();
    void setName(String name);
    String getPicture();
    void setPicture(String picture);
    int getPreptime();
    void setPreptime(int preptime);
    int getPrice();
    void setPrice(int price);
    @XmlTransient
    List<OrderCourse> getOrderCourseList();
    void setOrderCourseList(List<OrderCourse> orderCourseList);
    
    @Override
    boolean equals(Object object);
    @Override
    int hashCode();
    @Override
    String toString();
    
}

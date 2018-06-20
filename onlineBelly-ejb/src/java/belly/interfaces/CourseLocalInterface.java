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

    boolean equals(Object object);

    Integer getId();

    String getName();

    @XmlTransient
    List<OrderCourse> getOrderCourseList();

    String getPicture();

    int getPreptime();

    int getPrice();

    int hashCode();

    void setId(Integer id);

    void setName(String name);

    void setOrderCourseList(List<OrderCourse> orderCourseList);

    void setPicture(String picture);

    void setPreptime(int preptime);

    void setPrice(int price);

    String toString();
    
}

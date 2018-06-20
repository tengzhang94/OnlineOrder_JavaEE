/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.interfaces;

import belly.entities.OrderCourse;
import belly.entities.Course;
import belly.entities.Person;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author toon1
 */
public interface FoodOrderLocalInterface extends Serializable {

    void addCourse(Course course);
    void removeCourse(Course course);

    Integer getId();
    short getComplete();
    void setComplete(short complete);
    Date getOrdertime();
    void setOrdertime(Date ordertime);
    Person getPersonID();
    void setPersonID(Person personID);
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

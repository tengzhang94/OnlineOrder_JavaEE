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

    boolean equals(Object object);

    short getComplete();

    Integer getId();

    @XmlTransient
    List<OrderCourse> getOrderCourseList();

    Date getOrdertime();

    Person getPersonID();

    int hashCode();

    void removeCourse(Course course);

    void setComplete(short complete);

    void setId(Integer id);

    void setOrderCourseList(List<OrderCourse> orderCourseList);

    void setOrdertime(Date ordertime);

    void setPersonID(Person personID);

    String toString();
    
}

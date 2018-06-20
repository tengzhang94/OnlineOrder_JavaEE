/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.interfaces;

import belly.entities.Course;
import belly.entities.FoodOrder;
import belly.entities.Person;
import java.io.Serializable;

/**
 *
 * @author toon1
 */
public interface CustomerSessionBeanLocal extends Serializable {

    /**
     * set the order on confirmed and persist in database
     * @return amount of time to wait fo the delivery
     */
    int confirmOrder();

    Person getCustomer();
    FoodOrder getOrder();
    int getTotalPrice();
    FoodOrder orderCourse(Course newCourse, int amount);
    void persist(Object object);
    FoodOrder removeCourse(Course whatCourse, int amount);
    void setCustomer(Person customer);
    void setOrder(FoodOrder order);
    FoodOrder viewOrder();
    
}

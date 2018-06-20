/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.interfaces;

import belly.entities.*;
import java.io.Serializable;

/**
 *
 * @author toon1
 */
public interface CustomerSessionBeanLocal extends Serializable {

    FoodOrder getLatestOrder(Person customer);int confirmOrder();
    FoodOrder removeCourse(Course whatCourse, int amount);
    FoodOrder orderCourse(Course newCourse, int amount);
    int getTotalPrice();
    void setCustomer(Person customer);
    void setOrder(FoodOrder order);
    FoodOrder getOrder();
    Person getCustomer();
    
    void persist(Object object);
    
}

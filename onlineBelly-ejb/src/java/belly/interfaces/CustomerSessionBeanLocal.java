/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.interfaces;

import belly.entities.*;
import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author toon1
 */
@Local
public interface CustomerSessionBeanLocal extends Serializable {

    FoodOrder unConfirmedOrder(Person customer);
    void confirmOrder();
    FoodOrder removeCourse(Course whatCourse, int amount);
    FoodOrder orderCourse(Course newCourse, int amount);
    int getTotalPrice();
    int getDuration();
    
    
    void setCustomer(Person customer);
    void setOrder(FoodOrder order);
    FoodOrder getOrder();
    Person getCustomer();
    
    void persist(Object object);
    
}

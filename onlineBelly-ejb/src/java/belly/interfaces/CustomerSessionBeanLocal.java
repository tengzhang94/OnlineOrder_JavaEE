/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.interfaces;

import java.io.Serializable;

/**
 *
 * @author toon1
 */
public interface CustomerSessionBeanLocal extends Serializable {

    FoodOrderLocalInterface getLatestOrder(PersonLocalInterface customer);int confirmOrder();
    FoodOrderLocalInterface removeCourse(CourseLocalInterface whatCourse, int amount);
    FoodOrderLocalInterface orderCourse(CourseLocalInterface newCourse, int amount);
    int getTotalPrice();

    void setCustomer(PersonLocalInterface customer);
    PersonLocalInterface getCustomer();
    void setOrder(FoodOrderLocalInterface order);
    FoodOrderLocalInterface getOrder();
    
    void persist(Object object);
    
}

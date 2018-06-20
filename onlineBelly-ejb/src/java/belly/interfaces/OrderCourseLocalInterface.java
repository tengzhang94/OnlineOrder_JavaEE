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
public interface OrderCourseLocalInterface extends Serializable {

    int decreaseCount();

    boolean equals(Object object);

    int getCount();

    Course getCourse();

    FoodOrder getFoodOrder();

    OrderCoursePK getOrderCoursePK();

    int hashCode();

    int increaseCount();

    void setCount(int count);

    void setCourse(Course course);

    void setFoodOrder(FoodOrder foodOrder);

    void setOrderCoursePK(OrderCoursePK orderCoursePK);

    String toString();
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.interfaces;

import belly.entities.OrderCoursePK;
import java.io.Serializable;

/**
 *
 * @author toon1
 */
public interface OrderCourseLocalInterface extends Serializable {

    int decreaseCount();
    int increaseCount();
    
    OrderCoursePKLocalInterface getOrderCoursePK();
    void setOrderCoursePK(OrderCoursePKLocalInterface orderCoursePK);int getCount();
    void setCount(int count);
    CourseLocalInterface getCourse();
    void setCourse(CourseLocalInterface course);
    FoodOrderLocalInterface getFoodOrder();
    void setFoodOrder(FoodOrderLocalInterface foodOrder);

    @Override
    boolean equals(Object object);
    @Override
    int hashCode();
    @Override
    String toString();    
}

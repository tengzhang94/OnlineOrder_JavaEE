/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.interfaces;

import belly.entities.Course;
import belly.entities.FoodOrder;
import belly.entities.OrderCoursePK;
import java.io.Serializable;

/**
 *
 * @author toon1
 */
public interface OrderCourseLocalInterface extends Serializable {

    int decreaseCount();
    int increaseCount();
    
    OrderCoursePK getOrderCoursePK();
    void setOrderCoursePK(OrderCoursePK orderCoursePK);int getCount();
    void setCount(int count);
    Course getCourse();
    void setCourse(Course course);
    FoodOrder getFoodOrder();
    void setFoodOrder(FoodOrder foodOrder);

    @Override
    boolean equals(Object object);
    @Override
    int hashCode();
    @Override
    String toString();    
}

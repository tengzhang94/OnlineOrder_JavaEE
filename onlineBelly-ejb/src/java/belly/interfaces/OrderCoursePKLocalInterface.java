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
public interface OrderCoursePKLocalInterface extends Serializable {

    int getCourseId();
    void setCourseId(int courseId);
    int getOrderId();
    void setOrderId(int orderId);
    
    @Override
    boolean equals(Object object);
    @Override
    int hashCode();
    @Override
    String toString();
    
}

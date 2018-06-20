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
public interface OrderCoursePKLoclInterface extends Serializable {

    boolean equals(Object object);

    int getCourseId();

    int getOrderId();

    int hashCode();

    void setCourseId(int courseId);

    void setOrderId(int orderId);

    String toString();
    
}

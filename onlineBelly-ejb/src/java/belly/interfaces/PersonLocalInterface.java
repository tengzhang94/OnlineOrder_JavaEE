/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.interfaces;

import belly.entities.FoodOrder;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author toon1
 */
public interface PersonLocalInterface extends Serializable {

    Integer getId();
    String getLogin();
    void setLogin(String login);
    String getName();
    void setName(String name);
    String getPassword();
    void setPassword(String password);    @XmlTransient
    List<FoodOrder> getFoodOrderList();
    void setFoodOrderList(List<FoodOrder> foodOrderList);
    
    @Override
    boolean equals(Object object);
    @Override
    int hashCode();
    @Override
    String toString();
    
}

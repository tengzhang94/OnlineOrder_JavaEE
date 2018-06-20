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

    boolean equals(Object object);

    @XmlTransient
    List<FoodOrder> getFoodOrderList();

    Integer getId();

    String getLogin();

    String getName();

    String getPassword();

    int hashCode();

    void setFoodOrderList(List<FoodOrder> foodOrderList);

    void setId(Integer id);

    void setLogin(String login);

    void setName(String name);

    void setPassword(String password);

    String toString();
    
}

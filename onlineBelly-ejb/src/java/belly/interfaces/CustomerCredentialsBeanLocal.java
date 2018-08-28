/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.interfaces;

import belly.entities.FoodOrder;
import belly.entities.Person;
import belly.exceptions.InvalidCredentialsException;
import belly.exceptions.NotUniqueCredentialsException;
import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author toon1
 */
@Local
public interface CustomerCredentialsBeanLocal extends Serializable {

    Person loginCustomer(String loginName, String password) throws InvalidCredentialsException;
    Person registerCustomer(String loginName, String password, String personName) throws NotUniqueCredentialsException;
    Person validateCredentials(String loginName, String password) throws InvalidCredentialsException;  
    
    Person anonomyLogin(String loginName, String password) throws InvalidCredentialsException;
    
    void persist(Object object);    
}

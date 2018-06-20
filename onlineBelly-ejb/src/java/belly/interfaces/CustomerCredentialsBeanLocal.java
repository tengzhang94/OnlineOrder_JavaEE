/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.interfaces;

import belly.entities.Person;
import belly.exceptions.InvalidCredentialsException;
import belly.exceptions.NotUniqueCredentialsException;
import java.io.Serializable;

/**
 *
 * @author toon1
 */
public interface CustomerCredentialsBeanLocal extends Serializable {

    Person loginCustomer(String loginName, String password) throws InvalidCredentialsException;
    Person registerCustomer(String loginName, String password, String personName) throws NotUniqueCredentialsException;
    
    void persist(Object object);    
}

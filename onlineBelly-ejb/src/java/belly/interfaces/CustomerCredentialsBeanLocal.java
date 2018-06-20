/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.interfaces;

import belly.ejb.CustomerSessionBean;
import belly.exceptions.InvalidCredentialsException;
import belly.exceptions.NotUniqueCredentialsException;
import java.io.Serializable;

/**
 *
 * @author toon1
 */
public interface CustomerCredentialsBeanLocal extends Serializable {

    /**
     * @param loginName username in Person table
     * @param password hashed password in Person table
     * @return statefull session bean for the customer session
     * @throws InvalidCredentialsException in case the person entry doe not exist
     **/
    CustomerSessionBean loginCustomer(String loginName, String password) throws InvalidCredentialsException;

    void persist(Object object);

    /**
     * @param loginName username in Person table
     * @param password hashed password in Person table
     * @param personName nickname for this customer
     * @return new session with an order loaded
     * @throws NotUniqueCredentialsException in case the person cant be created due to parameter constraints
     **/
    CustomerSessionBean registerCustomer(String loginName, String password, String personName) throws NotUniqueCredentialsException;
    
}

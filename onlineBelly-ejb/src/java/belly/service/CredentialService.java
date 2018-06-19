/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.service;

import belly.ejb.CustomerCredentialsBean;
import belly.ejb.CustomerSessionBean;
import belly.exceptions.InvalidCredentialsException;
import belly.exceptions.NotUniqueCredentialsException;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Teng
 */
@WebService(serviceName = "CredentialService")
@Stateless()
public class CredentialService {

    @EJB
    private CustomerCredentialsBean ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "loginCustomer")
    public CustomerSessionBean loginCustomer(@WebParam(name = "loginName") String loginName, @WebParam(name = "password") byte[] password) throws InvalidCredentialsException {
        return ejbRef.loginCustomer(loginName, password);
    }

    @WebMethod(operationName = "registerCustomer")
    public CustomerSessionBean registerCustomer(@WebParam(name = "loginName") String loginName, @WebParam(name = "password") byte[] password, @WebParam(name = "personName") String personName) throws NotUniqueCredentialsException {
        return ejbRef.registerCustomer(loginName, password, personName);
    }

    @WebMethod(operationName = "persist")
    @Oneway
    public void persist(@WebParam(name = "object") Object object) {
        ejbRef.persist(object);
    }
    
}

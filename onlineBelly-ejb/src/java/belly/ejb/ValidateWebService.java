/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.ejb;

import belly.entities.FoodOrder;
import belly.entities.Person;
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
 * @author dell
 */
@WebService(serviceName = "ValidateWebService")
@Stateless()
public class ValidateWebService {

    @EJB
    private CustomerCredentialsBean ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "loginCustomer")
    public CustomerSessionBean loginCustomer(@WebParam(name = "loginName") String loginName, @WebParam(name = "password") String password) throws InvalidCredentialsException {
        return ejbRef.loginCustomer(loginName, password);
    }

    @WebMethod(operationName = "registerCustomer")
    public CustomerSessionBean registerCustomer(@WebParam(name = "loginName") String loginName, @WebParam(name = "password") String password, @WebParam(name = "personName") String personName) throws NotUniqueCredentialsException {
        return ejbRef.registerCustomer(loginName, password, personName);
    }

    @WebMethod(operationName = "validateCredentials")
    public Person validateCredentials(@WebParam(name = "loginName") String loginName, @WebParam(name = "password") String password) throws InvalidCredentialsException {
        return ejbRef.validateCredentials(loginName, password);
    }

    @WebMethod(operationName = "getLatestOrder")
    public FoodOrder getLatestOrder(@WebParam(name = "customer") Person customer) {
        return ejbRef.getLatestOrder(customer);
    }

    @WebMethod(operationName = "persist")
    @Oneway
    public void persist(@WebParam(name = "object") Object object) {
        ejbRef.persist(object);
    }
    
}

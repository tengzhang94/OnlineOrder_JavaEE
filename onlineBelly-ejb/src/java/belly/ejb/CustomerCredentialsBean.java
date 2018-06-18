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
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author toon1
 */
@Stateless
@LocalBean
public class CustomerCredentialsBean {

    @PersistenceContext(unitName = "onlineBelly-ejbPU")
    private EntityManager em;
    
    /**
     * @param loginName username in Person table
     * @param password hashed password in Person table
     * @return statefull session bean for the customer session
     * @throws InvalidCredentialsException in case the person entry doe not exist
     **/
    public CustomerSessionBean loginCustomer(String loginName, byte[] password) throws InvalidCredentialsException {
        Person customer;
        FoodOrder myOrder;        
        try
        {
            customer = validateCredentials(loginName,password);
            myOrder = getLatestOrder(customer);
            return new CustomerSessionBean(customer,myOrder);
        }
        catch (InvalidCredentialsException e)
        {
            throw e;
        }
    }
    /**
     * @param loginName username in Person table
     * @param password hashed password in Person table
     * @param personName nickname for this customer
     * @return new session with an order loaded
     * @throws NotUniqueCredentialsException in case the person cant be created due to parameter constraints
     **/
    public CustomerSessionBean registerCustomer(String loginName, byte[] password, String personName) throws NotUniqueCredentialsException {
        
        Person customer;
        FoodOrder myOrder;
        CustomerSessionBean newSession;
        // check if loginName is unique, otherwise throw exception
        try
        {
            Query query = em.createNamedQuery("Person.findByLogin");
            query.setParameter("login", loginName);
            query.getSingleResult();
            throw new NotUniqueCredentialsException();
        }
        catch(NoResultException e)          
        {
            //case the loginname is unique, so continue
        }       
        
        customer = new Person(personName,loginName,password);       //create a new person
        em.persist(customer);
        myOrder = new FoodOrder(customer);                          //create a new order
        em.persist(myOrder);
        newSession = new CustomerSessionBean(customer,myOrder);
        
        return newSession;
    }
    /**
     * @param loginName username in Person table
     * @param password hashed password in Person table
     * @return boolean to indicate existe,ce or not
     * @throws InvalidCredentialsException in case the person entry doe not exist
     **/
    private Person validateCredentials(String loginName, byte[] password) throws InvalidCredentialsException {
        
        Query query = em.createNamedQuery("Person.findByCredentials");
        query.setParameter("login", loginName);
        query.setParameter("password", password);
        try
        {
            return (Person) query.getSingleResult();
        }
        catch(Exception e)                              //case invalid credentials
        {
            throw new InvalidCredentialsException();
        }
    }
    /**
     * @param customer enity in database to retrieve lastest order
     * @return order that has not been finished or a new order
     * @throws InvalidCredentialsException in case the person entry doe not exist
     **/
    private FoodOrder getLatestOrder(Person customer) {
        // check if the is any unfinished order, otherwise create a new one
        // return that order   
        FoodOrder myOrder;
        
        Query query = em.createNamedQuery("FoodOrder.findByPersonOpen");
        query.setParameter("personid",customer.getId());
        
        try
        {
            myOrder = (FoodOrder) query.getSingleResult();
        }
        catch (Exception e)
        {
            myOrder = new FoodOrder(customer);
            em.persist(myOrder);
        }
        
        return myOrder;
    }
    
    public void persist(Object object) {
        em.persist(object);
    }
 }

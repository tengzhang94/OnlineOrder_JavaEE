/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.ejb;

import belly.interfaces.CustomerCredentialsBeanLocal;
import belly.entities.*;
import belly.exceptions.InvalidCredentialsException;
import belly.exceptions.NotUniqueCredentialsException;
import belly.interfaces.PersonLocalInterface;
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
public class CustomerCredentialsBean implements CustomerCredentialsBeanLocal {

    @PersistenceContext(unitName = "onlineBelly-ejbPU")
    private EntityManager em;
    
    /**
     * @param loginName username in Person table
     * @param password hashed password in Person table
     * @return customer object
     * @throws InvalidCredentialsException in case the person entry doe not exist
     **/
    @Override
    public Person loginCustomer(String loginName, String password) throws InvalidCredentialsException {
        Person customer;        
        try
        {
            customer = validateCredentials(loginName,password);
            System.out.println("retrieved :" +customer);
            return customer;
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
    @Override
    public Person registerCustomer(String loginName, String password, String personName) throws NotUniqueCredentialsException {
        
        Person customer;
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
        
        return customer;
    }
    /**
     * @param loginName username in Person table
     * @param password hashed password in Person table
     * @return boolean to indicate existe,ce or not
     * @throws InvalidCredentialsException in case the person entry doe not exist
     **/
    public Person validateCredentials(String loginName, String password) throws InvalidCredentialsException {
        
        Query query = em.createNamedQuery("Person.findByCredentials");
        query.setParameter("login", loginName);
        query.setParameter("password", password);
        try
        {
            System.out.println("persons found :" +query.getResultList());
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
    public FoodOrder getLatestOrder(Person customer) {
        // check if the is any unfinished order, otherwise create a new one
        // return that order   
        FoodOrder myOrder;
        
        Query query = em.createNamedQuery("FoodOrder.findByPersonOpen");
        query.setParameter("personID",customer);
        
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

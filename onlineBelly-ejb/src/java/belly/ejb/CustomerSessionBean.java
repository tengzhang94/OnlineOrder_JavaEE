/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.ejb;

import belly.interfaces.CustomerSessionBeanLocal;
import belly.entities.*;
import java.util.NoSuchElementException;
import javax.annotation.PreDestroy;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author toon1
 */
@Stateful
public class CustomerSessionBean implements CustomerSessionBeanLocal {

    
    @PersistenceContext(unitName = "onlineBelly-ejbPU")
    private EntityManager em;
    
    private FoodOrder order;
    private Person customer;
    
    public CustomerSessionBean() {
    }
    public CustomerSessionBean(Person customer, FoodOrder order) {
        this.customer = customer;
        this.order = order;        
    }

    @Override
    public FoodOrder unConfirmedOrder(Person customer) {
        
        Query query = em.createNamedQuery("FoodOrder.findByPersonOpen");
        query.setParameter("personID",customer);
        
        try
        {
            this.order = (FoodOrder) query.getSingleResult();
        }
        catch (Exception e)
        {
            this.order = new FoodOrder(customer);
            em.persist(this.order);
        }        
        return this.order;
    }
    // by teng in August
    // when an order is made then return from comment page
    // if we simply get this.order the previous confirmed order will show up again
    // so we need to use setLatestOrder to get order this customer
    // has not confirmed yet
    @Override
    public FoodOrder getOrder() {return unConfirmedOrder(this.customer);}
    @Override
    public void setOrder(FoodOrder order) {
        this.order = order;
    }
    @Override
    public Person getCustomer(){return this.customer;}    
    @Override
    public void setCustomer(Person customer) {
        this.customer = customer;
    }
    
    @Override
    public FoodOrder orderCourse(Course newCourse, int amount) {
        
        for (int i = 0;i<amount;i++){
            order.addCourse(newCourse);
        }
        // by teng in Augustfinter
        // so everytime it adds, it is already saved in the database
        // if another logs in then this user logs in again the order is still there
        // otherwise it would be lost
        em.merge(order);
        return order;
    }

    @Override
    public FoodOrder removeCourse(Course whatCourse, int amount) {
        for (int i = 0;i<amount;i++){
            order.removeCourse(whatCourse);
        }
        // by teng in August
        // so everytime it decreases, it is already saved in the database
        // if another logs in then this user logs in again the order is still there
        em.merge(order);
        return order;
    }

    @Override
    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public void confirmOrder() {
        //indicate the order as completed
        order.setComplete((short) 1);
        em.merge(order);                //save changes to database
    }
    
    @Override
    public int getTotalPrice() {
        int totalPrice;        
        try{
            totalPrice = order.getOrderCourseList().stream().mapToInt(oc -> (oc.getCount()*oc.getCourse().getPrice())).sum();            
            return totalPrice;
        }
        catch(NoSuchElementException e)
        {
                //System.out.println("no orders yet");
                return 0;
        }
    }
    
    @Override
    public int getDuration() {
        int waitTime;        
        
        try{
            waitTime = order.getOrderCourseList().stream().mapToInt(oc -> oc.getCourse().getPreptime()).max().getAsInt();
            return waitTime;
        }
        catch(NoSuchElementException e)
        {
                System.out.println("no orders yet");
                return 0;
        }
    }
    
    @PreDestroy
    @PrePassivate
    public void doPersistency()
    {
        em.merge(this.customer);
        em.merge(this.order);
        order.getOrderCourseList().forEach((o) -> {em.merge(o);});
        System.out.println("did cleanup");
    }
}

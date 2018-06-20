/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.ejb;

import belly.interfaces.CustomerSessionBeanLocal;
import belly.entities.*;
import java.util.ArrayList;
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
    public FoodOrder setLatestOrder(Person customer) {
        
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
    
    @Override
    public FoodOrder getOrder() {return this.order;}

    @Override
    public Person getCustomer(){return this.customer;}    
    @Override
    public void setCustomer(Person customer) {
        this.customer = customer;
    }
    @Override
    public void setOrder(FoodOrder order) {
        this.order = order;
    }
    
    @Override
    public FoodOrder orderCourse(Course newCourse, int amount) {
        
        for (int i = 0;i<amount;i++){
            order.addCourse(newCourse);
        }
        return order;
    }

    @Override
    public FoodOrder removeCourse(Course whatCourse, int amount) {
        for (int i = 0;i<amount;i++){
            order.removeCourse(whatCourse);
        }
        return order;
    }

    @Override
    public void persist(Object object) {
        em.persist(object);
    }

    /**
     * set the order on confirmed and persist in database
     * @return amount of time to wait fo the delivery
     */
    @Override
    public int confirmOrder() {
        ArrayList<OrderCourse> myCourses = (ArrayList<OrderCourse>)order.getOrderCourseList();
        int waitTime;
        
        //indicate the order as completed
        order.setComplete((short) 1);
        em.merge(order);                //change changes to database
        
        waitTime = myCourses.stream().mapToInt(oc -> oc.getCourse().getPreptime()).max().getAsInt();
        return waitTime;
    }
    
    @Override
    public int getTotalPrice() {
        ArrayList<OrderCourse> myCourses = (ArrayList<OrderCourse>)order.getOrderCourseList();
        int totalPrice;
        
        totalPrice = myCourses.stream().mapToInt(oc -> (oc.getCount()*oc.getCourse().getPrice())).sum();
        return totalPrice;
    }
}

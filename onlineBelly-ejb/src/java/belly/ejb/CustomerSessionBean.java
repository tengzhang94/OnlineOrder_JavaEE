/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.ejb;

import belly.interfaces.CustomerSessionBeanLocal;
import belly.entities.*;
import belly.interfaces.CourseLocalInterface;
import belly.interfaces.FoodOrderLocalInterface;
import belly.interfaces.OrderCourseLocalInterface;
import belly.interfaces.PersonLocalInterface;
import java.util.ArrayList;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author toon1
 */
@Stateful
@LocalBean
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
    
        /**
     * @param customer enity in database to retrieve lastest order
     * @return order that has not been finished or a new order
     **/
    
    public FoodOrderLocalInterface getLatestOrder(Person customer) {
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
    
    /**
     * Get the value of order
     *
     * @return the value of order
     */
    @Override
    public FoodOrderLocalInterface getOrder() {return this.order;}
    /**
     * Set the value of order
     *
     * @param order new value of order
     */
    @Override
    public void setOrder(FoodOrderLocalInterface order) {this.order = (FoodOrder) order;}
    @Override
    public PersonLocalInterface getCustomer(){return customer;}
    @Override
    public void setCustomer(PersonLocalInterface customer){this.customer = (Person) customer;}
    
    @Override
    public FoodOrderLocalInterface orderCourse(CourseLocalInterface newCourse, int amount) {
        
        for (int i = 0;i<amount;i++){
            order.addCourse(newCourse);
        }
        return order;
    }

    @Override
    public FoodOrderLocalInterface removeCourse(CourseLocalInterface whatCourse, int amount) {
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

    @Override
    public FoodOrderLocalInterface getLatestOrder(PersonLocalInterface customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

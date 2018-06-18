/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.ejb;

import belly.entities.Course;
import belly.entities.FoodOrder;
import belly.entities.OrderCourse;
import belly.entities.Person;
import java.util.ArrayList;
import java.util.Comparator;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author toon1
 */
@Stateful
@LocalBean
public class CustomerSessionBean {

    
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
     * Get the value of order
     *
     * @return the value of order
     */
    public FoodOrder getOrder() {return order;}
    /**
     * Set the value of order
     *
     * @param order new value of order
     */
    public void setOrder(FoodOrder order) {this.order = order;}
    public Person getCustomer(){return customer;}
    public void setCustomer(Person customer){this.customer = customer;}
    
    public FoodOrder orderCourse(Course newCourse, int amount) {
        
        for (int i = 0;i<amount;i++){
            order.addCourse(newCourse);
        }
        return order;
    }

    public FoodOrder removeCourse(Course whatCourse, int amount) {
        for (int i = 0;i<amount;i++){
            order.removeCourse(whatCourse);
        }
        return order;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    /**
     * set the order on confirmed and persist in database
     * @return amount of time to wait fo the delivery
     */
    public int confirmOrder() {
        ArrayList<OrderCourse> myCourses = (ArrayList<OrderCourse>)order.getOrderCourseList();
        int waitTime;
        
        //indicate the order as completed
        order.setComplete(true);
        em.merge(order);                //change changes to database
        
        waitTime = myCourses.stream().mapToInt(oc -> oc.getCourse().getPreptime()).max().getAsInt();
        return waitTime;
    }
    
    public int getTotalPrice() {
        ArrayList<OrderCourse> myCourses = (ArrayList<OrderCourse>)order.getOrderCourseList();
        int totalPrice;
        
        totalPrice = myCourses.stream().mapToInt(oc -> (oc.getCount()*oc.getCourse().getPrice())).sum();
        return totalPrice;
    }

    public FoodOrder viewOrder() {
        //show for each course name, amount, price
        //show total price
        return this.order;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import belly.entities.*;
import belly.exceptions.*;
import belly.interfaces.*;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import javax.ejb.EJB;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author toon1
 */
@Named(value = "onlineOrderMBean")
@SessionScoped
public class OnlineOrderMBean implements Serializable {

    @EJB
    private CustomerSessionBeanLocal customerSessionBean;
    @EJB
    private CustomerCredentialsBeanLocal customerCredentialsBean;
    @EJB
    private CourseOverviewBeanLocal courseOverviewBean;
     
    private String loginName;
    private String password;
    private String nickName;
    private Course myCourse;

    public String getNickName() {return nickName;}
    public void setNickName(String nickName) {this.nickName = nickName;}
    public String getLoginName() {return loginName;}
    public void setLoginName(String loginName) {this.loginName = loginName;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}    /**
     * Creates a new instance of OnlineOrderMBean
     */
    public OnlineOrderMBean() {
    }
    
     /**
     *
     * @return list of course objects to be displayed in the menu
     */
    public List<Course> getCourses()
    {
        //ArrayList<Course> myList = new ArrayList<>();
        //myList.add(new Course(2,"eten",15,6));
        return courseOverviewBean.getOverview();
    }
    
    public List<OrderCourse> getOrderedCourses()
    {
        /**ArrayList<OrderCourse> myOs = new ArrayList<>();
        
        myOs.add(new OrderCourse(1,1));
        myOs.add(new OrderCourse(1,2));
        myOs.add(new OrderCourse(1,3));
        return myOs;
        */
        return customerSessionBean.getOrder().getOrderCourseList();
    }
    
    public int totalPrice()
    {
        return customerSessionBean.getTotalPrice();
        //return 42;
    }
    public int deliveryDuration()
    {
        return customerSessionBean.getDuration();
        //return 42;
    }
    
    public String confirm()
    {
        System.out.println("finished session");
        customerSessionBean.confirmOrder();
        return "MenuList";
    }
    
    public void orderCourse()
    {
        System.out.println("order : "+ this.myCourse);
        customerSessionBean.orderCourse(myCourse, 1);
        //check if logged in
        //case yes, add to order
        //else redirect to login view
    }
    public void deleteCourse()
    {
        System.out.println("cancel order : "+ this.myCourse);   
        customerSessionBean.removeCourse(myCourse, 1);
        //check if logged in
        //case yes, add to order
        //else redirect to login view
    }
    public String loginCustomer()
    {
        try
        {
            Person p = customerCredentialsBean.loginCustomer(loginName, password);
            customerSessionBean.setCustomer(p);
            customerSessionBean.setLatestOrder(p);
            return "MenuList";
        }
        catch (InvalidCredentialsException e)
        {
            //display msg to try again
            
            return "LoginPage";
        }
    }
    public String registerCustomer()
    {
        try
        {
            System.out.println("creting new person");
            Person newCustomer  = customerCredentialsBean.registerCustomer(loginName, password, nickName);
            customerSessionBean.setCustomer(newCustomer);
            customerSessionBean.setLatestOrder(newCustomer);            
            return "MenuList";
        }
        catch (NotUniqueCredentialsException e)
        {
            //display msg to use other loginName
            return "RegisterPage";
        }
    }
    /**
     * see if a user is logged in, otherwise signal that  
     * @return the current customer
     */
    public Person getCustomer()
    {
        if (checkLoggedInUser())
            return customerSessionBean.getCustomer();
        else
               //redirect to login page;
        return null;    
    }

    private boolean checkLoggedInUser()
    {
        Principal loginUser = getLoggedInUser();
        return (loginUser != null);
    }

    public String showCustomerName(Person customer)
    {
        return customerSessionBean.getCustomer().getName();
    }

    /**
     *
     * @return Principal of the logged-in user
     */
    private Principal getLoggedInUser()
    {
        HttpServletRequest request =
                (HttpServletRequest) FacesContext.getCurrentInstance().
                    getExternalContext().getRequest();
        return request.getUserPrincipal();
    }

    public Course getMyCourse() {
        return myCourse;
    }

    public void setMyCourse(Course myCourse) {
        this.myCourse = myCourse;
    }

    
}

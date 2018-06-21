/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import belly.entities.*;
import belly.exceptions.*;
import belly.interfaces.*;
import java.io.IOException;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;

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
    
    public List<Course> getCourses(){return courseOverviewBean.getOverview();}    
    public List<OrderCourse> getOrderedCourses(){return customerSessionBean.getOrder().getOrderCourseList();}    
    public int totalPrice(){return customerSessionBean.getTotalPrice();}
    public int deliveryDuration()    {return customerSessionBean.getDuration();}
    
    public String confirm()
    {
        System.out.println("finished session");
        customerSessionBean.confirmOrder();
        return "MenuList";
    }
    
    public void orderCourse()
    {
        if(!checkLoggedInUser())
            
        System.out.println("order : "+ this.myCourse);
        customerSessionBean.orderCourse(myCourse, 1);
        //check if logged in
        //case yes, add to order
        //else redirect to login view
    }
    public void orderCourse(Course course)
    {
        System.out.println("order : "+ course);
        customerSessionBean.orderCourse(course, 1);
    }
    public void deleteCourse()
    { 
        System.out.println("remove : "+ this.myCourse);
        customerSessionBean.removeCourse(myCourse, 1);
    }
    public void deleteCourse(Course course) throws IOException
    {
        System.out.println("remove : "+ course);
        customerSessionBean.removeCourse(course, 1);
    }
    public String loginCustomer()
    {
        try
        {
            Person p = customerCredentialsBean.loginCustomer(loginName, password);
            customerSessionBean.setCustomer(p);
            customerSessionBean.setLatestOrder(p);
            System.out.println("MenuList");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user",p);
            System.out.println(FacesContext.getCurrentInstance().getExternalContext().getSessionMap());
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
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user",newCustomer);
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

    public boolean checkLoggedInUser()
    {
        Person loginUser = getLoggedInUser();
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
    private Person getLoggedInUser()
    {
        HttpServletRequest request =
                (HttpServletRequest) FacesContext.getCurrentInstance().
                    getExternalContext().getRequest();
        return (Person) request.getAttribute("user");
    }

    public Course getMyCourse() {return myCourse;}
    public void setMyCourse(Course myCourse) {this.myCourse = myCourse;}

    
}

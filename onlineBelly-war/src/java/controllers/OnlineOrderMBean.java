/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import belly.ejb.Soap;
import belly.ejb.SoapWS_Service;
import belly.ejb.Soap_Service;
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
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author toon1
 */
@Named(value = "onlineOrderMBean")
@SessionScoped
public class OnlineOrderMBean implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Soap/Soap.wsdl")
    private Soap_Service service_1;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/SoapWS/SoapWS.wsdl")
    private SoapWS_Service service;



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
    
    public List<belly.ejb.Course> getCourses(){return getOverview();}    
    public List<OrderCourse> getOrderedCourses(){return customerSessionBean.getOrder().getOrderCourseList();}    
    public int totalPrice(){return customerSessionBean.getTotalPrice();}
    public int deliveryDuration()    {return customerSessionBean.getDuration();}
    
    public void confirm() throws IOException
    {
        System.out.println("finished session");
        customerSessionBean.confirmOrder();
        ExternalContext ec =FacesContext.getCurrentInstance().getExternalContext();
         ec.redirect(ec.getRequestContextPath() + "/Comment.html");
    }
    
    public void orderCourse() throws IOException
    {
        if(!checkLoggedInUser()){
            ExternalContext ec =FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/LoginPage.jsf");
        }
            
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

    private List<belly.ejb.Course> getOverview() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        belly.ejb.SoapWS port = service.getSoapWSPort();
        return port.getOverview();
    }

    private List<Course> getOverview_1() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        SoapWSLocalInterface port = service_1.getPort(SoapWSLocalInterface.class);
        return port.getOverview();
    }

  
    
}

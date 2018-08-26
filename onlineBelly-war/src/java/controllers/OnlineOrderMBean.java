/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


//import belly.ejb.Soap;
//import belly.ejb.SoapWS_Service;
//import belly.ejb.Soap_Service;
import belly.entities.*;
import belly.exceptions.*;
import belly.interfaces.*;
import belly.webservice.TimeIndicate1;
import belly.webservice.TimeIndicateInterfaceImpl;
import java.io.IOException;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;

//import javax.xml.ws.WebServiceRef;

/**
 *
 * @author toon1&teng
 */
@Named(value = "onlineOrderMBean")
@SessionScoped
public class OnlineOrderMBean implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/TimeIndicate1/TimeIndicateInterfaceImpl.wsdl")
    private TimeIndicate1 service_1;

  
    //@WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Soap/Soap.wsdl")
    //private Soap_Service service_1;

    //@WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/SoapWS/SoapWS.wsdl")
    //private SoapWS_Service service;



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

    
    public void orderCourse() throws IOException
    {
        //by teng in August
        //without this condition throws noEJBException for the 1st order without login first
        //because the checkLoggedInUser was always false before
        //it is always login and menuList switching from each other 
        if(!checkLoggedInUser()){
            ExternalContext ec =FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/LoginPage.jsf");
        }
        else{
        System.out.println("order : "+ this.myCourse);
        customerSessionBean.orderCourse(this.myCourse, 1);
        }
        //check if logged in
        //case yes, add to order
        //else redirect to login view
    }
    public void orderCourse(Course course)
    {
        System.out.println("order : "+ course);
        customerSessionBean.orderCourse(course, 1);
    }
        
    public void confirm() throws IOException
    {
        System.out.println("finished session");
        customerSessionBean.confirmOrder();
        ExternalContext ec =FacesContext.getCurrentInstance().getExternalContext();
         ec.redirect(ec.getRequestContextPath() + "/Comment.html");
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
    public void loginCustomer() throws IOException
    {
        try
        {
            Person p = customerCredentialsBean.loginCustomer(loginName, password);
            //by teng in August
            //have to check if this user has logged in otherwise it stays
            //without this check the same user logs in again
            //another stateful session bean will be created
            if(checkLoggedInUser()){
            Person pNow = (Person)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            if(pNow.getName().equals(p.getName()))
            {
                System.out.println("This is the current user!");
                ////return "MenuList";
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(ec.getRequestContextPath() + "/MenuList.jsf");
                return;
            }
        }
            customerSessionBean.setCustomer(p);
            customerSessionBean.unConfirmedOrder(p);
            System.out.println("Login done!");
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.getSessionMap().put("user",p);
            ec.redirect(ec.getRequestContextPath() + "/MenuList.jsf");
            //return "MenuList";
        }
        catch (InvalidCredentialsException e)
        {
            //display msg to try again
            System.out.println("Invalid credential, try again!!");
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/LoginPage.jsf");
                
            //////return "LoginPage";
        }
    }
    // by teng in August
    // only go to the checkout page when it is logged in
    public void tryAccessCheckout() throws IOException
    {
        if(checkLoggedInUser())
        {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/CheckoutPage.jsf");
            return ;
        }
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/MenuList.jsf");
    }
    // by teng in August
    // log out user if it is already logged in
    public void tryLogout() throws IOException
    {
        if(checkLoggedInUser())
        {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            System.out.println("user successfully logged out");
           
        }
         System.out.println("Not logged in yet");
         ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
         ec.redirect(ec.getRequestContextPath() + "/MenuList.jsf");
    }
    public String registerCustomer()
    {
        try
        {
            System.out.println("creating new person");
            Person newCustomer  = customerCredentialsBean.registerCustomer(loginName, password, nickName);
            customerSessionBean.setCustomer(newCustomer);
            customerSessionBean.unConfirmedOrder(newCustomer);            
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
        Person loginUser = this.getLoggedInUser();
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
        //HttpServletRequest request =
        //        (HttpServletRequest) FacesContext.getCurrentInstance().
        //            getExternalContext().getRequest();
         return (Person)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        //return (Person) request.getAttribute("user");
    }

    public Course getMyCourse() {return myCourse;}
    public void setMyCourse(Course myCourse) {this.myCourse = myCourse;}

   // private List<belly.ejb.Course> getOverview() {
   //     // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
   //     // If the calling of port operations may lead to race condition some synchronization is required.
   //     belly.ejb.SoapWS port = service.getSoapWSPort();
   //     return port.getOverview();
   // }

   // private List<Course> getOverview_1() {
   //     // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
   //     // If the calling of port operations may lead to race condition some synchronization is required.
   //     SoapWSLocalInterface port = service_1.getPort(SoapWSLocalInterface.class);
   //     return port.getOverview();
    //}

    

    public String showTime(){
        return this.showTime1_1();
    }
    
    

    private String showTime1_1() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        belly.webservice.TimeIndicateInterface port = service_1.getTimeIndicateInterfaceImplPort();
        return port.showTime1();
    }

    private XMLGregorianCalendar generateTime1() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        belly.webservice.TimeIndicateInterface port = service_1.getTimeIndicateInterfaceImplPort();
        return port.generateTime1();
    }
  
    
}

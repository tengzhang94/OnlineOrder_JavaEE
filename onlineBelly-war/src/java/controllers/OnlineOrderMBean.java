/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import belly.ejb.CourseOverviewBean;
import belly.ejb.CustomerCredentialsBean;
import belly.ejb.CustomerSessionBean;
import belly.entities.Course;
import belly.entities.Person;
import belly.exceptions.InvalidCredentialsException;
import belly.exceptions.NotUniqueCredentialsException;
import static com.sun.mail.util.ASCIIUtility.getBytes;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import webService_client.CredentialService_Service;
import webService_client.InvalidCredentialsException_Exception;
import webService_client.NotUniqueCredentialsException_Exception;

/**
 *
 * @author toon1
 */
@Named(value = "onlineOrderMBean")
@SessionScoped
public class OnlineOrderMBean implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/CredentialService/CredentialService.wsdl")
    private CredentialService_Service service;

    private webService_client.CustomerSessionBean customerSessionBean;

    @EJB
    private CourseOverviewBean courseOverviewBean;
     
    private String loginName;
    private String password;
    private String nickName;

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
    
    public void orderCourse()
    {
        //check if logged in
        //case yes, add to order
        //else redirect to login view
    }
    public String loginCustomer()
    {
        byte[] cypherBytes = getBytes(this.password);
        try
        {
            customerSessionBean = loginCustomer_1(loginName, cypherBytes);
            return "MenuList";
        }
        catch (InvalidCredentialsException_Exception e)
        {
            //display msg to try again
            System.out.println("bad login attempt");            
            System.out.println("filled in pw: "+new String(cypherBytes));
            return "RegisterPage";
        }
    }
    public void registerCustomer() 
    {
        byte[] cypherBytes = getBytes(this.password);
        try
        {
            System.out.println("creting new person");
            customerSessionBean = registerCustomer_1(loginName, cypherBytes, nickName);
        }
        catch (NotUniqueCredentialsException_Exception e)
        {
            //display msg to use other loginName
        }
    }
    /**
     * see if a user is logged in, otherwise signal that  
     * @return the current customer
     */
    public webService_client.Person getCustomer()
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

    private webService_client.CustomerSessionBean loginCustomer_1(java.lang.String loginName, byte[] password) throws InvalidCredentialsException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        webService_client.CredentialService port = service.getCredentialServicePort();
        return port.loginCustomer(loginName, password);
    }

    private webService_client.CustomerSessionBean registerCustomer_1(java.lang.String loginName, byte[] password, java.lang.String personName) throws NotUniqueCredentialsException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        webService_client.CredentialService port = service.getCredentialServicePort();
        return port.registerCustomer(loginName, password, personName);
    }

    
}

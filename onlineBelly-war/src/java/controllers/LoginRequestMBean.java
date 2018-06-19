/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import belly.ejb.CustomerCredentialsBean;
import belly.exceptions.InvalidCredentialsException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author toon1
 */
@Named(value = "loginRequestMBean")
@RequestScoped
public class LoginRequestMBean {

    @EJB
    private CustomerCredentialsBean customerCredentialsBean;

    private String loginName;
    private String password;
    
    /**
     * Creates a new instance of LoginRequestMBean
     */
    public LoginRequestMBean() {
    }
    public String loginCustomer()
    {
        try
        {
            /**customerSessionBean =**/ customerCredentialsBean.loginCustomer(loginName, password);
            return "MenuList";
        }
        catch (InvalidCredentialsException e)
        {
            //display msg to try again
            System.out.println("bad login attempt");            
            return "RegisterPage";
        }
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}

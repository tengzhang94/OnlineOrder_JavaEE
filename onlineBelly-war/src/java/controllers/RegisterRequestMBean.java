/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import belly.ejb.CustomerCredentialsBean;
import belly.exceptions.NotUniqueCredentialsException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author toon1
 */
@Named(value = "registerRequestMBean")
@RequestScoped
public class RegisterRequestMBean {

    @EJB
    private CustomerCredentialsBean customerCredentialsBean;   
    
    private String newLoginName;
    private String newPassword;
    private String newNickName;
    /**
     * Creates a new instance of RegisterRequestMBean
     */
    public RegisterRequestMBean() {
    }
    
    public void registerCustomer()
    {
        try
        {
            System.out.println("creting new person");
            /**customerSessionBean = **/customerCredentialsBean.registerCustomer(newLoginName, newPassword, newNickName);
        }
        catch (NotUniqueCredentialsException e)
        {
            //display msg to use other loginName
        }
    }

    public String getNewLoginName() {
        return newLoginName;
    }

    public void setNewLoginName(String newLoginName) {
        this.newLoginName = newLoginName;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewNickName() {
        return newNickName;
    }

    public void setNewNickName(String newNickName) {
        this.newNickName = newNickName;
    }
    
}

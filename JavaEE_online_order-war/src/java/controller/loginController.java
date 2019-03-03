/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Person;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import model.PersonFacadeLocal;

/**
 *
 * @author dell
 */
@Named(value = "loginController")
@SessionScoped
public class loginController implements Serializable {

    /**
     * Creates a new instance of loginController
     */
    private String name;
    private String pass;
    private boolean isLogged = false;
    
    @EJB
    private PersonFacadeLocal personFacade;
    private List<Person> persons;

    public boolean isIsLogged() {
        return isLogged;
    }
    
    public List<Person> persons(){
        return this.personFacade.findAll();
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }
    
    public loginController() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String action(){
    persons = persons();
    for (Person p : persons){
    if(this.name.equals(p.getNickname())&&this.pass.equals(p.getPassword())){
        isLogged = true;
        return "shopping_cart.xtml?faces-redirect=true";
    }
    }

  return "index.xtml?faces-redirect=true";
    }
    
}

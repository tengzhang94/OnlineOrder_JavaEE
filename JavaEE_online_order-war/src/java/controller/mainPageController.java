/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.*;
import javax.ejb.EJB;
import model.CourseFacadeLocal;
import entities.*;


/**
 *
 * @author Teng
 */
@Named(value = "mainPageController")
@SessionScoped
public class mainPageController implements Serializable {

    @EJB
    private CourseFacadeLocal courseFacade;
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
    public mainPageController() {
    }
    
    public List<Course> findAll(){
        return this.courseFacade.findAll();
    }
    
    public void delete(Course p){
        this.courseFacade.remove(p);
    }
}

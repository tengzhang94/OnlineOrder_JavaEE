/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// by teng in August
package controllers;

import client.restClient;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.core.GenericType;
import service.Restolocation;

/**
 *
 * @author teng
 */
@Named(value = "restoLocationMBean")
@ApplicationScoped
public class restoLocationMBean implements Serializable{

    /**
     * Creates a new instance of restoLocationMBean
     */
    private restClient client = new restClient();
    private List<Restolocation> locationList;
    public restoLocationMBean() {
    }
   
    public List<Restolocation> getList(){
    GenericType<List<Restolocation>> gType = new GenericType<List<Restolocation>>() {};
        this.setList(client.findAll_XML(gType));
        return locationList;
    }
    
     public void setList(List<Restolocation> list) {
        this.locationList = list;
    }
}

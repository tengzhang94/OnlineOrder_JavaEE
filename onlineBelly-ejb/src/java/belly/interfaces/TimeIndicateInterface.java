/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.interfaces;

import java.io.Serializable;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author dell
 */
@WebService()
public interface TimeIndicateInterface extends Serializable {
    @WebMethod (operationName="showTime")
    public String showTime();
    @WebMethod (operationName = "generateTime")
     public Date generateTime();
     
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.interfaces;

import belly.entities.Course;
import java.io.Serializable;
import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author teng
 */
@WebService()
public interface SoapWSLocalInterface extends Serializable {

    @WebMethod(operationName = "getOverview")
    List<Course> getOverview();

    @WebMethod(operationName = "persist")
    @Oneway
    void persist(@WebParam(name = "object") Object object);
    
}

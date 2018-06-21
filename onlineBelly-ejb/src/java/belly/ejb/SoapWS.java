/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.ejb;

import belly.entities.Course;
import belly.interfaces.CourseOverviewBeanLocal;
import belly.interfaces.SoapWSLocalInterface;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;


/**
 *
 * @author dell
 */
@WebService(serviceName = "SoapWS")
@Stateless
public class SoapWS implements SoapWSLocalInterface {

    @EJB
    private CourseOverviewBeanLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "getOverview")
    @Override
    public List<Course> getOverview() {
        return ejbRef.getOverview();
    }

    @WebMethod(operationName = "persist")
    @Oneway
    @Override
    public void persist(@WebParam(name = "object") Object object) {
        ejbRef.persist(object);
    }
    
}

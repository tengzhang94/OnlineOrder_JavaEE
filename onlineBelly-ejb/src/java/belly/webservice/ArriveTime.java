/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.webservice;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ejb.Stateless;

/**
 *
 * @author cx738
 */
@Stateless()
@WebService(serviceName = "ArriveTime")
public class ArriveTime {

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "showArriveTime")
    public String showArriveTime() {
         Date date = new Date();
         SimpleDateFormat format = new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss");
        return format.format(date);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.webservice;

import belly.interfaces.TimeIndicateInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@Stateless()
@WebService(serviceName = "TimeIndicate1", endpointInterface= "belly.interfaces.TimeIndicateInterface")
public class TimeIndicateInterfaceImpl implements TimeIndicateInterface {
    
    @Override
    public String showTime() {
         Date date = new Date();
         SimpleDateFormat format = new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss zzz");
        return format.format(date);
    }
    
    @Override
    public Date generateTime(){
        Date date=new Date();
        return date;
    }
}

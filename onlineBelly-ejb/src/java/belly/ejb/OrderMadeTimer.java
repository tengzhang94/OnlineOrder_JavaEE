/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.ejb;

import javax.ejb.Stateless;
import belly.interfaces.OrderMadeTimerLocal;
import javax.annotation.Resource;
import javax.ejb.Timeout;
import javax.ejb.TimerService;

/**
 *
 * @author teng
 */
@Stateless
public class OrderMadeTimer implements OrderMadeTimerLocal {

    @Resource
    TimerService timerService;
    
    public OrderMadeTimer(){}
    
    @Timeout
    @Override
    public void orderMade() {
        System.out.println("@Order has been received at " + new java.util.Date()) ;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void setTimer() {
        timerService.createTimer(5000, "Setting a timer");
    }
    
}

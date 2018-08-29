/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.ejb;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TimerService;

/**
 *
 * @author Zheng Liang
 */
@Stateless
public class TimerBean  {
    
    @Resource
    TimerService timerService;

    //@Override
    @Schedule(minute = "42", hour = "13")
    public void happyHour() {
        System.out.println("ITS HAPPY HOUR") ; //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    @Schedule( minute = "43", hour = "13")
    public void happyHourOver() {
        System.out.println("HAPPY HOUR OVER") ; //To change body of generated methods, choose Tools | Templates.
    }

}

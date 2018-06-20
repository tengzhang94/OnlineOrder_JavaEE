/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.ejb;

import java.util.Date;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import belly.ejb.CourseOverviewBean;

/*
 *
 * @author Zheng Liang
 */
@Singleton
public class Timer implements TimerLocal {

    @Override
    @Schedule(second = "0", minute = "*", hour = "*")
    public void timer(Timer timer) {
        System.out.println(new Date()) ; //To change body of generated methods, choose Tools | Templates.
        
    }

}

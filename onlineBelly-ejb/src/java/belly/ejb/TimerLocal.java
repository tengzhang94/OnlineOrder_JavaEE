/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.ejb;

import javax.ejb.Local;

/**
 *
 * @author Zheng Liang
 */
@Local
public interface TimerLocal {
   public void timer(Timer timer);
}

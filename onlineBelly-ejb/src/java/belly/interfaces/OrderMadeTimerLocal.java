/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.interfaces;

import javax.ejb.Local;

/**
 *
 * @author teng
 */
@Local
public interface OrderMadeTimerLocal {
       public void setTimer();
        public void orderMade();
    
}

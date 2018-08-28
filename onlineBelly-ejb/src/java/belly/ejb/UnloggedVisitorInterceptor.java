/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.ejb;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author teng
 */

public class UnloggedVisitorInterceptor {
    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
     
     System.out.println("Inception starts");
         Object[] newPara = new Object[2];
         newPara[0] = "visitor";
         newPara[1] = "1234"; 
         context.setParameters(newPara);
     System.out.println("Inception ends");
     return context.proceed();

    }
}

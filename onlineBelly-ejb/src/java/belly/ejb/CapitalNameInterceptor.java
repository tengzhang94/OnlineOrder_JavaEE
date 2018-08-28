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
public class CapitalNameInterceptor {
    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
     
         System.out.println("Inception starts");
         Object[] newPara = context.getParameters();
         newPara[2]=((String)newPara[2]).toUpperCase();
         
         context.setParameters(newPara);
         System.out.println("Inception ends");
         return context.proceed();

    }
}

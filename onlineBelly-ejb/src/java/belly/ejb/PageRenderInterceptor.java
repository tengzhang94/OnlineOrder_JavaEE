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
 * @author toon1
 */
public class PageRenderInterceptor {
    
    static int pageCount = 0;
    
    @AroundInvoke
    public Object countHit(InvocationContext ctx) throws Exception
    {
        if (ctx.getMethod().toString().contains("getOverview"))
            System.out.println("this is the "+(PageRenderInterceptor.pageCount++)+" page hit");
        else
            System.out.println("page has been hit "+PageRenderInterceptor.pageCount);
        
        return ctx.proceed();    
    }
    
}

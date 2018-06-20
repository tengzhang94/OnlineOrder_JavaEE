/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.ejb;

import belly.entities.Course;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import belly.interfaces.CourseOverviewBeanLocal;
import javax.interceptor.Interceptors;

/**
 *
 * @author toon1
 */
@Singleton
public class CourseOverviewBean implements CourseOverviewBeanLocal {

    @PersistenceContext(unitName = "onlineBelly-ejbPU")
    private EntityManager em;

    @Override
    @Interceptors(PageHitInterceptor.class)
    public List<Course> getOverview() {
        Query query = em.createNamedQuery("Course.findAll");
        return query.getResultList();
    }

    @Override
    public void persist(Object object) {
        em.persist(object);
    }
    

    
}

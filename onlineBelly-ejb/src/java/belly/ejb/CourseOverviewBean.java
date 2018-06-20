/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.ejb;

import belly.entities.Course;
import belly.interfaces.CourseLocalInterface;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import belly.interfaces.CourseOverviewBeanLocal;

/**
 *
 * @author toon1
 */
@Singleton
@LocalBean
public class CourseOverviewBean implements CourseOverviewBeanLocal {

    @PersistenceContext(unitName = "onlineBelly-ejbPU")
    private EntityManager em;

    @Override
    public List<Course> getOverview() {
        Query query = em.createNamedQuery("Course.findAll");
        return query.getResultList();
    }

    @Override
    public void persist(Object object) {
        em.persist(object);
    }
    

    
}

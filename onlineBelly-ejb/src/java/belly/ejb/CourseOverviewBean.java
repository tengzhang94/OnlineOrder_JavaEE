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
import javax.xml.bind.annotation.*;

/**
 *
 * @author toon1& teng
 */
@Singleton
public class CourseOverviewBean implements CourseOverviewBeanLocal {

    @PersistenceContext(unitName = "onlineBelly-ejbPU")
    private EntityManager em;

    @Override
    @Interceptors(PageRenderInterceptor.class)
    @XmlElement
    public List<Course> getOverview() {
        Query query = em.createNamedQuery("Course.findAll");
        return query.getResultList();
    }
    // by teng in August
    // for the soap webservice to show on the MenuList page

    /**
     *
     * @return
     */
    @XmlElement
    @Override
    public int getCourseNr()
    {
      // List<Course> overview = this.getOverview();
      //  return overview.size();
        //Query query = em.createNamedQuery("Course.getCourseNr");
        //return  ((Long)query.getSingleResult()).intValue();
        return 8;
    }
    
    @Override
    public void persist(Object object) {
        em.persist(object);
    }
    

    
}

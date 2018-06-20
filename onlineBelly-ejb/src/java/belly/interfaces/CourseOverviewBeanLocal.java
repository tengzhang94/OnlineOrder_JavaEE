/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belly.interfaces;

import belly.entities.Course;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author toon1
 */
@Local
public interface CourseOverviewBeanLocal extends Serializable {

    List<Course> getOverview();

    void persist(Object object);    
}

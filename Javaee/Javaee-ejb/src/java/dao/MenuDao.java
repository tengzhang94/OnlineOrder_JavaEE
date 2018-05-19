/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.MenuOrdered;

/**
 *
 * @author Teng Zhang
 */
@Stateless
public class MenuDao implements MenuDaoLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
     @PersistenceContext
    private EntityManager em;
    
    @Override
    public void addMenu(MenuOrdered menuOrdered) {
        em.persist(menuOrdered);
    }
}

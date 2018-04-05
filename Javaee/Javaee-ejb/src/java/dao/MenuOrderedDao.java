/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.MenuOrdered;

/**
 *
 * @author Zheng Liang
 */
@Stateless
public class MenuOrderedDao implements MenuOrderedDaoLocal {
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;
    
    
    @Override
    public void addMenu(MenuOrdered menuOrdered) {
        em.persist(menuOrdered);
    }
   
    @Override
    public void deleteMenu(int menuOrderedId) {
        em.remove(getMenuOrdered(menuOrderedId));
    }
    
    @Override
    public void more(MenuOrdered menuOrdered) {
        em.merge(menuOrdered);
    }

    @Override
    public void less(MenuOrdered menuOrdered) {
        em.merge(menuOrdered);
    }

    @Override
    public MenuOrdered getMenuOrdered(int menuOrderedId) {
        return em.find(MenuOrdered.class, menuOrderedId);
    }

    @Override
    public List<MenuOrdered> getAllMenuOrdered() {
        return em.createNamedQuery("MenuOrdered.getAll").getResultList();
    }
    
    
    
}

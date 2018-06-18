/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Local;
import model.MenuOrdered;

/**
 *
 * @author Zheng Liang
 */
@Local
public interface MenuOrderedDaoLocal {

    void addMenu(MenuOrdered menuOrdered);

    void more(MenuOrdered menuOrdered);

    void less(MenuOrdered menuOrdered);

    void deleteMenu(int menuOrderedId);

    MenuOrdered getMenuOrdered(int menuOrderedId);

    List<MenuOrdered> getAllMenuOrdered();
    
}

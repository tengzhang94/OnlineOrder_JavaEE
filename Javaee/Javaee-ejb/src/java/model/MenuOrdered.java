/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/**
 *
 * @author Zheng Liang
 */
@Entity
@Table
@NamedQueries(@NamedQuery(name="MenuOrdered.getAll",query="SELECT e FROM MenuOrdered e"))
public class MenuOrdered implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private int menuOrderedId;
    @Column
    private String menuOrderedName;
    @Column
    private String menuOrderedtype;
    @Column
    private String menuOrderedpic;
    @Column
    private int number;

    public MenuOrdered(int menuOrderedId, String menuOrderedName, String menuOrderedtype, String menuOrderedpic, int number) {
        this.menuOrderedId = menuOrderedId;
        this.menuOrderedName = menuOrderedName;
        this.menuOrderedtype = menuOrderedtype;
        this.menuOrderedpic = menuOrderedpic;
        this.number = number;
    }

    public MenuOrdered() {
    }

    public int getMenuOrderedId() {
        return menuOrderedId;
    }

    public void setMenuOrderedId(int menuOrderedId) {
        this.menuOrderedId = menuOrderedId;
    }

    public String getMenuOrderedName() {
        return menuOrderedName;
    }

    public void setMenuOrderedName(String menuOrderedName) {
        this.menuOrderedName = menuOrderedName;
    }

    public String getMenuOrderedtype() {
        return menuOrderedtype;
    }

    public void setMenuOrderedtype(String menuOrderedtype) {
        this.menuOrderedtype = menuOrderedtype;
    }

    public String getMenuOrderedpic() {
        return menuOrderedpic;
    }

    public void setMenuOrderedpic(String menuOrderedpic) {
        this.menuOrderedpic = menuOrderedpic;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    
    
    
}

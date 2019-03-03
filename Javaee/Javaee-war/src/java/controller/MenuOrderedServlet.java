/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MenuOrderedDaoLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MenuOrdered;

/**
 *
 * @author Zheng Liang
 */
@WebServlet(name = "MenuOrderedServlet", urlPatterns = {"/MenuOrderedServlet"})
public class MenuOrderedServlet extends HttpServlet {
    @EJB
    private MenuOrderedDaoLocal menuOrderedDao;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String menuOrderedIdStr = request.getParameter("menuOrderedId");
        int menuOrderedId=0;
        if(menuOrderedIdStr != null && !menuOrderedIdStr.equals(""))
         menuOrderedId = Integer.parseInt(menuOrderedIdStr);
        String menuOrderedName = request.getParameter("menuOrderedName");
        String menuOrderedtype = request.getParameter("menuOrderedtype");
        String menuOrderedpic = request.getParameter("menuOrderedpic");
        String numberStr = request.getParameter("number");
        int number =0;
        if(numberStr != null && !numberStr.equals(""))
         number = Integer.parseInt(numberStr);
        
        
        MenuOrdered menuOrdered = new MenuOrdered(menuOrderedId,menuOrderedName, menuOrderedtype,menuOrderedpic,number);
        if("Add".equalsIgnoreCase(action)){
            menuOrderedDao.addMenu(menuOrdered);
        }else if("More".equalsIgnoreCase(action)){
            menuOrderedDao.more(menuOrdered);
        }else if("Less".equalsIgnoreCase(action)){
            menuOrderedDao.less(menuOrdered);
        }else if("Delete".equalsIgnoreCase(action)){
            menuOrderedDao.deleteMenu(menuOrderedId);
        }else if("Search".equalsIgnoreCase(action)){
           menuOrdered = menuOrderedDao.getMenuOrdered(menuOrderedId);
        }
        
        request.setAttribute("menuOrdered", menuOrdered);
        request.setAttribute("allMenuOrdered", menuOrderedDao.getAllMenuOrdered());
        request.getRequestDispatcher("menuOrdered.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

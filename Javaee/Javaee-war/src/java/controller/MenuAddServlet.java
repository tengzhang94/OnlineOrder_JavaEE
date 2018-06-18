/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MenuDaoLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MenuOrdered;

/**
 *
 * @author Teng Zhang
 */
@WebServlet(name = "MenuAddServlet", urlPatterns = {"/MenuAddServlet"})
public class MenuAddServlet extends HttpServlet {

    @EJB
    private MenuDaoLocal menuDao;
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
            menuDao.addMenu(menuOrdered);
        }
    //necessarily a response?    
    //    request.setAttribute("menuOrdered", menuOrdered);
    //    request.setAttribute("allMenuOrdered", menuDao.getAllMenuOrdered());
    //    request.getRequestDispatcher("menuOrdered.jsp").forward(request, response);
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

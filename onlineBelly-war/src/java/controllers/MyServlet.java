/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Zheng Liang & teng
 */
public class MyServlet extends HttpServlet {

    @Resource(mappedName = "jmsDemo/navinDest")
    private Queue demoNavinDest;
    @Resource(mappedName = "jmsDemo/navin")
    private ConnectionFactory queue;
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
        
        if (request.getParameter("sendText") != null) 
        {
            response.setContentType("text/html;charset=UTF-8");

            String str = request.getParameter("t1");
            PrintWriter out = response.getWriter();
            //out.println("Demo");
            sendJMSMessageToNavinDest(str);
            //out.println("<h1>your message is record</h1>");
            System.out.println("Your message is sent!");
            response.sendRedirect("MenuList.jsf");
        }
        // by teng in August
        // return to the menu page
        if (request.getParameter("return") != null) {
            System.out.println("return is submitted");
            response.sendRedirect("MenuList.jsf");
        }
        
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

    private void sendJMSMessageToNavinDest(String messageData) {
        //context.createProducer().send(demoNavinDest, messageData);
        try{
        Connection con = queue.createConnection();
        Session s = con.createSession();
        MessageProducer mp = s.createProducer(demoNavinDest);
         TextMessage tm = s.createTextMessage();
         tm.setText(messageData);
         mp.send(tm);
        }
        catch(Exception e){
        System.out.println(e);
        }
    }

}

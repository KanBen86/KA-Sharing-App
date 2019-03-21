/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import de.kasharing.app.jpa.Nutzer;
import javax.ejb.EJB;
import de.kasharing.app.ejb.NutzerBean;
import de.kasharing.app.helper.Response;

/**
 *
 * @author Orlando Jähde
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet { 
    
    private final static String URL = "/login/";

    @EJB
    NutzerBean nutzerBean;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nickName = request.getParameter("nickName");
        String passwort = request.getParameter("passwort");
        
        Response<Nutzer> n = nutzerBean.findByNick(nickName);
        if (n.getStatus().equals("Error")) {
            //n = nutzerBean.findByEmail(nickName);
        }
        
        if (n.getResponse().getPasswort().equals(passwort)) {
            request.setAttribute("nutzer", n);
            request.getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/index.html");
        }
        else {
            boolean datenFalsch = true;
            request.setAttribute("datenFalsch", datenFalsch);
            request.getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/login/");
        }
        
        
    }

}
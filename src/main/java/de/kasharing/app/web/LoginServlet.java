/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import de.kasharing.app.jpa.Nutzer;
import javax.ejb.EJB;
import de.kasharing.app.ejb.KundeBean;
import de.kasharing.app.ejb.MitarbeiterBean;
import de.kasharing.app.helper.Response;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Orlando Jähde
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet { 
    
    private final static String URL = "/login/";

    @EJB
    KundeBean kundeBean;
    
    @EJB
    MitarbeiterBean mitarbeiterBean;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String nickName = request.getParameter("nickName");
        String passwort = request.getParameter("passwort");

        //Hier fehlt Überprüfung, welcher Kundentyp
        
        if (n.getStatus().equals("Error")) {
            //n = nutzerBean.findByEmail(nickName);
        }
        
        if (n.getResponse().getPasswort().equals(passwort)) {
            session.setAttribute("nutzer", n);
            //request.setAttribute("nutzer", n);
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

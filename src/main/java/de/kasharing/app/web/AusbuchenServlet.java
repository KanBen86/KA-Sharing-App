/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.web;

import javax.ejb.EJB;        
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import de.kasharing.app.ejb.FahrzeugBean;
import de.kasharing.app.enums.FahrzeugAusbuchung;
import de.kasharing.app.helper.Response;
import de.kasharing.app.jpa.Fahrzeug;

/**
 *
 * @author Orlando Jähde
 */
@WebServlet(urlPatterns = {"/ausbuchen/*"})
public class AusbuchenServlet extends HttpServlet {
    
    @EJB
    FahrzeugBean fahrzeugBean;

    FahrzeugAusbuchung fahrzeugAusbuchung;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Anfrage an die index.jsp weiterleiten
        long id = -1;
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.length() > 2) {
            try {
                id = Long.parseLong(pathInfo.split("/")[1]);
            } catch (NumberFormatException ex) {
                // URL enthält keine gültige Long-Zahl
            }
        }
        
        Response<Fahrzeug> detailFahrzeug = fahrzeugBean.findById(id);
        request.setAttribute("detailFahrzeug", detailFahrzeug);
        
        FahrzeugAusbuchung[] statusList = fahrzeugAusbuchung.values();
        request.setAttribute("statusList", statusList);
        
        request.getRequestDispatcher("/WEB-INF/zurueckgeben.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Anfrage an die index.jsp weiterleiten
        long id = -1;
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.length() > 2) {
            try {
                id = Long.parseLong(pathInfo.split("/")[1]);
            } catch (NumberFormatException ex) {
                // URL enthält keine gültige Long-Zahl
            }
        }
        
        Response<Fahrzeug> detailFahrzeug = fahrzeugBean.findById(id);
        log(request.getParameter("grund"));
        
        try {
            detailFahrzeug.getResponse().setGrund(Enum.valueOf(FahrzeugAusbuchung.class, request.getParameter("grund")));
        } catch (IllegalArgumentException ex) {
            System.out.println("Das Enum FahrzeugAusbung ist noch nicht gefüllt");
        }
        
        detailFahrzeug.getResponse().setDeaktiviert(true);        
        detailFahrzeug = fahrzeugBean.updateFahrzeug(detailFahrzeug.getResponse());
        
        response.sendRedirect(request.getContextPath() + "/index.html");
    }
}

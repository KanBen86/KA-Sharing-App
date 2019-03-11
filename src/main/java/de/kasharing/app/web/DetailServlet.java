/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.web;

import de.kasharing.app.ejb.FahrzeugBean;
import de.kasharing.app.enums.FahrzeugGetriebeArt;
import de.kasharing.app.enums.FahrzeugHersteller;
import de.kasharing.app.enums.FahrzeugKlasse;
import de.kasharing.app.enums.FahrzeugTyp;
import de.kasharing.app.jpa.Fahrzeug;
import de.kasharing.app.enums.FahrzeugStatus;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dominik
 */
@WebServlet(urlPatterns = {"/new/", "/detail/*"})
public class DetailServlet extends HttpServlet {
    
    @EJB
    FahrzeugBean fahrzeugBean;
    FahrzeugStatus fahrzeugstatus;
    FahrzeugGetriebeArt fahrzeugGetriebeArt;
    FahrzeugHersteller fahrzeugHersteller;
    FahrzeugKlasse fahrzeugKlasse;
    FahrzeugTyp fahrzeugTyp;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
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
        
        Fahrzeug detailFahrzeug = fahrzeugBean.findById(id);
        request.setAttribute("detailFahrzeug", detailFahrzeug);
        
        FahrzeugStatus[] statusList = fahrzeugstatus.values();
        request.setAttribute("statusList", statusList);
        
        FahrzeugGetriebeArt[] getriebeList = fahrzeugGetriebeArt.values();
        request.setAttribute("getriebeList", getriebeList);
        
        FahrzeugHersteller[] herstellerList = fahrzeugHersteller.values();
        request.setAttribute("herstellerList", herstellerList);
        
        FahrzeugKlasse[] klassenList = fahrzeugKlasse.values();
        request.setAttribute("klassenList", klassenList);
        
        FahrzeugTyp[] typList = fahrzeugTyp.values();
        request.setAttribute("typList", typList);
        
        request.getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response); 
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.web;

import de.kasharing.app.jpa.Buchung;
import de.kasharing.app.jpa.Fahrzeug;      
import de.kasharing.app.ejb.NutzerBean;
import de.kasharing.app.ejb.BuchungBean;
import de.kasharing.app.ejb.FahrzeugBean;
import javax.ejb.EJB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Orlando Jähde
 */
@WebServlet(name = "BuchungServlet", urlPatterns = {"/book/*"})

public class BuchungServlet extends HttpServlet {
    
    @EJB
    FahrzeugBean fahrzeugBean;
    BuchungBean buchungBean;    
    NutzerBean nutzerBean;
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code."
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        
        request.getRequestDispatcher("/WEB-INF/book.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        long id = -1;
        String pathInfo = request.getPathInfo();
        
        if (pathInfo != null && pathInfo.length() > 2) {
            try {
                id = Long.parseLong(pathInfo.split("/")[1]);
            } catch (NumberFormatException ex) {
                // URL enthält keine gültige Long-Zahl
            }
        }
        
        //Erstellen des (noch leeren) Nutzers
        Nutzer nutzer = new Nutzer();

        //Erstellen der Buchung
        Buchung buchung = new Buchung();

        //Füllen der Buchung mit nötigen Informationen
        buchung.setFahrzeug(fahrzeugBean.findById(id));
        buchung.setNutzer(nutzerBean.findById(nutzer.getId()));

        try{
            buchung.setGeliehenAb(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("datumBeginn"));
            buchung.setGeliehenBis(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("datumEnde"));
            }
        catch (ParseException parseException) {
                log ("Datum konnte nicht gesetzt werden:" + parseException.getStackTrace());
            }
        

        

        
        
        //Kontrolle, ab Buchung korrekt erstellt wurde
        /*if (buchung.checkValues()) {
            buchung = buchungBean.createBuchung(buchung);
        }
        else {
            System.out.println("Buchung konnte nicht erstellt werden.");
            //response.sendRedirect(request.getContextPath() + BuchungServlet.URL);
        }*/
    }

}

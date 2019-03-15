/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.web;

import de.kasharing.app.ejb.BuchungBean;
import de.kasharing.app.ejb.FahrzeugBean;
import de.kasharing.app.enums.FahrzeugStatus;
import de.kasharing.app.jpa.Buchung;
import de.kasharing.app.jpa.Fahrzeug;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Benjamin Kanzler
 */
@WebServlet(urlPatterns = {"/index.html"})

public class IndexServlet extends HttpServlet {

    public static final String URL = "/index.html";

    @EJB
    FahrzeugBean fahrzeugBean;
    @EJB
    BuchungBean buchungBean;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Anfrage an die index.jsp weiterleiten
        List<Fahrzeug> fahrzeuge = fahrzeugBean.findAll();
        Date date = new Date();
        for (Fahrzeug fahrzeug : fahrzeuge) {
            boolean notAvailable = false;
            List<Buchung> buchungen = buchungBean.findByFahrzeug(fahrzeug);
            if (buchungen != null) {
                /*for (Buchung buchung : buchungen) {
                    if (!date.after(buchung.getGeliehenBis())) {
                        if (date.after(buchung.getGeliehenAb()) && date.before(buchung.getGeliehenBis())) {
                            notAvailable = true;
                        }
                    }
                }*/
            }
            if (notAvailable) {
                fahrzeug.setLeihStatus(FahrzeugStatus.AUSGELIEHEN);
            }
        }
        request.setAttribute("AlleFahrzeuge", fahrzeuge);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
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
        //hier muss der Filter geprüft werden
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

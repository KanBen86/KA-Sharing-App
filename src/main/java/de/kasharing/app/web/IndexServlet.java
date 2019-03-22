/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.web;

import de.kasharing.app.ejb.BuchungBean;
import de.kasharing.app.ejb.FahrzeugBean;
import de.kasharing.app.enums.FahrzeugStatus;
import de.kasharing.app.enums.ResponseStatus;
import de.kasharing.app.helper.Response;
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
 * @author Dominik Kunzmann
 */
@WebServlet(urlPatterns = {"/index.html"})

public class IndexServlet extends HttpServlet {

    public static final String URL = "/index.html";

    @EJB
    FahrzeugBean fahrzeugBean;
    @EJB
    BuchungBean buchungBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Anfrage an die index.jsp weiterleiten
        Response<Fahrzeug> fahrzeugResponse = fahrzeugBean.findAll();
        Date date = new Date();
        if (fahrzeugResponse.getStatus() == ResponseStatus.ERFOLGREICH) {
            if (!fahrzeugResponse.getResponseList().isEmpty()) {
                for (Fahrzeug fahrzeug : fahrzeugResponse.getResponseList()) {
                    boolean notAvailable = fahrzeug.isDeaktiviert();
                    List<Buchung> buchungen = buchungBean.findByFahrzeug(fahrzeug).getResponseList();
                    if (buchungen != null) {
                        for (Buchung buchung : buchungen) {
                            if (!date.after(buchung.getGeliehenBis())) {
                                if (date.after(buchung.getGeliehenAb()) && date.before(buchung.getGeliehenBis())) {
                                    notAvailable = true;
                                }
                            }
                        }
                    }
                    if (notAvailable) {
                        fahrzeug.setLeihStatus(FahrzeugStatus.NICHTVERFUEGBAR);
                        fahrzeugResponse.getResponseList().remove(fahrzeug);
                    }
                }
            }
        }
        if (fahrzeugResponse.getStackTrace() != null) {
            for (StackTraceElement e : fahrzeugResponse.getStackTrace()) {
                System.out.println(e);
            }
        }
        request.setAttribute("AlleFahrzeuge", fahrzeugResponse);
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

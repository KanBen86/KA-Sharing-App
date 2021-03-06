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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

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

    @Transactional
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Anfrage an die index.jsp weiterleiten
        Response<Fahrzeug> fahrzeugResponse = fahrzeugBean.findAll();
        Date date = new Date();
        if (fahrzeugResponse.getStatus() == ResponseStatus.ERFOLGREICH) {
            if (fahrzeugResponse.getResponseList() != null) {
                if (!fahrzeugResponse.getResponseList().isEmpty()) {
                    for (Fahrzeug fahrzeug : fahrzeugResponse.getResponseList()) {
                        boolean notAvailable = fahrzeug.isDeaktiviert();
                        List<Buchung> buchungen = buchungBean.findByFahrzeug(fahrzeug).getResponseList();
                        if (buchungen != null) {
                            for (Buchung buchung : buchungen) {
                                if (buchung.isActive()) {
                                    if (date.getTime() > buchung.getGeliehenBis().getTime()) {
                                        if (date.getTime() >= buchung.getGeliehenAb().getTime() && 
                                                date.getTime() <= buchung.getGeliehenBis().getTime()) {
                                            notAvailable = true;
                                        }
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
        Response<Fahrzeug> fahrzeugResponse = new Response<>();
        fahrzeugResponse.setResponseList(new ArrayList<Fahrzeug>());

        // hier wird das Datum der Verfügbarkeit gefiltert
        filterByDatum(request, fahrzeugResponse);

        // hier können zukünftig weitere Filter eingebunden werden. Jedoch sollten alle Methoden mit dem Grundbestand fahzeugResponse arbeiten.
        // Hier werden die Fahrzeuge an das Frontend übergeben und in der Liste der Fahrzeuge angezeigt.
        request.setAttribute("AlleFahrzeuge", fahrzeugResponse);
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
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

    /**
     * Diese Methode filtert die Suchergebnisse bzgl. aller Fahrzeuge, die im gewählten Zeitraum nicht verfügbar sind.
     *
     * @param request
     * @param fahrzeugResponse
     */
    private void filterByDatum(HttpServletRequest request, Response<Fahrzeug> fahrzeugResponse) {
        Response<Fahrzeug> alleFahrzeugeResponse = fahrzeugBean.findAll();

        try {
            Date von = (new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("filterDatumAb")));
            Date bis = (new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("filterDatumBis")));
            for (Fahrzeug f : alleFahrzeugeResponse.getResponseList()) {
                boolean verfuegbar = true;
                Response<Buchung> buchungenResponse = buchungBean.findByFahrzeug(f);
                for (Buchung b : buchungenResponse.getResponseList()) {
                    if (b.isActive()) {
                        if ((b.getGeliehenAb().getTime() <= von.getTime() && b.getGeliehenBis().getTime() >= von.getTime())
                                || (b.getGeliehenAb().getTime() <= bis.getTime() && b.getGeliehenBis().getTime() >= bis.getTime())) {
                            verfuegbar = false;
                        }
                    }
                }
                if (verfuegbar) {
                    fahrzeugResponse.getResponseList().add(f);
                }
            }
        } catch (ParseException parseException) {
            log("Datum konnte nicht gesetzt werden:" + parseException.getStackTrace());
        }
    }
}

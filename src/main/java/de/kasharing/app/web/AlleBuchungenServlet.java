/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.web;

import de.kasharing.app.jpa.Buchung;
import de.kasharing.app.jpa.Fahrzeug;
import de.kasharing.app.jpa.Nutzer;
import de.kasharing.app.ejb.NutzerBean;
import de.kasharing.app.ejb.BuchungBean;
import de.kasharing.app.ejb.FahrzeugBean;
import javax.ejb.EJB;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import javax.persistence.NoResultException;

/**
 *
 * @author Dominik Kunzmann
 */
@WebServlet(name = "AlleBuchungServlet", urlPatterns = {"/buchungen"})

public class AlleBuchungenServlet extends HttpServlet {

    private final static String URL = "/buchungen/";

    @EJB
    FahrzeugBean fahrzeugBean;

    @EJB
    BuchungBean buchungBean;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code."
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Fahrzeug> fahrzeuge = fahrzeugBean.findAll();
        Date date = new Date();
        for (Fahrzeug fahrzeug : fahrzeuge) {
            boolean notAvailable = false;
            List<Buchung> buchungen = buchungBean.findByFahrzeug(fahrzeug);
            if (buchungen != null) {
                for (Buchung buchung : buchungen) {
                    if (!date.after(buchung.getGeliehenBis())) {
                        if (date.after(buchung.getGeliehenAb()) && date.before(buchung.getGeliehenBis())) {
                            notAvailable = true;
                        }
                    }
                }
            }
            if (!notAvailable) {
                fahrzeuge.remove(fahrzeug);
            }
        }

        request.setAttribute("AlleBuchungsFahrzeuge", fahrzeuge);
        request.getRequestDispatcher("/WEB-INF/buchungen.jsp").forward(request, response);
    }
}

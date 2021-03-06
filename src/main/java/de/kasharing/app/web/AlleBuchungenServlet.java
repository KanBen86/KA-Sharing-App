/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.web;

import de.kasharing.app.jpa.Buchung;
import de.kasharing.app.ejb.BuchungBean;
import de.kasharing.app.ejb.FahrzeugBean;
import de.kasharing.app.enums.ResponseStatus;
import de.kasharing.app.helper.Response;
import javax.ejb.EJB;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author Dominik Kunzmann
 */
@WebServlet(name = "AlleBuchungServlet", urlPatterns = {"/buchungen"})

public class AlleBuchungenServlet extends HttpServlet {

    public final static String URL = "/buchungen";

    @EJB
    FahrzeugBean fahrzeugBean;

    @EJB
    BuchungBean buchungBean;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code."
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Date date = new Date();
        List<Buchung> aktuelleBuchungen = new ArrayList<Buchung>();
        Response<Buchung> buchungResponse = buchungBean.findAll();
        if (buchungResponse.getStatus() == ResponseStatus.ERFOLGREICH) {
            List<Buchung> buchungen = buchungResponse.getResponseList();
            if (buchungen != null) {
                for (Buchung buchung : buchungen) {
                    System.out.println(buchung);
                    if (date.getTime() <= buchung.getGeliehenBis().getTime()) {
                        if (date.getTime() >= buchung.getGeliehenAb().getTime()) {
                            if (buchung.isActive()) {
                                aktuelleBuchungen.add(buchung);
                            }
                        }
                    }
                }
            }
        } else {
            log(buchungResponse.getException() + ": " + buchungResponse.getMessage());
            request.setAttribute("message", buchungResponse.getMessage());
        }
        buchungResponse.setResponseList(aktuelleBuchungen);
        System.out.println(aktuelleBuchungen);
        request.setAttribute(
                "AlleBuchungsFahrzeuge", buchungResponse);
        request.getRequestDispatcher("/WEB-INF/buchungen.jsp").forward(request, response);
    }
}

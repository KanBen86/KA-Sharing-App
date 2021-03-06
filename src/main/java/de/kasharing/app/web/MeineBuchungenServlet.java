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
import de.kasharing.app.jpa.Kunde;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dominik Kunzmann
 */
@WebServlet(name = "MeineBuchungenServlet", urlPatterns = {"/meinebuchungen"})

public class MeineBuchungenServlet extends HttpServlet {

    public final static String URL = "/meinbuchungen";

    @EJB
    FahrzeugBean fahrzeugBean;

    @EJB
    BuchungBean buchungBean;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code."
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Response<Kunde> k = (Response<Kunde>) session.getAttribute("kunde");

        //Hier wird anstatt von findAll() die Methode "findByKunde(kunde) eingesetzt wenn Benjamin diese fertig hat
        Response<Buchung> buchungResponse = buchungBean.findByNutzer(k.getResponse());
        if (buchungResponse.getStackTrace() != null) {
            for (StackTraceElement e : buchungResponse.getStackTrace()) {
                log(e.toString());
            }
        }
        log(buchungResponse.getException());

        request.setAttribute("MeineFahrzeuge", buchungResponse);
        request.getRequestDispatcher("/WEB-INF/meinebuchungen.jsp").forward(request, response);
    }
}

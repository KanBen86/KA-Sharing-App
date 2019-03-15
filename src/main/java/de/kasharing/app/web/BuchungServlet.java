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
 * @author Orlando Jähde
 */
@WebServlet(name = "BuchungServlet", urlPatterns = {"/book/*"})

public class BuchungServlet extends HttpServlet {

    private final static String URL = "/book/";

    @EJB
    FahrzeugBean fahrzeugBean;

    @EJB
    BuchungBean buchungBean;

    @EJB
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

        log("Die Post-Methode ist aufgerufen");
        long id = -1;
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.length() > 2) {
            try {
                id = Long.parseLong(pathInfo.split("/")[1]);
            } catch (NumberFormatException ex) {
                // URL enthält keine gültige Long-Zahl
            }
        }

        // Erstellen eines Nutzer zu Testzwecken
        // an dieser Stelle wird später der Nutzer aus der Session ausgelesen
        Nutzer nutzer = new Nutzer();

        nutzer.setNickName("Dieter");
        Nutzer tempNutzer = nutzerBean.findByNick(nutzer.getNickName());
        if (tempNutzer != null) {
            nutzer = tempNutzer;
        } else {
            log("Es konnte kein Datenbankeintrag gefunden werden");
            nutzer = nutzerBean.createNutzer(nutzer);
        }

        // Das Fahrzeug in der Datenbank suchen
        Fahrzeug fahrzeug = fahrzeugBean.findById(id);

        //Erstellen der Buchung
        Buchung buchung = new Buchung();

        //Füllen der Buchung mit nötigen Informationen
        buchung.setFahrzeug(fahrzeug);
        buchung.setNutzer(nutzer);

        try {
            System.out.println(request.getParameter("startDatum"));
            buchung.setGeliehenAb(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDatum")));
            System.out.println(request.getParameter("endDatum"));
            buchung.setGeliehenBis(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endDatum")));
        } catch (ParseException parseException) {
            log("Datum konnte nicht gesetzt werden:" + parseException.getStackTrace());
        } catch (NullPointerException ex){
            log("Das Datum konnte nicht gesetzt werden: "+ ex.getMessage());
        }

        //Kontrolle, ab Buchung korrekt erstellt wurde
        if (buchung.checkValues()) {
            buchungBean.createBuchung(buchung);
            response.sendRedirect(request.getContextPath() + IndexServlet.URL);
        } else {
            log("Buchung konnte nicht erstellt werden.");
            System.out.println("Buchung konnte nicht erstellt werden.");
            response.sendRedirect(request.getContextPath() + BuchungServlet.URL + id);
        }
    }

}

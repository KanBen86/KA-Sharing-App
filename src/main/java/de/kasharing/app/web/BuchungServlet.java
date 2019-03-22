/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.web;

import de.kasharing.app.jpa.Buchung;
import de.kasharing.app.jpa.Fahrzeug;
import de.kasharing.app.jpa.Nutzer;
import de.kasharing.app.ejb.KundeBean;
import de.kasharing.app.ejb.MitarbeiterBean;
import de.kasharing.app.ejb.BuchungBean;
import de.kasharing.app.ejb.FahrzeugBean;
import de.kasharing.app.enums.ResponseStatus;
import de.kasharing.app.helper.Response;
import de.kasharing.app.jpa.Kunde;
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
import javax.servlet.http.HttpSession;

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
    KundeBean kundeBean;

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

        Response<Fahrzeug> detailFahrzeug = fahrzeugBean.findById(id);
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
        /*Nutzer nutzer = new Nutzer();

        nutzer.setNickName("Dieter");
        Response<Nutzer> tempNutzer = nutzerBean.findByNick(nutzer.getNickName());
        if (tempNutzer.getResponse() != null) {
            nutzer = tempNutzer.getResponse();
        } else {
            log("Es konnte kein Datenbankeintrag gefunden werden");
            nutzer = nutzerBean.createNutzer(nutzer);
        }*/
        
        // Das Fahrzeug in der Datenbank suchen
        Fahrzeug fahrzeug = fahrzeugBean.findById(id).getResponse();
        
        //Erstellen der Buchung
        Buchung buchung = new Buchung();

        //Füllen der Buchung mit nötigen Informationen
        buchung.setFahrzeug(fahrzeug);        
        
        HttpSession session = request.getSession();
        
        if (session.getAttribute("kunde") != null) {       
            Kunde k = (Kunde) session.getAttribute("kunde");
            buchung.setNutzer(k);
        }
        else if (session.getAttribute("mitarbeiter") != null) {
            log ("Sie sind als Mitarbeiter eingeloggt!");
            response.sendRedirect(request.getContextPath() + "/index");
        }
        else {
            log ("Bitte loggen Sie sich erst ein!");
            response.sendRedirect(request.getContextPath() + "/login");
        }        

        Date date1 = new Date();
        Date date2 = new Date();

        SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy");
        ft.setLenient(false);

        try {
            date1 = ft.parse(request.getParameter("startDatum"));
            date2 = ft.parse(request.getParameter("endDatum"));
            System.out.println(date1.toString());
            buchung.setGeliehenAb(date1);
            System.out.println(date2.toString());
            buchung.setGeliehenBis(date2);
        } catch (ParseException parseException) {
            log("Datum konnte nicht gesetzt werden:" + parseException.getStackTrace());
        } catch (NullPointerException ex) {
            log("Das Datum konnte nicht gesetzt werden: " + ex.getMessage());
        }

        //Kontrolle, ab Buchung korrekt erstellt wurde
        if (buchung.checkValues()) {
            Response<Buchung> buchungResponse = buchungBean.createBuchung(buchung);
            if (buchungResponse.getStatus() == ResponseStatus.ERFOLGREICH) {
                response.sendRedirect(request.getContextPath() + IndexServlet.URL);
            } else {
                response.sendRedirect(request.getContextPath() + BuchungServlet.URL + id);
            }
        } else {
            log("Buchung konnte nicht erstellt werden.");
            System.out.println("Buchung konnte nicht erstellt werden.");
            response.sendRedirect(request.getContextPath() + BuchungServlet.URL + id);
        }
    }

}

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(urlPatterns = {"/detail/*"})

public class FahrzeugDetailServlet extends HttpServlet {

    public final static String URL = "/detail";

    @EJB
    FahrzeugBean fahrzeugBean;

    FahrzeugStatus fahrzeugStatus;
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

        FahrzeugStatus[] statusList = fahrzeugStatus.values();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        Fahrzeug f = new Fahrzeug();

        long id = -1;
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.length() > 2) {
            try {
                f.setId(Long.parseLong(pathInfo.split("/")[1]));
            } catch (NumberFormatException ex) {
                // URL enthält keine gültige Long-Zahl
            }
        }
        //Setzen der Werte für das neue Fahrzeug
        //Boolsche Variablen:
        f.setAbs(request.getParameter("abs") != null);
        f.setCd(request.getParameter("cd") != null);
        f.setElektrischeFensterheber(request.getParameter("elektrischerFensterheber") != null);
        f.setEsp(request.getParameter("esp") != null);
        f.setFahrassiSystem(request.getParameter("fahrassiSystem") != null);
        f.setNavigation(request.getParameter("navigation") != null);
        f.setKlimaanlage(request.getParameter("klimaanlage") != null);
        f.setServolenkung(request.getParameter("servolenkung") != null);
        //String Variablen:
        f.setModell(request.getParameter("modell"));
        f.setAusfuehrung(request.getParameter("ausfuehrung"));
        //Datums Variablen:
        try {
            f.setAnschaffungsDatum(new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("anschaffungsDatum")));
            f.setHauptuntersuchungBis(new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("hauptuntersuchungBis")));
        } catch (ParseException parseException) {
            log("Datum konnte nicht gesetzt werden:" + parseException.getStackTrace());
        }
        //Float/Integer Variablen:           
        f.setAnschaffungsPreis(Float.parseFloat(request.getParameter("anschaffungsPreis")));
        f.setPlaetze(Integer.parseInt(request.getParameter("plaetze")));
        f.setPreisProTag(Float.parseFloat(request.getParameter("preisProTag")));
        f.setRaeder(Integer.parseInt(request.getParameter("raeder")));
        //Enums:
        f.setGetriebeart(Enum.valueOf(FahrzeugGetriebeArt.class, request.getParameter("getriebeart")));
        f.setHersteller(Enum.valueOf(FahrzeugHersteller.class, request.getParameter("hersteller")));
        f.setKlasse(Enum.valueOf(FahrzeugKlasse.class, request.getParameter("klasse")));
        f.setLeihStatus(Enum.valueOf(FahrzeugStatus.class, request.getParameter("leihstatus")));
        f.setTyp(Enum.valueOf(FahrzeugTyp.class, request.getParameter("typ")));
        //Fahrzeugbild: (to-do)
        //f.setBild(request.getAttribute("bild"));

        //Kontrolle, ab Fahrzeug korrekt erstellt wurde
        //if (f.checkValues()) {
        f = fahrzeugBean.updateFahrzeug(f);
        log("Fahrzeug sollte persistiert sein: " + f);
        response.sendRedirect(request.getContextPath() + IndexServlet.URL);
        /*}
            else {
                System.out.println("Fahrzeug konnte nicht erstellt werden.");
                response.sendRedirect(request.getContextPath() + CreateFahrzeugServlet.URL);
            }*/
    }

}

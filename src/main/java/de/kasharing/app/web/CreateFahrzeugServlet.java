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
import de.kasharing.app.enums.FahrzeugStatus;
import de.kasharing.app.enums.FahrzeugTyp;
import de.kasharing.app.jpa.Fahrzeug;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.text.ParseException;
import java.awt.Image;

/**
 *
 * @author Benjamin Kanzler
 */
@WebServlet(name = "CreateFahrzeugServlet", urlPatterns = {"/newCar"})
public class CreateFahrzeugServlet extends HttpServlet {
    
    public static final String URL = "/newCar";

    @EJB
    FahrzeugBean fahrzeugBean;
    
    FahrzeugStatus fahrzeugStatus;
    FahrzeugGetriebeArt fahrzeugGetriebeArt;
    FahrzeugHersteller fahrzeugHersteller;
    FahrzeugKlasse fahrzeugKlasse;
    FahrzeugTyp fahrzeugTyp;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Fahrzeug neuesFahrzeug = new Fahrzeug();
        request.setAttribute("detailFahrzeug", neuesFahrzeug);
        
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
        
            request.setCharacterEncoding("utf-8");
        
            Fahrzeug f = new Fahrzeug();
            
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
            }
            catch (ParseException parseException) {
                log ("Datum konnte nicht gesetzt werden:" + parseException.getStackTrace());
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
            f.setLeihStatus(Enum.valueOf(FahrzeugStatus.class, request.getParameter("leihStatus")));
            f.setTyp(Enum.valueOf(FahrzeugTyp.class, request.getParameter("typ")));
            //Fahrzeugbild: (to-do)
            //f.setBild(request.getAttribute("bild"));
            
            //Kontrolle, ab Fahrzeug korrekt erstellt wurde
            //if (f.checkValues()) {
                f = fahrzeugBean.createFahrzeug(f);
                response.sendRedirect(request.getContextPath() + "/" + f.getId());
            /*}
            else {
                System.out.println("Fahrzeug konnte nicht erstellt werden.");
                response.sendRedirect(request.getContextPath() + CreateFahrzeugServlet.URL);
            }*/
    }

}

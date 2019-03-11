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
        
            Fahrzeug f = new Fahrzeug();
            
            /*f.setAbs(request.getAttribute("abs"));
            f.setAnschaffungsDatum(request.getAttribute("anschaffungsDatum"));
            f.setAnschaffungsPreis(request.getAttribute("anschaffungsPreis"));
            f.setAusfuehrung(request.getAttribute("ausfuehrung"));
            f.setBild(request.getAttribute("bild"));
            f.setCd(request.getAttribute("cd"));
            f.setElektrischeFensterheber(request.getAttribute("elektrischerFensterheber"));
            f.setEsp(request.getAttribute("esp"));
            f.setFahrassiSystem(request.getAttribute("fahrassiSystem"));
            f.setGetriebeart(request.getAttribute("getriebeart"));
            f.setHauptuntersuchungBis(request.getAttribute("hauptuntersuchungBis"));
            f.setHersteller(request.getAttribute("hersteller"));
            f.setKlasse(request.getAttribute("klasse"));
            f.setKlimaanlage(request.getAttribute("klimaanlage"));
            f.setLeihStatus(request.getAttribute("leihStatus"));
            f.setModell(request.getAttribute("modell"));
            f.setNavigation(request.getAttribute("navigation"));
            f.setPlaetze(request.getAttribute("plaetze"));
            f.setPreisProTag(request.getAttribute("preisProTag"));
            f.setRaeder(request.getAttribute("raeder"));
            f.setServolenkung(request.getAttribute("servolenkung"));
            f.setTyp(request.getAttribute("typ"));*/
            
            System.out.println(request.getAttribute("abs"));
            System.out.println("Test");
            
            //Kontrolle, ab Fahrzeug korrekt erstellt wurde
            /*if (f.checkValues()) {
                f = fahrzeugBean.createFahrzeug(f);
            }
            else {
                System.out.println("Fahrzeug konnte nicht erstellt werden.");
                response.sendRedirect(request.getContextPath() + CreateFahrzeugServlet.URL);
            }*/
    }

}

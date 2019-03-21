/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import de.kasharing.app.jpa.Nutzer;
import de.kasharing.app.jpa.Adresse;
import de.kasharing.app.jpa.Bankverbindung;
import javax.ejb.EJB;
import de.kasharing.app.ejb.NutzerBean;
import de.kasharing.app.ejb.BankverbindungBean;
import de.kasharing.app.ejb.AdresseBean;
import de.kasharing.app.enums.ResponseStatus;
import de.kasharing.app.enums.NutzerRolle;
import de.kasharing.app.helper.Response;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Orlando Jähde
 */

@WebServlet(urlPatterns = {"/editProfile/"})
public class BenutzerverwaltungServlet extends HttpServlet {
    
    @EJB
    NutzerBean nutzerBean;
    
    @EJB
    AdresseBean adresseBean;
    
    @EJB
    BankverbindungBean bankverbindungBean;
    
    NutzerRolle nutzerRolle;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        HttpSession s = request.getSession();
        
        if (s.getAttribute("nutzer") == null) {
            log("Kein Nutzer angemeldet");
            response.sendRedirect(request.getContextPath() + "login");
        }
        Nutzer n = (Nutzer) s.getAttribute("nutzer");
        request.setAttribute("benutzer", n);
        
        Adresse a = n.getAdresse();
        request.setAttribute("Adresse", a);
        
        Bankverbindung bv = n.getBank();
        request.setAttribute("bankverbindung", bv);
        
        request.getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession s = request.getSession();
        
        if (s.getAttribute("nutzer") == null) {
            log("Kein Nutzer angemeldet");
            response.sendRedirect(request.getContextPath() + "login");
        }
        
        Nutzer n = (Nutzer) s.getAttribute("nutzer");
        
        Adresse a = n.getAdresse();
        Bankverbindung bv = n.getBank();
        
        //Bankverbindungsdaten einlesen
        bv.setBic(request.getParameter("bic"));
        bv.setIban(request.getParameter("iban"));
        bv.setInsitut(request.getParameter("institut"));
        
        //Adressdaten einlesen
        a.setHausnummer(request.getParameter("hausnummer"));
        a.setName(request.getParameter("name"));
        a.setVorname(request.getParameter("vorname"));
        a.setOrt(request.getParameter("ort"));
        a.setPlz(request.getParameter("plz"));
        a.setStrasse(request.getParameter("strasse"));
        
        //Nutzerdaten einlesen und um Adresse und Bankverbindung ergänzen
        n.setAdresse(a);
        n.setBank(bv);
        n.setEmail(request.getParameter("email"));
        n.setNickName(request.getParameter("nickName"));
        n.setPasswort(request.getParameter("passwort"));
        try {
            n.setRolle(Enum.valueOf(NutzerRolle.class, request.getParameter("nutzerRolle")));
        }
        catch (IllegalArgumentException ex) {
            System.out.println("Nutzerrolle wurde nicht ausgefüllt:" + ex);
        }
        
        a = adresseBean.updateAdresse(a).getResponse();
        bv = bankverbindungBean.updateBankverbindung(bv).getResponse();
        n = nutzerBean.updateNutzer(n);
        
        s.setAttribute("nutzer", n);
        
        response.sendRedirect(request.getContextPath() + "editProfile/");
        
    }


}

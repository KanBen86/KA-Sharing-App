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
@WebServlet(name = "RegistrierungServlet", urlPatterns = {"/register"})
public class RegistrierungServlet extends HttpServlet {
    
    private final static String URL = "/register/";
    
    @EJB
    NutzerBean nutzerBean;
    
    @EJB
    BankverbindungBean bankverbindungBean;    
    
    @EJB
    AdresseBean adresseBean;
    
    NutzerRolle nutzerRolle;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        NutzerRolle [] rollenList = nutzerRolle.values();
        request.setAttribute("rollenList", rollenList);
        
        request.getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession s = request.getSession();
        
        request.setCharacterEncoding("utf-8");
        
        Nutzer n = new Nutzer();
        Adresse a = new Adresse();
        Bankverbindung bv = new Bankverbindung();
        
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
        
        a = adresseBean.createNewAdresse(a).getResponse();
        bv = bankverbindungBean.createNewBankverbindung(bv).getResponse();
        n = nutzerBean.createNutzer(n);
        
        s.setAttribute("nutzer", n);
        
        response.sendRedirect(request.getContextPath() + "/index.html");
    }

}

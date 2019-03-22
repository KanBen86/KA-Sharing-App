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
import de.kasharing.app.ejb.KundeBean;
import de.kasharing.app.ejb.MitarbeiterBean;
import de.kasharing.app.ejb.BankverbindungBean;
import de.kasharing.app.ejb.AdresseBean;
import de.kasharing.app.enums.ResponseStatus;
import de.kasharing.app.enums.NutzerRolle;
import de.kasharing.app.helper.Response;
import de.kasharing.app.jpa.Kunde;
import de.kasharing.app.jpa.Mitarbeiter;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Orlando Jähde
 */

@WebServlet(urlPatterns = {"/editProfile/"})
public class BenutzerverwaltungServlet extends HttpServlet {
    
    @EJB
    KundeBean kundeBean;
    
    @EJB
    MitarbeiterBean mitarbeiterBean;
    
    @EJB
    AdresseBean adresseBean;
    
    @EJB
    BankverbindungBean bankverbindungBean;
    
    NutzerRolle nutzerRolle;
    
    boolean istKunde = false;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        HttpSession s = request.getSession();
        
        if (s.getAttribute("kunde") == null && s.getAttribute("mitarbeiter") == null) {
            log("Kein Nutzer angemeldet");
            response.sendRedirect(request.getContextPath() + "login");
        }
        else if (s.getAttribute("mitarbeiter") == null) {
            Kunde k = (Kunde) s.getAttribute("kunde");
            
            Adresse a = k.getAdresse();
            request.setAttribute("Adresse", a);

            Bankverbindung bv = k.getBank();
            request.setAttribute("bankverbindung", bv);
            
            istKunde = true;
        }
        else if (s.getAttribute("kunde") == null) {
            Mitarbeiter m = (Mitarbeiter) s.getAttribute("mitarbeiter");
            
            Adresse a = m.getAdresse();
            request.setAttribute("Adresse", a);

            Bankverbindung bv = m.getBank();
            request.setAttribute("bankverbindung", bv);
        }
        
        request.getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession s = request.getSession();
        
        if (s.getAttribute("kunde") == null && s.getAttribute("mitarbeiter") == null) {
            log("Kein Nutzer angemeldet");
            response.sendRedirect(request.getContextPath() + "login");
        }
        
        Nutzer n = (Nutzer) s.getAttribute("nutzer");
        
        if (istKunde) {
            Kunde k = (Kunde) s.getAttribute("kunde");
            
            Adresse a = k.getAdresse();
            Bankverbindung bv = k.getBank();
            
            
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
            k.setAdresse(a);
            k.setBank(bv);
            k.setEmail(request.getParameter("email"));
            k.setNickName(request.getParameter("nickName"));
            k.setPasswort(request.getParameter("passwort"));
            /*try {
                n.setRolle(Enum.valueOf(NutzerRolle.class, request.getParameter("nutzerRolle")));
            }
            catch (IllegalArgumentException ex) {
                System.out.println("Nutzerrolle wurde nicht ausgefüllt:" + ex);
            }*/

            a = adresseBean.updateAdresse(a).getResponse();
            bv = bankverbindungBean.updateBankverbindung(bv).getResponse();
            k = kundeBean.updateNutzer(k).getResponse();

            s.setAttribute("kunde", k);
        }
        else if (!istKunde) {
            Mitarbeiter m = (Mitarbeiter) s.getAttribute("mitarbeiter");
            
            Adresse a = m.getAdresse();
            
            //Adressdaten einlesen
            a.setHausnummer(request.getParameter("hausnummer"));
            a.setName(request.getParameter("name"));
            a.setVorname(request.getParameter("vorname"));
            a.setOrt(request.getParameter("ort"));
            a.setPlz(request.getParameter("plz"));
            a.setStrasse(request.getParameter("strasse"));

            //Nutzerdaten einlesen und um Adresse und Bankverbindung ergänzen
            m.setAdresse(a);
            m.setEmail(request.getParameter("email"));
            m.setNickName(request.getParameter("nickName"));
            m.setPasswort(request.getParameter("passwort"));
            /*try {
                n.setRolle(Enum.valueOf(NutzerRolle.class, request.getParameter("nutzerRolle")));
            }
            catch (IllegalArgumentException ex) {
                System.out.println("Nutzerrolle wurde nicht ausgefüllt:" + ex);
            }*/

            a = adresseBean.updateAdresse(a).getResponse();
            m = mitarbeiterBean.updateNutzer(m).getResponse();

            s.setAttribute("mitarbeiter", m);
        }
                
        response.sendRedirect(request.getContextPath() + "editProfile/");
        
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import de.kasharing.app.jpa.Adresse;
import de.kasharing.app.jpa.Bankverbindung;
import javax.ejb.EJB;
import de.kasharing.app.ejb.KundeBean;
import de.kasharing.app.ejb.MitarbeiterBean;
import de.kasharing.app.ejb.BankverbindungBean;
import de.kasharing.app.ejb.AdresseBean;
import de.kasharing.app.enums.NutzerRolle;
import de.kasharing.app.helper.Response;
import de.kasharing.app.jpa.Kunde;
import de.kasharing.app.jpa.Mitarbeiter;
import de.kasharing.app.jpa.Nutzer;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Orlando Jähde
 */
@WebServlet(urlPatterns = {"/editProfile/"})
public class BenutzerverwaltungServlet extends HttpServlet {

    public static final String URL = "/editProfile/";

    @EJB
    KundeBean kundeBean;

    @EJB
    MitarbeiterBean mitarbeiterBean;

    @EJB
    AdresseBean adresseBean;

    @EJB
    BankverbindungBean bankverbindungBean;

    NutzerRolle nutzerRolle;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession s = request.getSession();

        if (s.getAttribute("kunde") == null && s.getAttribute("mitarbeiter") == null) {
            log("Kein Nutzer angemeldet");
            response.sendRedirect(request.getContextPath() + "login");
        } else if (s.getAttribute("mitarbeiter") == null) {
            Response<Kunde> k = (Response<Kunde>) s.getAttribute("kunde");

            Adresse a = k.getResponse().getAdresse();
            request.setAttribute("Adresse", a);

            Bankverbindung bv = k.getResponse().getBank();
            request.setAttribute("bankverbindung", bv);
        } else if (s.getAttribute("kunde") == null) {
            Response<Mitarbeiter> m = (Response<Mitarbeiter>) s.getAttribute("mitarbeiter");

            Adresse a = m.getResponse().getAdresse();
            request.setAttribute("Adresse", a);

            Bankverbindung bv = m.getResponse().getBank();
            request.setAttribute("bankverbindung", bv);
        }

        request.getRequestDispatcher("/WEB-INF/nutzerprofil.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession s = request.getSession();

        if (s.getAttribute("kunde") == null && s.getAttribute("mitarbeiter") == null) {
            log("Kein Nutzer angemeldet");
            response.sendRedirect(request.getContextPath() + "login");
        }

        if (s.getAttribute("mitarbeiter") == null) {
            Response<Kunde> k = (Response<Kunde>) s.getAttribute("kunde");

            k = kundeBean.updateNutzer(befuelleNutzer(k.getResponse(), request));
            s.setAttribute("kunde", k);
        } else if (s.getAttribute("mitarbeiter") == null) {
            Response<Mitarbeiter> m = (Response<Mitarbeiter>) s.getAttribute("mitarbeiter");

            /*try {
                n.setRolle(Enum.valueOf(NutzerRolle.class, request.getParameter("nutzerRolle")));
            }
            catch (IllegalArgumentException ex) {
                System.out.println("Nutzerrolle wurde nicht ausgefüllt:" + ex);
            }*/
            m = mitarbeiterBean.updateNutzer(befuelleNutzer(m.getResponse(), request));

            s.setAttribute("mitarbeiter", m);
        }

        response.sendRedirect(request.getContextPath() + BenutzerverwaltungServlet.URL);

    }

    private <E extends Nutzer> E befuelleNutzer(E n, HttpServletRequest request) {
        Adresse a = new Adresse();
        if (n.getAdresse() != null) {
            log("Die Adresse im Kunde ist nicht null");
            a = n.getAdresse();
        } else {
            n.setAdresse(adresseBean.createNewAdresse(a).getResponse());
        }
        Bankverbindung bv = new Bankverbindung();
        if (n.getBank() != null) {
            log("Die Bankverbindung im Kunde ist nicht null");
            bv = n.getBank();
        } else {
            n.setBank(bankverbindungBean.createNewBankverbindung(bv).getResponse());
        }

        log("Bankverbindung: " + bv);
        log("Adresse: " + a);

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
        n.setEmail(request.getParameter("email"));
        n.setNickName(request.getParameter("nickName"));
        n.setPasswort(request.getParameter("passwort"));
        /*try {
                n.setRolle(Enum.valueOf(NutzerRolle.class, request.getParameter("nutzerRolle")));
            }
            catch (IllegalArgumentException ex) {
                System.out.println("Nutzerrolle wurde nicht ausgefüllt:" + ex);
            }*/
        n.setAdresse(adresseBean.updateAdresse(a).getResponse());
        n.setBank(bankverbindungBean.updateBankverbindung(bv).getResponse());
        return n;
    }

}

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
import de.kasharing.app.ejb.MitarbeiterBean;
import de.kasharing.app.ejb.KundeBean;
import de.kasharing.app.ejb.BankverbindungBean;
import de.kasharing.app.ejb.AdresseBean;
import de.kasharing.app.enums.NutzerRolle;
import de.kasharing.app.enums.ResponseStatus;
import de.kasharing.app.helper.Response;
import de.kasharing.app.jpa.Kunde;
import de.kasharing.app.jpa.Mitarbeiter;
import de.kasharing.app.jpa.Nutzer;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Orlando Jähde
 */
@WebServlet(name = "RegistrierungServlet", urlPatterns = {"/registrieren"})
public class RegistrierungServlet extends HttpServlet {

    private final static String URL = "/registrieren/";

    @EJB
    protected MitarbeiterBean mitarbeiterBean;

    @EJB
    protected KundeBean kundeBean;

    @EJB
    protected BankverbindungBean bankverbindungBean;

    @EJB
    protected AdresseBean adresseBean;

    private NutzerRolle nutzerRolle;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        NutzerRolle[] rollenList = nutzerRolle.values();
        request.setAttribute("rollenList", rollenList);

        request.getRequestDispatcher("/WEB-INF/registrieren.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession s = request.getSession();

        request.setCharacterEncoding("utf-8");
        //Nutzerdaten einlesen und um Adresse und Bankverbindung ergänzen
//        n.setAdresse(a);
//        n.setBank(bv);
        if (Integer.parseInt(request.getParameter("nutzer")) == 1) {
            Kunde k = new Kunde();
            befuelleNutzer(k, request);
            Response<Kunde> responseK = kundeBean.createNutzer(k);

        } else if (Integer.parseInt(request.getParameter("nutzer")) == 2) {
            Mitarbeiter m = new Mitarbeiter();
            befuelleNutzer(m, request);
            Response<Mitarbeiter> responseM = mitarbeiterBean.createNutzer(m);
        } else {
            System.out.println("Nutzerrolle wurde nicht ausgefüllt!");
        }
        response.sendRedirect(request.getContextPath() + "/index.html");
    }

    private <E extends Nutzer> void befuelleNutzer(E n, HttpServletRequest request) {
        Adresse a = new Adresse();
        Bankverbindung bv = new Bankverbindung();
        n.setEmail(request.getParameter("email"));
        n.setNickName(request.getParameter("nickName"));
        n.setPasswort(request.getParameter("passwort"));
        n.setBank(bankverbindungBean.createNewBankverbindung(bv).getResponse());
        n.setAdresse(adresseBean.createNewAdresse(a).getResponse());
    }
}

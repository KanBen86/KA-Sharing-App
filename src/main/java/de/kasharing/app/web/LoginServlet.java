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
import javax.ejb.EJB;
import de.kasharing.app.ejb.KundeBean;
import de.kasharing.app.ejb.MitarbeiterBean;
import de.kasharing.app.enums.ResponseStatus;
import de.kasharing.app.helper.Response;
import de.kasharing.app.jpa.Kunde;
import de.kasharing.app.jpa.Mitarbeiter;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Orlando Jähde
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    public final static String URL = "/login";

    @EJB
    KundeBean kundeBean;

    @EJB
    MitarbeiterBean mitarbeiterBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/einloggen.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String nickName = request.getParameter("nickName");
        String passwort = request.getParameter("passwort");
        Response<Mitarbeiter> responseM = mitarbeiterBean.findByNick(nickName);
        Response<Kunde> responseK = kundeBean.findByNick(nickName);

        if (Integer.parseInt(request.getParameter("nutzer")) == 1) {
            log("Kundenanmeldung:\n\tSetze mitarbeiter auf null...");
            responseM = null;
            if (responseK.getStatus() == ResponseStatus.ERFOLGREICH) {
                log("\tDer Nickname passt zu einem Kunden...");
                log("\t\tPW der GUI: " + passwort);
                log("\t\tPW des Obj: " + responseK.getResponse().getPasswort());
                if (responseK.getResponse().getPasswort().trim().equals(passwort.trim())) {
                    log("\tDas Passwort wurde richtig eingegeben...");
                    log("\tSetze Message erfolgreich...");
                    responseK.setMessage("Passworteingabe Erfolgreich!");
                } else {
                    log("\tDas Passwort wurde falsch eingegeben...");
                    log("\tSetze Message error PW falsch...");
                    log("\trequest zur einloggen.jsp");
                    responseK.setStatus(ResponseStatus.ERROR);
                    responseK.setMessage("Falsches Passwort!");
                    request.setAttribute("nickName", nickName);
                    request.getRequestDispatcher("/WEB-INF/einloggen.jsp").forward(request, response);
                    return;
                }
            } else {
                log("\tDer Nickname konnte nicht zu einem User aufgelöst werden...");
                log("\tSetze Message error...");
                log("\trequest zur einloggen.jsp");
                responseK.setMessage("Es gibt keinen Kunden mit dem eingegebenen Namen");
                responseK.setStatus(ResponseStatus.ERROR);
                request.getRequestDispatcher("/WEB-INF/einloggen.jsp").forward(request, response);
                return;
            }
        } else if (Integer.parseInt(request.getParameter("nutzer")) == 2) {
            responseK = null;
            if (responseM.getStatus() == ResponseStatus.ERFOLGREICH) {
                if (responseM.getResponse().getPasswort().trim().equals(passwort.trim())) {
                    responseM.setMessage("Passworteingabe Erfolgreich!");
                } else {
                    responseM.setStatus(ResponseStatus.ERROR);
                    responseM.setMessage("Falsches Passwort!");
                    request.setAttribute("nickName", nickName);
                    request.getRequestDispatcher("/WEB-INF/einloggen.jsp").forward(request, response);
                    return;
                }
            } else {
                responseM.setStatus(ResponseStatus.ERROR);
                responseM.setMessage("Es gibt keinen Mitarbeiter mit dem eingegebenen Namen");
                request.getRequestDispatcher("/WEB-INF/einloggen.jsp").forward(request, response);
                return;

            }
        }
        log("\tSetzte Kunde und Mitarbeiter...");
        log("\tsendRedirect zur Indexseite");
        session.setAttribute("mitarbeiter", responseM);
        session.setAttribute("kunde", responseK);
        response.sendRedirect(request.getContextPath());
    }
}

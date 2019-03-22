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
import de.kasharing.app.jpa.Nutzer;
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

    private final static String URL = "/login/";

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
        

        if (Integer.parseInt(request.getParameter("nutzer")) == 1) {
            System.out.println(nickName);
            Response<Kunde> responseK = kundeBean.findByNick(nickName);
            if (responseK.getStatus() == ResponseStatus.ERFOLGREICH) {
                if (responseK.getResponse().getPasswort() == passwort) {
                    responseK.setMessage("Passworteingabe Erfolgreich!");
                }
            } else {
                    responseK.setStatus(ResponseStatus.ERROR);
                    responseK.setMessage("Falsches Passwort!");
                    request.setAttribute("nickName", nickName);
                    request.getRequestDispatcher("/WEB-INF/einloggen.jsp").forward(request, response);
            }
            session.setAttribute("kunde", responseK);
            session.setAttribute("mitarbeiter", null);

        } else if (Integer.parseInt(request.getParameter("nutzer")) == 2) {
            Response<Mitarbeiter> responseM = mitarbeiterBean.findByNick(nickName);
            if (responseM.getStatus() == ResponseStatus.ERFOLGREICH) {
                if (responseM.getResponse().getPasswort() == passwort) {
                    responseM.setMessage("Passworteingabe Erfolgreich!");
                } else {
                    responseM.setStatus(ResponseStatus.ERROR);
                    responseM.setMessage("Falsches Passwort!");
                    request.setAttribute("nickName", nickName);
                    request.getRequestDispatcher("/WEB-INF/einloggen.jsp").forward(request, response);
                }
            }
            session.setAttribute("mitarbeiter", responseM);
            session.setAttribute("kunde", null);
    
        }
        response.sendRedirect(request.getContextPath() + IndexServlet.URL);
    }
}

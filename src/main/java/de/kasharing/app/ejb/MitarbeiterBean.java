/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.ejb;

import de.kasharing.app.enums.ResponseStatus;
import de.kasharing.app.helper.Response;
import de.kasharing.app.jpa.Kunde;
import de.kasharing.app.jpa.Mitarbeiter;
import de.kasharing.app.jpa.Nutzer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Benjamin Kanzler
 */
@Stateless
public class MitarbeiterBean {

    @PersistenceContext
    protected EntityManager em;

    public Response<Mitarbeiter> createNutzer(Mitarbeiter m) {
        Response<Mitarbeiter> nutzer = new Response<>();
        try {
            em.persist(m);
            nutzer.setResponse(em.merge(m));
            nutzer.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (NoResultException ex) {
            nutzer.setStatus(ResponseStatus.ERROR);
            nutzer.setException(ex.getClass().getName());
            nutzer.setMessage(ex.getMessage());
            nutzer.setStackTrace(ex.getStackTrace());
        }
        return nutzer;
    }

    public Response<Mitarbeiter> updateNutzer(Mitarbeiter m) {
        Response<Mitarbeiter> nutzer = new Response<>();
        try {
            nutzer.setResponse(em.merge(m));
            nutzer.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (NoResultException ex) {
            nutzer.setStatus(ResponseStatus.ERROR);
            nutzer.setException(ex.getClass().getName());
            nutzer.setMessage(ex.getMessage());
            nutzer.setStackTrace(ex.getStackTrace());
        }
        return nutzer;
    }

    public Response<Mitarbeiter> findById(Long id) {
        Response<Mitarbeiter> nutzer = new Response<>();
        try {
            nutzer.setResponse(em.find(Mitarbeiter.class, id));
            nutzer.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (NoResultException ex) {
            nutzer.setStatus(ResponseStatus.ERROR);
            nutzer.setException(ex.getClass().getName());
            nutzer.setMessage(ex.getMessage());
            nutzer.setStackTrace(ex.getStackTrace());
        }
        return nutzer;
    }

    public Response<Mitarbeiter> findByNick(String nick) {
        Response<Mitarbeiter> nutzer = new Response<>();
        try {
            nutzer.setResponse((Mitarbeiter) (em.createQuery("SELECT m FROM Mitarbeiter m WHERE m.nickName LIKE :NICK")
                    .setParameter("NICK", nick)
                    .getSingleResult()));
            nutzer.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (NoResultException ex) {
            nutzer.setStatus(ResponseStatus.ERROR);
            nutzer.setException(ex.getClass().getName());
            nutzer.setMessage(ex.getMessage());
            nutzer.setStackTrace(ex.getStackTrace());
        }
        return nutzer;
    }

    public Response<Mitarbeiter> findByEmail(String email) {
        Response<Mitarbeiter> nutzer = new Response<>();
        nutzer.setResponse((Mitarbeiter) em.createQuery("SELECT m FROM Mitarbeiter m WHERE m.email LIKE :EMAIL")
                .setParameter("EMAIL", email)
                .getSingleResult());
        return nutzer;
    }

}

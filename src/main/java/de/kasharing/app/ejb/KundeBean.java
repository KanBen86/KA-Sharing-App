/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.ejb;

import de.kasharing.app.enums.ResponseStatus;
import de.kasharing.app.helper.Response;
import de.kasharing.app.jpa.Kunde;
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
public class KundeBean {

    @PersistenceContext
    protected EntityManager em;

    public Response<Kunde> createNutzer(Kunde k) {
        Response<Kunde> nutzer = new Response<>();
        try {
            em.persist(k);
            nutzer.setResponse(em.merge(k));
        } catch (NoResultException ex) {
            nutzer.setStatus(ResponseStatus.ERROR);
            nutzer.setException(ex.getClass().getName());
            nutzer.setMessage(ex.getMessage());
            nutzer.setStackTrace(ex.getStackTrace());
        }
        return nutzer;

    }

    public Response<Kunde> updateNutzer(Kunde k) {

        Response<Kunde> nutzer = new Response<>();
        try {
            nutzer.setResponse(em.merge(k));
            nutzer.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (NoResultException ex) {
            nutzer.setStatus(ResponseStatus.ERROR);
            nutzer.setException(ex.getClass().getName());
            nutzer.setMessage(ex.getMessage());
            nutzer.setStackTrace(ex.getStackTrace());
        }
        return nutzer;
    }

    public Response<Kunde> findById(Long id) {
        Response<Kunde> nutzer = new Response<>();
        try {
            nutzer.setResponse(em.find(Kunde.class, id));
            nutzer.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (NoResultException ex) {
            nutzer.setStatus(ResponseStatus.ERROR);
            nutzer.setException(ex.getClass().getName());
            nutzer.setMessage(ex.getMessage());
            nutzer.setStackTrace(ex.getStackTrace());
        }
        return nutzer;
    }

    public Response<Kunde> findByNick(String nick) {
        Response<Kunde> nutzer = new Response<>();
        try {
            nutzer.setResponse((Kunde) (em.createQuery("SELECT k FROM Kunde k WHERE k.nickName LIKE :NICK")
                    .setParameter("NICK", "%"+nick+"%")
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

    public Response<Kunde> findByEmail(String email) {
        Response<Kunde> nutzer = new Response<>();
        nutzer.setResponse((Kunde) em.createQuery("SELECT k FROM Kunde k WHERE k.email LIKE :EMAIL")
                .setParameter("EMAIL", email)
                .getSingleResult());
        return nutzer;
    }

}

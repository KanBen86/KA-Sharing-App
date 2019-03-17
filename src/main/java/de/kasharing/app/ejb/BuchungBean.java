/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.ejb;

import de.kasharing.app.helper.Response;
import de.kasharing.app.jpa.Buchung;
import de.kasharing.app.jpa.Fahrzeug;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Benjamin Kanzler
 */
@Stateless
public class BuchungBean {

    @PersistenceContext
    protected EntityManager em;

    private Query query;

    public Response<Buchung> createBuchung(Buchung b) {
        Response<Buchung> response = new Response<Buchung>();
        b.setActive(true);
        try {
            em.persist(b);
            response.setResponse(em.merge(b));
        } catch (Exception ex) {
            response.setException(ex.getClass().getName());
            response.setStatus("FEHLER");
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Buchung updateBuchung(Buchung b) {
        return em.merge(b);
    }

    public Response<Buchung> findAll() {
        Response<Buchung> response = new Response<Buchung>();
        try {
            response.setResponseList(em.createQuery("SELECT b FROM Buchung b")
                    .getResultList());
        } catch (Exception ex) {
            response.setException(ex.getClass().getName());
            response.setStatus("FEHLER");
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Buchung> findByFahrzeug(Fahrzeug fahrzeug) {
        Response<Buchung> response = new Response<Buchung>();
        try {
            query = em.createQuery("SELECT b FROM Buchung b WHERE b.fahrzeug = :FAHRZEUG")
                    .setParameter("FAHRZEUG", fahrzeug);
            response.setResponseList(query
                    .getResultList());
        } catch (Exception ex) {
            response.setException(ex.getClass().getName());
            response.setStatus("FEHLER");
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Buchung> findByNutzerId(Long id) {
        Response<Buchung> response = new Response<Buchung>();
        try {
            query = em.createQuery("SELECT b FROM Buchung b WHERE b.nutzer LIKE :NID")
                    .setParameter("NID", id);
            response.setResponseList(query
                    .getResultList());
        } catch (Exception ex) {
            response.setException(ex.getClass().getName());
            response.setStatus("FEHLER");
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response<Buchung> storniereBuchung(Buchung b) {
        Response<Buchung> response = new Response<Buchung>();
        try {
            em.remove(b);
            response.setStatus("ERFOLGREICH");
        } catch (Exception ex) {
            response.setStatus("FEHLER");
            response.setException(ex.getClass().getName());
            response.setMessage(ex.getMessage());
        }
        return response;
    }
}

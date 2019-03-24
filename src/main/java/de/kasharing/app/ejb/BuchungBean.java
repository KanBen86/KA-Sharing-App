/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.ejb;

import de.kasharing.app.enums.ResponseStatus;
import de.kasharing.app.helper.Response;
import de.kasharing.app.jpa.Buchung;
import de.kasharing.app.jpa.Fahrzeug;
import de.kasharing.app.jpa.Nutzer;
import java.util.Date;
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

    public Response<Buchung> findById(Long id) {
        Response<Buchung> response = new Response<>();

        try {
            response.setResponse(em.find(Buchung.class, id));
            response.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (Exception ex) {
            response.setStatus(ResponseStatus.ERROR);
            response.setException(ex.getClass().getName());
            response.setMessage(ex.getMessage());
        } finally {
            return response;
        }
    }

    public Response<Buchung> createBuchung(Buchung b) {
        Response<Buchung> response = new Response<Buchung>();
        b.setActive(true);
        b.setTimestamp(new Date());
        try {
            Response<Buchung> checkList = this.findByFahrzeug(b.getFahrzeug());
            Boolean successful = true;
            for (Buchung checkBuchung : checkList.getResponseList()) {
                if (b.getGeliehenAb().getTime() >= checkBuchung.getGeliehenAb().getTime()
                        && b.getGeliehenAb().getTime() <= checkBuchung.getGeliehenBis().getTime()) {
                    successful = false;
                }
                if (b.getGeliehenBis().getTime() >= checkBuchung.getGeliehenAb().getTime()
                        && b.getGeliehenBis().getTime() <= checkBuchung.getGeliehenBis().getTime()) {
                    successful = false;
                }
            }
            if (successful) {
                em.persist(b);
                response.setResponse(em.merge(b));
                response.setStatus(ResponseStatus.ERFOLGREICH);
            } else {
                response.setStatus(ResponseStatus.WARNUNG);
                response.setMessage("Das Fahzeug ist in diesem Zeitraum leider schon gebucht.");
            }
        } catch (Exception ex) {
            response.setException(ex.getClass().getName());
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public Response updateBuchung(Buchung b) {
        Response<Buchung> response = new Response<>();

        try {
            response.setResponse(em.merge(b));
            response.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (Exception ex) {
            response.setException(ex.getClass().getName());
            response.setMessage(ex.getMessage());
            response.setStatus(ResponseStatus.ERROR);
        } finally {
            return response;
        }
    }

    public Response<Buchung> findAll() {
        Response<Buchung> response = new Response<Buchung>();
        try {
            Query query = em.createQuery("SELECT b FROM Buchung b");
            response.setResponseList(query
                    .getResultList());
            System.out.println(response.getResponseList());
            response.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (Exception ex) {
            response.setException(ex.getClass().getName());
            response.setStatus(ResponseStatus.ERROR);
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
            response.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (Exception ex) {
            response.setException(ex.getClass().getName());
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    public <E extends Nutzer> Response<Buchung> findByNutzer(E n) {
        Response<Buchung> response = new Response<Buchung>();
        try {
            response.setResponseList(em.createQuery("SELECT b FROM Buchung b WHERE b.nutzer = :NUTZER")
                    .setParameter("NUTZER", n)
                    .getResultList());
            response.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (Exception ex) {
            response.setException(ex.getClass().getName());
            response.setStatus(ResponseStatus.ERROR);
            response.setMessage(ex.getMessage());
            response.setStackTrace(ex.getStackTrace());
        }
        return response;
    }

    public Response<Buchung> storniereBuchung(Buchung b) {
        Response<Buchung> response = new Response<Buchung>();
        try {
            em.remove(b);
            response.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (Exception ex) {
            response.setStatus(ResponseStatus.ERROR);
            response.setException(ex.getClass().getName());
            response.setMessage(ex.getMessage());
        }
        return response;
    }
}

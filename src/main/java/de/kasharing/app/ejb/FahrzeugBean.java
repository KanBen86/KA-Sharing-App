/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.ejb;

import de.kasharing.app.enums.FahrzeugKlasse;
import de.kasharing.app.enums.FahrzeugTyp;
import de.kasharing.app.enums.ResponseStatus;
import de.kasharing.app.helper.Response;
import de.kasharing.app.jpa.Fahrzeug;
import java.util.Date;
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
public class FahrzeugBean {

    @PersistenceContext
    protected EntityManager em;

    public Response<Fahrzeug> createFahrzeug(Fahrzeug f) {
        Response<Fahrzeug> response = new Response<>();
        try {
            em.persist(f);
            response.setResponse(em.merge(f));
            response.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (Exception ex) {
            response.setStatus(ResponseStatus.ERROR);
            response.setException(ex.getClass().getName());
            response.setMessage(ex.getMessage());
        } finally {
            return response;
        }
    }

    public Response<Fahrzeug> updateFahrzeug(Fahrzeug f) {
        Response<Fahrzeug> response = new Response<>();
        try {
            f.setLastChange(new Date());
            response.setResponse(em.merge(f));
            response.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (Exception ex) {
            response.setStatus(ResponseStatus.ERROR);
            response.setException(ex.getClass().getName());
            response.setMessage(ex.getMessage());
        } finally {
            return response;
        }
    }

    public Response<Fahrzeug> findAll() {
        Response<Fahrzeug> response = new Response<>();
        try {
            System.out.println("Lese die Daten aus der Datenbank aus: ");
            Query query = em.createQuery("SELECT f FROM Fahrzeug f");
            List<Fahrzeug> fahrzeuge = query.getResultList();
            System.out.println("Die ausgelesenen Fahrzeuge sind: " + fahrzeuge);
            response.setResponseList(fahrzeuge);
            for (Fahrzeug f : response.getResponseList()) {
                System.out.println("Fahrzeug: " + f);
                if (f.isDeaktiviert()) {
                    response.getResponseList().remove(f);
                }
            }
            response.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (Exception ex) {
            response.setStatus(ResponseStatus.ERROR);
            response.setException(ex.getClass().getName());
            response.setMessage(ex.getMessage());
            response.setStackTrace(ex.getStackTrace());
        } finally {
            return response;
        }

    }

    public Response<Fahrzeug> findByModell(String modell) {

        modell = "%" + modell + "%";
        Response<Fahrzeug> response = new Response<>();
        try {
            response.setResponseList(em.createQuery("SELECT f FROM Fahrzeug f WHERE f.modell LIKE :MODELL")
                    .setParameter("MODELL", modell)
                    .getResultList());
            response.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (Exception ex) {
            response.setStatus(ResponseStatus.ERROR);
            response.setException(ex.getClass().getName());
            response.setMessage(ex.getMessage());
        } finally {
            return response;
        }
    }

    public Response<Fahrzeug> findByHersteller(String hersteller) {
        hersteller = "%" + hersteller + "%";

        Response<Fahrzeug> response = new Response<>();
        try {
            response.setResponseList(em.createQuery("SELECT f FROM Fahrzeug f WHERE f.hersteller LIKE :HERSTELLER")
                    .setParameter("HERSTELLER", hersteller)
                    .getResultList());
            response.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (Exception ex) {
            response.setStatus(ResponseStatus.ERROR);
            response.setException(ex.getClass().getName());
            response.setMessage(ex.getMessage());
        } finally {
            return response;
        }
    }

    public Response<Fahrzeug> findByTyp(FahrzeugTyp typ) {
        Response<Fahrzeug> response = new Response<>();
        try {
            response.setResponseList(em.createQuery("SELECT f FROM Fahrzeug f WHERE f.typ LIKE :TYP")
                    .setParameter("TYP", typ)
                    .getResultList());
            response.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (Exception ex) {
            response.setStatus(ResponseStatus.ERROR);
            response.setException(ex.getClass().getName());
            response.setMessage(ex.getMessage());
        } finally {
            return response;
        }
    }

    public Response<Fahrzeug> findByKlasse(FahrzeugKlasse klasse) {
        Response<Fahrzeug> response = new Response<>();
        try {
            response.setResponseList(em.createQuery("SELECT f FROM Fahrzeug f WHERE f.klasse LIKE :KLASSE")
                    .setParameter("KLASSE", klasse)
                    .getResultList());
            response.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (Exception ex) {
            response.setStatus(ResponseStatus.ERROR);
            response.setException(ex.getClass().getName());
            response.setMessage(ex.getMessage());
        } finally {
            return response;
        }
    }

    public Response<Fahrzeug> findById(long id) {
        Response<Fahrzeug> response = new Response<>();
        try {
            response.setResponse(em.find(Fahrzeug.class,
                    id));
            response.setStatus(ResponseStatus.ERFOLGREICH);
        } catch (Exception ex) {
            response.setStatus(ResponseStatus.ERROR);
            response.setException(ex.getClass().getName());
            response.setMessage(ex.getMessage());
        } finally {
            return response;
        }
    }

    public Response<Fahrzeug> deleteFahrzeug(long id) {
        Response<Fahrzeug> response = new Response<>();
        try {
            Response<Fahrzeug> f = findById(id);
            if (f != null) {
                em.remove(f);
            }
            response.setResponse(em.find(Fahrzeug.class,
                    id));
            response.setStatus(ResponseStatus.GELOESCHT);
            response.setMessage("Das Fahrzeug mit der ID " + f.getResponse().getId() + " wurde gelöscht.");
        } catch (Exception ex) {
            response.setStatus(ResponseStatus.ERROR);
            response.setException(ex.getClass().getName());
            response.setMessage(ex.getMessage());
        } finally {
            return response;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.ejb;

import de.kasharing.app.enums.ResponseStatus;
import de.kasharing.app.helper.Response;
import de.kasharing.app.jpa.Adresse;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Benjamin Kanzler
 */
@Stateless
public class AdresseBean {

    @PersistenceContext
    EntityManager em;

    public Response<Adresse> createNewAdresse(Adresse a) {
        Response<Adresse> response = new Response<>();
        try {
            em.persist(a);
            response.setResponse(em.merge(a));
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

    public Response<Adresse> updateAdresse(Adresse a) {
        Response<Adresse> response = new Response<>();
        try {
            response.setResponse(em.merge(a));
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

    public Response<Adresse> findAll() {
        Response<Adresse> response = new Response<>();
        try {
            response.setResponseList(em.createQuery("SELECT a FROM Adresse a").getResultList());
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
    public Response<Adresse> findById(Long id) {
        Response<Adresse> response = new Response<>();
        try {
            response.setResponse(em.find(Adresse.class, id));
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

    public Response<Adresse> deleteAdresse(Adresse a) {
        Response<Adresse> response = new Response<>();
        try {
            em.remove(a);
            response.setResponse(a);
            response.setStatus(ResponseStatus.ERFOLGREICH);
            response.setMessage("Die Adresse\n" + a + "\nwurde erfolgreich gelöscht.");
        } catch (Exception ex) {
            response.setStatus(ResponseStatus.ERROR);
            response.setException(ex.getClass().getName());
            response.setMessage(ex.getMessage());
            response.setStackTrace(ex.getStackTrace());
        } finally {
            return response;
        }
    }
}

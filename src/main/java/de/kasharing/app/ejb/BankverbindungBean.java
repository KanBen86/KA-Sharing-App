/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.ejb;

import de.kasharing.app.enums.ResponseStatus;
import de.kasharing.app.helper.Response;
import de.kasharing.app.jpa.Bankverbindung;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Benjamin Kanzler
 */
@Stateless
public class BankverbindungBean {

    @PersistenceContext
    EntityManager em;

    public Response<Bankverbindung> createNewBankverbindung(Bankverbindung b) {
        Response<Bankverbindung> response = new Response<>();
        try {
            em.persist(b);
            response.setResponse(em.merge(b));
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

    public Response<Bankverbindung> updateBankverbindung(Bankverbindung b) {
        Response<Bankverbindung> response = new Response<>();
        try {
            response.setResponse(em.merge(b));
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

    public Response<Bankverbindung> findAll() {
        Response<Bankverbindung> response = new Response<>();
        try {
            response.setResponseList(em.createQuery("SELECT b FROM Bankverbindung b").getResultList());
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

    public Response<Bankverbindung> findByBic(String bic) {
        Response<Bankverbindung> response = new Response<>();
        try {
            List<Bankverbindung> bankverbindungen = em.createQuery("SELECT b FROM Bankverbindung b WHERE b.bic LIKE :BIC")
                    .setParameter("BIC", bic)
                    .getResultList();
            if (bankverbindungen != null) {
                if (bankverbindungen.size() == 1) {
                    response.setResponse(bankverbindungen.get(0));
                } else {
                    response.setResponseList(bankverbindungen);
                }
                response.setStatus(ResponseStatus.ERFOLGREICH);
            } else {
                response.setStatus(ResponseStatus.NULL);
            }
        } catch (Exception ex) {
            response.setStatus(ResponseStatus.ERROR);
            response.setException(ex.getClass().getName());
            response.setMessage(ex.getMessage());
            response.setStackTrace(ex.getStackTrace());
        } finally {
            return response;
        }
    }

    public Response<Bankverbindung> findById(Long id) {
        Response<Bankverbindung> response = new Response<>();
        try {
            response.setResponse(em.find(Bankverbindung.class, id));
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

    public Response<Bankverbindung> deleteBankverbingung(Bankverbindung b) {
        Response<Bankverbindung> response = new Response<>();
        try {
            em.remove(b);
            response.setResponse(b);
            response.setStatus(ResponseStatus.ERFOLGREICH);
            response.setMessage("Die Bankverbindung\n" + b + "\nwurde erfolgreich gelöscht.");
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

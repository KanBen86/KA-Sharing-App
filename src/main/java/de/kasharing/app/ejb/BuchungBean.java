/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.ejb;

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

    public Buchung createBuchung(Buchung b) {
        b.setActive(true);
        em.persist(b);
        return em.merge(b);
    }

    public Buchung updateBuchung(Buchung b) {
        return em.merge(b);
    }

    public List<Buchung> findAll() {
        return em.createQuery("SELECT b FROM Buchung b")
                .getResultList();
    }

    public List<Buchung> findByFahrzeug(Fahrzeug fahrzeug) {
        Query query;
        query = em.createQuery("SELECT b FROM Buchung b WHERE b.fahrzeug = :FAHRZEUG")
                .setParameter("FAHRZEUG", fahrzeug);
        List<Buchung> buchungen = query
                .getResultList();
        return buchungen;
    }
    
    public List<Buchung> findByNutzerId(Long id) {

        return em.createQuery("SELECT b FROM Buchung b WHERE b.nutzer LIKE :NID")
                .setParameter("NID", id)
                .getResultList();
    }
    
    public void storniereBuchung(Buchung b){
        em.remove(b);
    }
}

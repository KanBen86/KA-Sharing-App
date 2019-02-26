/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.ejb;

import de.kasharing.app.enums.FahrzeugKlasse;
import de.kasharing.app.enums.FahrzeugTyp;
import de.kasharing.app.jpa.Fahrzeug;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Benjamin Kanzler
 */
@Stateless
public class FahrzeugBean {

    @PersistenceContext
    EntityManager em;

    public Fahrzeug updateFahrzeug(Fahrzeug f) {

        em.persist(f);

        return em.merge(f);
    }

    public List<Fahrzeug> findAll() {
        return em.createQuery("SELECT f FROM Fahrzeug f ORDER BY f.id DESC").getResultList();
    }

    public List<Fahrzeug> findByModell(String modell) {
        return em.createQuery("SELECT f FROM Fahrzeug f WHERE f.modell LIKE " + modell).getResultList();
    }

    public List<Fahrzeug> findByHersteller(String hersteller){
        return em.createQuery("SELECT f FROM Fahrzeug f WHERE f.hersteller LIKE " + hersteller).getResultList();
    }
    
    public List<Fahrzeug> findByTyp(FahrzeugTyp typ){
        return em.createQuery("SELECT f FROM Fahrzeug f WHERE f.typ LIKE " + typ).getResultList();
    }
    
    public List<Fahrzeug> findByKlasse(FahrzeugKlasse klasse){
        return em.createQuery("SELECT f FROM Fahrzeug f WHERE f.klasse LIKE " + klasse).getResultList();
    }
    
    public Fahrzeug
            findById(long id) {
        return em.find(Fahrzeug.class,
                id);
    }

}

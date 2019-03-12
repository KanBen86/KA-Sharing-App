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
    protected EntityManager em;

    public Fahrzeug createFahrzeug(Fahrzeug f) {
        em.persist(f);
        return em.merge(f);
    }

    public Fahrzeug updateFahrzeug(Fahrzeug f) {

        Fahrzeug databaseFahrzeug = findById(f.getId());
        if (databaseFahrzeug.getLastChange() == f.getLastChange()) {
            return em.merge(f);
        } else {
            databaseFahrzeug.setError("Leider wurde das Fahrzeug in der Datenbank in der Zwischenzeit geändert.");
            return databaseFahrzeug;
        }
    }

    public List<Fahrzeug> findAll() {
        return em.createQuery("SELECT f FROM Fahrzeug f")
                .getResultList();
    }

    public List<Fahrzeug> findByModell(String modell) {
        modell = "%" + modell + "%";

        return em.createQuery("SELECT f FROM Fahrzeug f WHERE f.modell LIKE :MODELL")
                .setParameter("MODELL", modell)
                .getResultList();
    }

    public List<Fahrzeug> findByHersteller(String hersteller) {
        hersteller = "%" + hersteller + "%";

        return em.createQuery("SELECT f FROM Fahrzeug f WHERE f.hersteller LIKE :HERSTELLER")
                .setParameter("HERSTELLER", hersteller)
                .getResultList();
    }

    public List<Fahrzeug> findByTyp(FahrzeugTyp typ) {
        return em.createQuery("SELECT f FROM Fahrzeug f WHERE f.typ LIKE :TYP")
                .setParameter("TYP", typ)
                .getResultList();
    }

    public List<Fahrzeug> findByKlasse(FahrzeugKlasse klasse) {
        return em.createQuery("SELECT f FROM Fahrzeug f WHERE f.klasse LIKE :KLASSE")
                .setParameter("KLASSE", klasse)
                .getResultList();
    }

    public Fahrzeug findById(long id) {
        return em.find(Fahrzeug.class,
                id);
    }

    public Fahrzeug deleteFahrzeug(long id) {
        Fahrzeug f = findById(id);
        if (f != null) {
            em.remove(f);
        }
        return f;
    }

}

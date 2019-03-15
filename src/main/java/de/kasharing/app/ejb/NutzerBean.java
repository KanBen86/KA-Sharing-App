/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.ejb;

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
public class NutzerBean {

    @PersistenceContext
    protected EntityManager em;

    public Nutzer createNutzer(Nutzer n) {

        em.persist(n);
        return em.merge(n);
    }

    public Nutzer updateNutzer(Nutzer n) {
        return em.merge(n);
    }

    public Nutzer findById(Long id) {
        return em.find(Nutzer.class, id);
    }

    public Nutzer findByNick(String nick) {
        Nutzer nutzer;
        try {
            nutzer = (Nutzer) em.createQuery("SELECT n FROM Nutzer n WHERE n.nickName LIKE :NICK")
                    .setParameter("NICK", nick)
                    .getSingleResult();
        } catch (NoResultException ex) {
            nutzer = null;
        }
        return nutzer;
    }

    public Nutzer findByEmail(String email) {
        Nutzer nutzer = (Nutzer) em.createQuery("SELECT n FROM Nutzer n WHERE n.email LIKE :EMAIL")
                .setParameter("EMAIL", email)
                .getSingleResult();
        return nutzer;
    }

}

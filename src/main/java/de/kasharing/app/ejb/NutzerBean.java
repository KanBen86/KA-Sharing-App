/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.ejb;

import de.kasharing.app.enums.ResponseStatus;
import de.kasharing.app.helper.Response;
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

    public Response<Nutzer> findByNick(String nick) {
        Response<Nutzer> nutzer = new Response<>();
        try {
            nutzer.setResponse((Nutzer)(em.createQuery("SELECT n FROM Nutzer n WHERE n.nickName LIKE :NICK")
                    .setParameter("NICK", nick)
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

    public Nutzer findByEmail(String email) {
        Nutzer nutzer = (Nutzer) em.createQuery("SELECT n FROM Nutzer n WHERE n.email LIKE :EMAIL")
                .setParameter("EMAIL", email)
                .getSingleResult();
        return nutzer;
    }

}

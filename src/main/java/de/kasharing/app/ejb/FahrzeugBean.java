/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.ejb;

import de.kasharing.app.jpa.Fahrzeug;
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
    
    public void createFahrzeug (Fahrzeug f){
        em.persist(f);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.jpa;

import de.kasharing.app.enums.NutzerRolle;
import javax.persistence.Entity;

/**
 *
 * @author Benjamin Kanzler
 */
@Entity
public class Mitarbeiter extends Nutzer {
    
    private NutzerRolle rolle;

    public NutzerRolle getRolle() {
        return rolle;
    }

    public void setRolle(NutzerRolle rolle) {
        this.rolle = rolle;
    }

    @Override
    public String toString() {
        return super.toString() + "Mitarbeiter{" + "rolle=" + rolle + '}';
    }
}

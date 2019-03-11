/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.enums;

/**
 *
 * @author Benjamin Kanzler
 */
public enum NutzerRolle {
    
    KUNDE("Kunde"), ADMIN("Administrator"), SB("Sachbearbeiter"), MANAGER("Manager");
    
    private String bezeichnung;
    
    private NutzerRolle(String bezeichnung){
        this.bezeichnung = bezeichnung;
    }
    
}

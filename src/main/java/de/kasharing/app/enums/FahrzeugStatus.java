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
public enum FahrzeugStatus {
    AUSGELIEHEN("Ausgeliehen"),
    WARTUNG("Wartung"), 
    REPERATUR("Reperatur"),
    VERFUEGBAR("Verfügbar");
    
    private String bezeichnung;
    
    private FahrzeugStatus(String bez){
        this.bezeichnung = bez;
    }
}

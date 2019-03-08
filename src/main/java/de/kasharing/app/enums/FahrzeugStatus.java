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
    AUSGELIEHEN("ausgeliehen"), 
    WARTUNG("Wartung"), 
    REPERATUR("Reperatur"), 
    TOTALSCHADEN("Totalschaden"), 
    VERFUEGBAR("Verfügbar"), 
    RESERVIERT("Reserviert");
    
    private String bezeichnung;
    
    private FahrzeugStatus(String bez){
        this.bezeichnung = bez;
    }
}

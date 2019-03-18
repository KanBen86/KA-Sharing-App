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
public enum FahrzeugTreibstoff {
    
    DIESEL("Diesel"),
    BENZIN("Benzin"),
    ELEKTRO("Elektro");
    
    private String bezeichnung;
    
    private FahrzeugTreibstoff (String bez){
        this.bezeichnung = bez;
    }
}

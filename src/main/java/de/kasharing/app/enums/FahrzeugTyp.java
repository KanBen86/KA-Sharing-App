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
public enum FahrzeugTyp {

    KOMBI("Kombi"), 
    LIMUSINE("Limusine"), 
    STUFENHECK("Stufenheck"), 
    CABRIO("Cabriolet"), 
    SUV("SUV");
    
    private String bezeichnung;
    
    private FahrzeugTyp(String bez){
        this.bezeichnung = bez;
    }
}

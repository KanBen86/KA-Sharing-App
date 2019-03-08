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
public enum FahrzeugKlasse {
    KLEINWAGEN("Kleinwagen"), 
    GOLFKLASSE("Golfklasse"), 
    MITTELKLASSE("Mittelklasse"), 
    OBERKLASSE("Oberklasse"), 
    LUXUSKLASSE("Luxusklasse");
    
    private String bezeichnung;
    
    private FahrzeugKlasse(String str){
        this.bezeichnung = str;
    }
}

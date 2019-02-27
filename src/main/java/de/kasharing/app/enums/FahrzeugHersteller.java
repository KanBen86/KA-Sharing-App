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
public enum FahrzeugHersteller {
    AUDI("Audi"), FORD("Ford"), MAZDA("Mazda"), NISSAN("Nissan"), MERCEDES("Mercedes"), OPEL("Opel"), TOYOTA("Toyota"), VOLVO("Volvo");

    private String bezeichnung;

    private FahrzeugHersteller(String bez) {
        this.bezeichnung = bez;
    }
}

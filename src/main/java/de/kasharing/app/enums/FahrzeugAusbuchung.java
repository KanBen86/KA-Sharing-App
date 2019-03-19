/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.enums;

/**
 * Dieses ENUM stellt die Auswahlmöglichkeiten beim Ausbuchen eines Fahzeuges
 * aus dem geschäftlichen Betrieb zur Verfügung.
 *
 * @author Benjamin Kanzler
 */
public enum FahrzeugAusbuchung {

    TOTALSCHADEN("Totalschaden"),
    UNRENTABEL("Unrentabel"),
    ABGESCHRIEBEN("Abgeschrieben");

    private String bezeichnung;

    private FahrzeugAusbuchung(String bez) {
        this.bezeichnung = bez;

    }
}

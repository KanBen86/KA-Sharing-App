/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.jpa;

import de.kasharing.app.enums.*;
import java.awt.Image;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Benjamin Kanzler
 */
@Entity
public class Fahrzeug implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private FahrzeugHersteller hersteller;

    private String modell;

    private FahrzeugTyp typ;

    private String ausfuehrung;

    private FahrzeugKlasse klasse;
    
    private FahrzeugTreibstoff treibstoff;

    private int plaetze;

    private int raeder;

    private int tueren;

    private boolean klimaanlage;

    private boolean elektrischeFensterheber;

    private boolean servolenkung;

    private boolean abs;

    private boolean esp;

    private boolean cd;

    private boolean navigation;

    private boolean fahrassiSystem;
    
    private boolean deaktiviert;

    private float preisProTag;

    private FahrzeugStatus leihStatus;

    @Temporal(TemporalType.DATE)
    private Date anschaffungsDatum;

    @Temporal(TemporalType.DATE)
    private Date hauptuntersuchungBis;

    @Column(insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChange;
    
    private float anschaffungsPreis;

    private FahrzeugGetriebeArt getriebeart;

    private Image bild;

    public Image getBild() {
        return bild;
    }

    public void setBild(Image bild) {
        this.bild = bild;
    }

    public FahrzeugStatus getLeihStatus() {
        return leihStatus;
    }

    public void setLeihStatus(FahrzeugStatus leihStatus) {
        this.leihStatus = leihStatus;
    }

    public FahrzeugGetriebeArt getGetriebeart() {
        return getriebeart;
    }

    public void setGetriebeart(FahrzeugGetriebeArt getriebeart) {
        this.getriebeart = getriebeart;
    }

    public FahrzeugKlasse getKlasse() {
        return klasse;
    }

    public void setKlasse(FahrzeugKlasse klasse) {
        this.klasse = klasse;
    }

    public void setHersteller(FahrzeugHersteller hersteller) {
        this.hersteller = hersteller;
    }

    public void setTyp(FahrzeugTyp typ) {
        this.typ = typ;
    }

    public void setAusfuehrung(String ausfuehrung) {
        this.ausfuehrung = ausfuehrung;
    }

    public void setRaeder(int raeder) {
        this.raeder = raeder;
    }

    public void setPreisProTag(float preisProTag) {
        this.preisProTag = preisProTag;
    }

    public void setAnschaffungsDatum(Date anschaffungsDatum) {
        this.anschaffungsDatum = anschaffungsDatum;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public void setKlimaanlage(boolean klimaanlage) {
        this.klimaanlage = klimaanlage;
    }

    public void setElektrischeFensterheber(boolean elektrischeFensterheber) {
        this.elektrischeFensterheber = elektrischeFensterheber;
    }

    public void setServolenkung(boolean servolenkung) {
        this.servolenkung = servolenkung;
    }

    public void setAbs(boolean abs) {
        this.abs = abs;
    }

    public void setEsp(boolean esp) {
        this.esp = esp;
    }

    public void setCd(boolean cd) {
        this.cd = cd;
    }

    public void setNavigation(boolean navigation) {
        this.navigation = navigation;
    }

    public void setFahrassiSystem(boolean fahrassiSystem) {
        this.fahrassiSystem = fahrassiSystem;
    }

    public void setAnschaffungsPreis(float anschaffungsPreis) {
        this.anschaffungsPreis = anschaffungsPreis;
    }

    public void setHauptuntersuchungBis(Date hauptuntersuchungBis) {
        this.hauptuntersuchungBis = hauptuntersuchungBis;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public FahrzeugHersteller getHersteller() {
        return hersteller;
    }

    public FahrzeugTyp getTyp() {
        return typ;
    }

    public String getAusfuehrung() {
        return ausfuehrung;
    }

    public int getRaeder() {
        return raeder;
    }

    public float getPreisProTag() {
        return preisProTag;
    }

    public Date getAnschaffungsDatum() {
        return anschaffungsDatum;
    }

    public String getModell() {
        return modell;
    }

    public boolean isKlimaanlage() {
        return klimaanlage;
    }

    public boolean isElektrischeFensterheber() {
        return elektrischeFensterheber;
    }

    public boolean isServolenkung() {
        return servolenkung;
    }

    public boolean isAbs() {
        return abs;
    }

    public boolean isEsp() {
        return esp;
    }

    public boolean isCd() {
        return cd;
    }

    public boolean isNavigation() {
        return navigation;
    }

    public boolean isFahrassiSystem() {
        return fahrassiSystem;
    }

    public float getAnschaffungsPreis() {
        return anschaffungsPreis;
    }

    public Date getHauptuntersuchungBis() {
        return hauptuntersuchungBis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPlaetze() {
        return plaetze;
    }

    public void setPlaetze(int plaetze) {
        this.plaetze = plaetze;
    }

    public int getTueren() {
        return tueren;
    }

    public void setTueren(int tueren) {
        this.tueren = tueren;
    }

    public Date getLastChange() {
        return lastChange;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public FahrzeugTreibstoff getTreibstoff() {
        return treibstoff;
    }

    public void setTreibstoff(FahrzeugTreibstoff treibstoff) {
        this.treibstoff = treibstoff;
    }

    public boolean isDeaktiviert() {
        return deaktiviert;
    }

    public void setDeaktiviert(boolean deaktiviert) {
        this.deaktiviert = deaktiviert;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.hersteller);
        hash = 43 * hash + Objects.hashCode(this.modell);
        hash = 43 * hash + Objects.hashCode(this.typ);
        hash = 43 * hash + Objects.hashCode(this.ausfuehrung);
        hash = 43 * hash + Objects.hashCode(this.klasse);
        hash = 43 * hash + Objects.hashCode(this.treibstoff);
        hash = 43 * hash + this.plaetze;
        hash = 43 * hash + this.raeder;
        hash = 43 * hash + this.tueren;
        hash = 43 * hash + (this.klimaanlage ? 1 : 0);
        hash = 43 * hash + (this.elektrischeFensterheber ? 1 : 0);
        hash = 43 * hash + (this.servolenkung ? 1 : 0);
        hash = 43 * hash + (this.abs ? 1 : 0);
        hash = 43 * hash + (this.esp ? 1 : 0);
        hash = 43 * hash + (this.cd ? 1 : 0);
        hash = 43 * hash + (this.navigation ? 1 : 0);
        hash = 43 * hash + (this.fahrassiSystem ? 1 : 0);
        hash = 43 * hash + (this.deaktiviert ? 1 : 0);
        hash = 43 * hash + Float.floatToIntBits(this.preisProTag);
        hash = 43 * hash + Objects.hashCode(this.leihStatus);
        hash = 43 * hash + Objects.hashCode(this.anschaffungsDatum);
        hash = 43 * hash + Objects.hashCode(this.hauptuntersuchungBis);
        hash = 43 * hash + Objects.hashCode(this.lastChange);
        hash = 43 * hash + Float.floatToIntBits(this.anschaffungsPreis);
        hash = 43 * hash + Objects.hashCode(this.getriebeart);
        hash = 43 * hash + Objects.hashCode(this.bild);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fahrzeug other = (Fahrzeug) obj;
        if (this.plaetze != other.plaetze) {
            return false;
        }
        if (this.raeder != other.raeder) {
            return false;
        }
        if (this.tueren != other.tueren) {
            return false;
        }
        if (this.klimaanlage != other.klimaanlage) {
            return false;
        }
        if (this.elektrischeFensterheber != other.elektrischeFensterheber) {
            return false;
        }
        if (this.servolenkung != other.servolenkung) {
            return false;
        }
        if (this.abs != other.abs) {
            return false;
        }
        if (this.esp != other.esp) {
            return false;
        }
        if (this.cd != other.cd) {
            return false;
        }
        if (this.navigation != other.navigation) {
            return false;
        }
        if (this.fahrassiSystem != other.fahrassiSystem) {
            return false;
        }
        if (this.deaktiviert != other.deaktiviert) {
            return false;
        }
        if (Float.floatToIntBits(this.preisProTag) != Float.floatToIntBits(other.preisProTag)) {
            return false;
        }
        if (Float.floatToIntBits(this.anschaffungsPreis) != Float.floatToIntBits(other.anschaffungsPreis)) {
            return false;
        }
        if (!Objects.equals(this.modell, other.modell)) {
            return false;
        }
        if (!Objects.equals(this.ausfuehrung, other.ausfuehrung)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.hersteller != other.hersteller) {
            return false;
        }
        if (this.typ != other.typ) {
            return false;
        }
        if (this.klasse != other.klasse) {
            return false;
        }
        if (this.treibstoff != other.treibstoff) {
            return false;
        }
        if (this.leihStatus != other.leihStatus) {
            return false;
        }
        if (!Objects.equals(this.anschaffungsDatum, other.anschaffungsDatum)) {
            return false;
        }
        if (!Objects.equals(this.hauptuntersuchungBis, other.hauptuntersuchungBis)) {
            return false;
        }
        if (!Objects.equals(this.lastChange, other.lastChange)) {
            return false;
        }
        if (this.getriebeart != other.getriebeart) {
            return false;
        }
        if (!Objects.equals(this.bild, other.bild)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Fahrzeug{" + "id=" + id + ", hersteller=" + hersteller + ", modell=" + modell + ", typ=" + typ + ", ausfuehrung=" + ausfuehrung + ", klasse=" + klasse + ", treibstoff=" + treibstoff + ", plaetze=" + plaetze + ", raeder=" + raeder + ", tueren=" + tueren + ", klimaanlage=" + klimaanlage + ", elektrischeFensterheber=" + elektrischeFensterheber + ", servolenkung=" + servolenkung + ", abs=" + abs + ", esp=" + esp + ", cd=" + cd + ", navigation=" + navigation + ", fahrassiSystem=" + fahrassiSystem + ", deaktiviert=" + deaktiviert + ", preisProTag=" + preisProTag + ", leihStatus=" + leihStatus + ", anschaffungsDatum=" + anschaffungsDatum + ", hauptuntersuchungBis=" + hauptuntersuchungBis + ", lastChange=" + lastChange + ", anschaffungsPreis=" + anschaffungsPreis + ", getriebeart=" + getriebeart + ", bild=" + bild + '}';
    }
    
}

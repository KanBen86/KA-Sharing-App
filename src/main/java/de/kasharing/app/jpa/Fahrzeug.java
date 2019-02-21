/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Benjamin Kanzler
 */
@Entity
public class Fahrzeug implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    
    private String hersteller;
    
    private String typ;
    
    private String ausfuehrung;
    
    private int raeder;
    
    private String zubehoer;
    
    private String preisProTag;
    
    private String leihStatus;
    
    private Date anschaffungsDatum;

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public void setAusfuehrung(String ausfuehrung) {
        this.ausfuehrung = ausfuehrung;
    }

    public void setRaeder(int raeder) {
        this.raeder = raeder;
    }

    public void setZubehoer(String zubehoer) {
        this.zubehoer = zubehoer;
    }

    public void setPreisProTag(String preisProTag) {
        this.preisProTag = preisProTag;
    }

    public void setLeihStatus(String leihStatus) {
        this.leihStatus = leihStatus;
    }

    public void setAnschaffungsDatum(Date anschaffungsDatum) {
        this.anschaffungsDatum = anschaffungsDatum;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getHersteller() {
        return hersteller;
    }

    public String getTyp() {
        return typ;
    }

    public String getAusfuehrung() {
        return ausfuehrung;
    }

    public int getRaeder() {
        return raeder;
    }

    public String getZubehoer() {
        return zubehoer;
    }

    public String getPreisProTag() {
        return preisProTag;
    }

    public String getLeihStatus() {
        return leihStatus;
    }

    public Date getAnschaffungsDatum() {
        return anschaffungsDatum;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.hersteller);
        hash = 19 * hash + Objects.hashCode(this.typ);
        hash = 19 * hash + Objects.hashCode(this.ausfuehrung);
        hash = 19 * hash + this.raeder;
        hash = 19 * hash + Objects.hashCode(this.zubehoer);
        hash = 19 * hash + Objects.hashCode(this.preisProTag);
        hash = 19 * hash + Objects.hashCode(this.leihStatus);
        hash = 19 * hash + Objects.hashCode(this.anschaffungsDatum);
        return hash;
    }

    @Override
    public String toString() {
        return "Fahrzeug{" + "id=" + id + ", hersteller=" + hersteller + ", typ=" + typ + ", ausfuehrung=" + ausfuehrung + ", raeder=" + raeder + ", zubehoer=" + zubehoer + ", preisProTag=" + preisProTag + ", leihStatus=" + leihStatus + ", anschaffungsDatum=" + anschaffungsDatum + '}';
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
        if (this.raeder != other.raeder) {
            return false;
        }
        if (!Objects.equals(this.hersteller, other.hersteller)) {
            return false;
        }
        if (!Objects.equals(this.typ, other.typ)) {
            return false;
        }
        if (!Objects.equals(this.ausfuehrung, other.ausfuehrung)) {
            return false;
        }
        if (!Objects.equals(this.zubehoer, other.zubehoer)) {
            return false;
        }
        if (!Objects.equals(this.preisProTag, other.preisProTag)) {
            return false;
        }
        if (!Objects.equals(this.leihStatus, other.leihStatus)) {
            return false;
        }
        if (!Objects.equals(this.anschaffungsDatum, other.anschaffungsDatum)) {
            return false;
        }
        return true;
    }
    
    
}

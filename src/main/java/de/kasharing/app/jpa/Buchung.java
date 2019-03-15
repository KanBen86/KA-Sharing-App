/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Benjamin Kanzler
 */
@Entity
public class Buchung implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fahrzeug_id", referencedColumnName = "id")
    private Fahrzeug fahrzeug;

    @OneToOne
    @JoinColumn(name = "nutzer_id", referencedColumnName = "id")
    private Nutzer nutzer;

    @Column(insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    private Date geliehenAb;

    private Date geliehenBis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fahrzeug getFahrzeug() {
        return fahrzeug;
    }

    public void setFahrzeug(Fahrzeug fahrzeug) {
        this.fahrzeug = fahrzeug;
    }

    public Nutzer getNutzer() {
        return nutzer;
    }

    public void setNutzer(Nutzer nutzer) {
        this.nutzer = nutzer;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getGeliehenAb() {
        return geliehenAb;
    }

    public void setGeliehenAb(Date geliehenAb) {
        this.geliehenAb = geliehenAb;
    }

    public Date getGeliehenBis() {
        return geliehenBis;
    }

    public void setGeliehenBis(Date geliehenBis) {
        this.geliehenBis = geliehenBis;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.fahrzeug);
        hash = 47 * hash + Objects.hashCode(this.nutzer);
        hash = 47 * hash + Objects.hashCode(this.timestamp);
        hash = 47 * hash + Objects.hashCode(this.geliehenAb);
        hash = 47 * hash + Objects.hashCode(this.geliehenBis);
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
        final Buchung other = (Buchung) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.fahrzeug, other.fahrzeug)) {
            return false;
        }
        if (!Objects.equals(this.nutzer, other.nutzer)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        if (!Objects.equals(this.geliehenAb, other.geliehenAb)) {
            return false;
        }
        if (!Objects.equals(this.geliehenBis, other.geliehenBis)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Buchung{" + "id=" + id + ", fahrzeug=" + fahrzeug + ", nutzer=" + nutzer + ", timestamp=" + timestamp + ", geliehenAb=" + geliehenAb + ", geliehenBis=" + geliehenBis + '}';
    }

    private boolean checkValues() {

        if (this.nutzer != null) {
            if (this.fahrzeug != null) {
                if (this.geliehenAb != null && this.geliehenAb.after(new Date())) {
                    if (this.geliehenBis.after(new Date())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}

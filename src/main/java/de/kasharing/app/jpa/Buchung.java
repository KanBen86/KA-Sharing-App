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

    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date geliehenAb;

    @Temporal(TemporalType.TIMESTAMP)
    private Date geliehenBis;

    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

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

    public boolean checkValues() {
        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        if (this.nutzer != null) {
            System.out.println("Nutzer gesetzt");
            if (this.fahrzeug != null) {
                System.out.println("Fahrzeug gesetzt");
                if (this.geliehenAb.getDay() >= date.getDay() && this.geliehenAb.getMonth() >= date.getMonth() && this.geliehenAb.getYear() >= date.getYear() ) {
                    System.out.println("geliehenAb Datum ist später als heute");
                    if (this.geliehenBis.getTime() >= date.getTime()
                            && this.geliehenBis.getTime() >= this.geliehenAb.getTime()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

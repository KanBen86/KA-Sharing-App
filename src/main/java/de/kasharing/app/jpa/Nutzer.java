/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kasharing.app.jpa;

import de.kasharing.app.enums.NutzerRolle;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Benjamin Kanzler
 */
@Entity
public class Nutzer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nickName;

    private String email;
    
    private String passwort;

    private NutzerRolle rolle;
    
    @OneToOne()
    @JoinColumn(name ="bank_id", referencedColumnName = "id")
    private Bankverbindung bank;

    @OneToOne()
    @JoinColumn(name ="adresse_id", referencedColumnName = "id")
    private Adresse adresse;

    public Bankverbindung getBank() {
        return bank;
    }

    public void setBank(Bankverbindung bank) {
        this.bank = bank;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
    
    public NutzerRolle getRolle() {
        return rolle;
    }

    public void setRolle(NutzerRolle rolle) {
        this.rolle = rolle;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
    
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.nickName);
        hash = 41 * hash + Objects.hashCode(this.email);
        hash = 41 * hash + Objects.hashCode(this.passwort);
        hash = 41 * hash + Objects.hashCode(this.rolle);
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
        final Nutzer other = (Nutzer) obj;
        if (!Objects.equals(this.nickName, other.nickName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.passwort, other.passwort)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.rolle != other.rolle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Nutzer{" + "id=" + id + ", nickName=" + nickName + ", email=" + email + ", passwort=" + passwort + ", rolle=" + rolle + ", bank=" + bank + ", adresse=" + adresse + '}';
    }
    
}

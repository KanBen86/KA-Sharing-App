package de.kasharing.app.jpa;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import de.kasharing.app.jpa.Fahrzeug;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Benjamin Kanzler
 */
public class FahrzeugTest {
    
    private Fahrzeug f1, f2;
    
    public FahrzeugTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void createFahrzeuge(){
        f1 = new Fahrzeug();
        
        assertNotNull(f1);
        
        f2 = new Fahrzeug();
        
        assertNotNull(f2);
        
        f1.setAbs(true);
        f1.setAusfuehrung("Elegance");
        f1.setAnschaffungsPreis(11111.11f);
        f1.setAnschaffungsDatum(new Date());
        f1.setCd(true);
        f1.setElektrischeFensterheber(true);
        f1.setEsp(true);
        f1.setFahrassiSystem(true);
        f1.setHauptuntersuchungBis(new Date());
        f1.setHersteller("Nissan");
        f1.setKlimaanlage(true);
        f1.setLeihStatus("verliehen");
        f1.setModell("Tuscan");
        f1.setNavigation(true);
        f1.setPreisProTag(59.99f);
        f1.setRaeder(4);
        f1.setServolenkung(true);
        f1.setTyp("Kombi");
        
        f2.setAbs(true);
        f2.setAusfuehrung("Elegance");
        f2.setAnschaffungsPreis(11111.11f);
        f2.setAnschaffungsDatum(new Date());
        f2.setCd(true);
        f2.setElektrischeFensterheber(true);
        f2.setEsp(true);
        f2.setFahrassiSystem(true);
        f2.setHauptuntersuchungBis(new Date());
        f2.setHersteller("Nissan");
        f2.setKlimaanlage(true);
        f2.setLeihStatus("verliehen");
        f2.setModell("Tuscan");
        f2.setNavigation(true);
        f2.setPreisProTag(59.99f);
        f2.setRaeder(4);
        f2.setServolenkung(true);
        f2.setTyp("Limusine");
        
        assertTrue(f1.isAbs());
        assertTrue(f1.isCd());
        assertTrue(f1.isElektrischeFensterheber());
        assertTrue(f1.isEsp());
        assertTrue(f1.isFahrassiSystem());
        assertTrue(f1.isKlimaanlage());
        assertTrue(f1.isNavigation());
        assertTrue(f1.isServolenkung());
        
        assertTrue(f1.getAusfuehrung().equals("Elegance"));
        assertTrue(f1.getLeihStatus().equals("verliehen"));
        assertTrue(f1.getModell().equals("Tuscan"));
        assertTrue(f1.getTyp().equals("Kombi"));
        
        assertNotNull(f1.getHauptuntersuchungBis());
        assertNotNull(f1.getAnschaffungsDatum());
        
        assertTrue(f1.getAnschaffungsPreis()==11111.11f);
        assertTrue(f1.getPreisProTag()==59.99f);
        assertTrue(f1.getRaeder()==4);
        
        assertTrue(f1.equals(f1));
        assertFalse(f1.equals(f2));
    }
}
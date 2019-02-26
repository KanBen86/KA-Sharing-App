package de.kasharing.app.ejb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import de.kasharing.app.jpa.Fahrzeug;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class FahrzeugBeanTest {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private FahrzeugBean bean;

    @Resource
    private Fahrzeug f;

    public FahrzeugBeanTest() {
    }

    /*
    @BeforeClass
    public static void start() throws NamingException {
        container = EJBContainer.createEJBContainer();
    }

    @AfterClass
    public static void tearDownClass() {
        if (container != null) {
            container.close();
        }
    }
    @Before
    public void inject() throws NamingException {
        container.getContext().bind("inject", this);
    }

    @After
    public void reset() throws NamingException {
        container.getContext().unbind("inject");
    }
     */

    @Before
    public void setUp() {

        bean = new FahrzeugBean();

        f = new Fahrzeug();

        f.setAbs(true);
        f.setAusfuehrung("Elegance");
        f.setAnschaffungsPreis(11111.11f);
        f.setAnschaffungsDatum(new Date());
        f.setCd(true);
        f.setElektrischeFensterheber(true);
        f.setEsp(true);
        f.setFahrassiSystem(true);
        f.setHauptuntersuchungBis(new Date());
        f.setHersteller("Nissan");
        f.setKlimaanlage(true);
        f.setLeihStatus("verliehen");
        f.setModell("Tuscan");
        f.setNavigation(true);
        f.setPreisProTag(59.99f);
        f.setRaeder(4);
        f.setServolenkung(true);
        f.setTyp("Kombi");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createFahrzeugTest() {

        Fahrzeug updatedF = bean.updateFahrzeug(f);
        assertTrue(updatedF.equals(f));
    }
    /*
    @Test
    public void findAllTest(){
        System.out.println(bean.findAll());
    }
     */
}

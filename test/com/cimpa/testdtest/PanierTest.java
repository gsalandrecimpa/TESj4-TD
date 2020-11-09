package com.cimpa.testdtest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cimpa.testd.Orange;
import com.cimpa.testd.OrangeException;
import com.cimpa.testd.Panier;
import com.cimpa.testd.PanierException;

public class PanierTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConstruct() throws PanierException {
		Panier p;
		try {
			p = new Panier(-1);
			fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof PanierException);
			assertEquals("La contenance doit être d'au moins 1",e.getMessage());
		}
		try {
			p = new Panier(0);
			fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof PanierException);
			assertEquals("La contenance doit être d'au moins 1",e.getMessage());
		}
		p = new Panier(1);
		p = new Panier(7);
	}
	
	@Test
	public void testSetContenance() throws PanierException, OrangeException {
		Panier p = new Panier(1);
		assertEquals(1,p.getContenance());
		
		p.setContenance(25);
		assertEquals(25,p.getContenance());
		
		try { 
			p.setContenance(0);
			fail("Exception expected");
		}
		catch (Exception e) {
			assertTrue(e instanceof PanierException);
			assertEquals("La contenance doit être d'au moins 1",e.getMessage());
		}
		assertEquals(25,p.getContenance());
		
		p.ajoute(new Orange(1,""));
		p.ajoute(new Orange(1,""));
		p.setContenance(2);
		assertEquals(2,p.getContenance());
		try { 
			p.setContenance(1);
			fail("Exception expected");
		}
		catch (Exception e) {
			assertTrue(e instanceof PanierException);
			assertEquals("Une nouvelle contenance ne peut être inférieure à la taille courante (=2) du panier.",e.getMessage());
		}
		assertEquals(2,p.getContenance());
		
	}
	
	@Test
	public void testEstVideEstPleinAjoute() throws PanierException, OrangeException {
		Panier p = new Panier(1);
		assertTrue(p.estVide());
		assertFalse(p.estPlein());
		
		try {
			p.ajoute(null);
			fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof PanierException);
			assertEquals("ajout impossible d'un element null",e.getMessage());
		}
		assertTrue(p.estVide());
		assertFalse(p.estPlein());

		
		p.ajoute(new Orange(1,""));
		assertFalse(p.estVide());
		assertTrue(p.estPlein());
		
		p = new Panier(2);
		assertTrue(p.estVide());
		assertFalse(p.estPlein());
		p.ajoute(new Orange(1,""));
		assertFalse(p.estVide());
		assertFalse(p.estPlein());
		p.ajoute(new Orange(1,""));
		assertFalse(p.estVide());
		assertTrue(p.estPlein());
		
		try {
			p.ajoute(new Orange(1,""));
			fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof PanierException);
			assertEquals("ajout impossible car le panier est plein",e.getMessage());
		}
		
	}
	
	@Test
	public void testGetPrix() throws PanierException, OrangeException {
		double pr1=5.658;
		double pr2=3.67;
		double pr3=7.9876;
		
		Panier p = new Panier(3);
		p.ajoute(new Orange(pr1,""));
		assertEquals(pr1,p.getPrix(),0);
		p.ajoute(new Orange(pr2,""));
		assertEquals(pr1+pr2,p.getPrix(),0);
		p.ajoute(new Orange(pr3,""));
		assertEquals(pr1+pr2+pr3,p.getPrix(),0);
		p.retire();
		assertEquals(pr1+pr2,p.getPrix(),0);
		
		
	}
	
	@Test
	public void testAjouteRetireEqualsToString() throws PanierException, OrangeException {
		Panier p = new Panier(2);
		Panier p2 = new Panier(2);
		assertFalse(p.equals(new Integer(34)));
		assertEquals("Panier-prix total=0.00:",p.toString());
		assertEquals(p,p2);
		p.retire();
		assertEquals("Panier-prix total=0.00:",p.toString());
		assertEquals(p,p2);
		
		
		p.ajoute(new Orange(2,"toto"));
		assertEquals("Panier-prix total=2.00:prix=2.00,origine=toto;",p.toString());
		assertNotEquals(p,p2);
		p.retire();
		assertEquals("Panier-prix total=0.00:",p.toString());
		assertEquals(p,p2);
		p.ajoute(new Orange(2,"toto"));
		assertEquals("Panier-prix total=2.00:prix=2.00,origine=toto;",p.toString());
		p2.ajoute(new Orange(2,"toto"));
		assertEquals(p,p2);
		
		p.ajoute(new Orange(3.072,"tutu"));
		assertEquals("Panier-prix total=5.07:prix=2.00,origine=toto;prix=3.07,origine=tutu;",p.toString());
		assertNotEquals(p,p2);
		p.retire();
		assertEquals("Panier-prix total=2.00:prix=2.00,origine=toto;",p.toString());
		assertEquals(p,p2);
		
		p.setContenance(7);
		assertNotEquals(p,p2);
		
	}
	
	@Test
	public void testBoycotteOrigine() throws PanierException, OrangeException {
		Panier p = new Panier(5);
		p.ajoute(new Orange(0.80, "France"));
		p.ajoute(new Orange(0.80, "Espagne"));
		p.ajoute(new Orange(0.90, "Floride"));
		
		Panier p2 = new Panier(5);
		p2.ajoute(new Orange(0.80, "Espagne"));
		p2.ajoute(new Orange(0.90, "Floride"));
		
		assertNotEquals(p,p2);
		p.boycotteOrigine("France");
		assertEquals(p,p2);
		
		p = new Panier(5);
		p.ajoute(new Orange(0.80, "France"));
		p.ajoute(new Orange(0.80, "Espagne"));
		p.ajoute(new Orange(0.80, "France"));
		p.ajoute(new Orange(0.90, "Floride"));
		p.ajoute(new Orange(0.80, "France"));
		
		assertNotEquals(p,p2);
		p.boycotteOrigine("France");
		assertEquals(p,p2);
		p2.boycotteOrigine("France");
		assertEquals(p,p2);
	}

}

package com.cimpa.testdtest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cimpa.testd.Orange;
import com.cimpa.testd.OrangeException;

public class OrangeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConstruct() throws OrangeException {
		Orange o = new Orange(0,"");
		assertEquals(0,o.getPrix(),0);
		assertEquals("",o.getOrigine());
		o = new Orange(1.2,"toto");
		assertEquals(1.2,o.getPrix(),0);
		assertEquals("toto",o.getOrigine());
		try {
			o = new Orange(-1.2,"toto");
			fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof OrangeException);
			assertEquals("Le prix ne peut être négatif",e.getMessage());
		}
		try {
			o = new Orange(1.2,null);
			fail("Exception expected");
		} catch (Exception e) {
			assertTrue(e instanceof OrangeException);
			assertEquals("L'origine ne peut être définie comme null",e.getMessage());
		}
	}
	
	@Test
	public void testToString() throws OrangeException {
		Orange o = new Orange(0,"");
		assertEquals("prix=0.00,origine=", o.toString());
		o = new Orange(0.4,"tutu");
		assertEquals("prix=0.40,origine=tutu", o.toString());
		o = new Orange(0.44,"tutu");
		assertEquals("prix=0.44,origine=tutu", o.toString());
		o = new Orange(0.444,"tutu");
		assertEquals("prix=0.44,origine=tutu", o.toString());
		o = new Orange(0.445,"tutu");
		assertEquals("prix=0.45,origine=tutu", o.toString());
	}
	@Test
	public void testEquals() throws OrangeException {
		Orange o1 = new Orange(0.456,"toto");
		Orange o2 = new Orange(0.456,"toto");
		Orange o3 = new Orange(0.456,"tutu");
		Orange o4 = new Orange(0.457,"toto");
		
		assertFalse(o1.equals(null));

		assertEquals(o1, o1);
		assertTrue(o1.equals(o1));

		assertEquals(o1, o2);
		assertTrue(o1.equals(o2));

		assertNotEquals(o1, o3);
		assertFalse(o1.equals(o3));

		assertNotEquals(o1, o4);
		assertFalse(o1.equals(o4));
		
		o2.setPrix(0.457);
		assertNotEquals(o1, o2);
		assertEquals(o2, o4);
		
	}

}

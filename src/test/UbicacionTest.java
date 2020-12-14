package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import negocio.*;


class UbicacionTest {

	Ubicacion ubicacion1;
	Ubicacion ubicacion2;

    @Before
    public void setUp() {
    	ubicacion1 = new Ubicacion(10,20);
    	ubicacion2 = new Ubicacion(20,40);
    }
    
    @Test
    public void equalsTest() {
    	setUp();
    	assertFalse(ubicacion1.equals(ubicacion2));
    }
    
    @Test
    public void equalsTest2() {
    	setUp();
    	assertTrue(ubicacion1.equals(new Ubicacion(10,20)));
    }
    
    @Test
    public void equalsInvertidoTest() {
    	setUp();
    	assertFalse(ubicacion1.equals(new Ubicacion(20,10)));
    }
}

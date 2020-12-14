package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import negocio.*;

class ClienteTest {
	Cliente cliente1;
	Cliente cliente2;
	
	Ubicacion ubicacion1;
	Ubicacion ubicacion2;

    @Before
    public void setUp() {
    	ubicacion1 = new Ubicacion(10,20);
    	ubicacion2 = new Ubicacion(20,40);
    	cliente1 = new Cliente ("Cliente 1",ubicacion1);
    	cliente2 = new Cliente ("Cliente 2",ubicacion2);
    }
    
    @Test
    public void equalsFalseTest() {
    	setUp();
    	assertFalse(cliente1.equals(cliente2));
    }
    
    @Test
    public void equalsNombreTest() {
    	setUp();
    	assertTrue(cliente1.equals(new Cliente ("Cliente 1",ubicacion2)));
    }
    
    @Test
    public void equalsUbicacionTest() {
    	setUp();
    	assertTrue(cliente1.equals(new Cliente ("Cliente 3",ubicacion1)));
    }
    
    @Test
    public void NombreNullTest() {
    	Ubicacion ubicacion = new Ubicacion(1,1);
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		Cliente clienteNombreNull = new Cliente(null,ubicacion);
    	});
    }
    
    @Test
    public void NombreVacioTest() {
    	Ubicacion ubicacion = new Ubicacion(1,1);
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		Cliente clienteNombreVacio = new Cliente("",ubicacion);
    	});
    }
}

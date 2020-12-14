package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import negocio.*;

class CentroTest {
	
	Centro centro;
	Cliente cliente;
	Ubicacion ubicacionCentro;
	Ubicacion ubicacionCliente;

    @Before
    public void setUp() {
    	ubicacionCentro = new Ubicacion(10,20);
    	ubicacionCliente = new Ubicacion(20,40);
		centro = new Centro("Centro1",ubicacionCentro);
    	cliente = new Cliente("Cliente1",ubicacionCliente);
    }
    
    @Test
    public void equalsUbicacionTest() {
    	setUp();
    	assertTrue(centro.equals(new Centro("Centro2",ubicacionCentro)));
    }
    
    @Test
    public void equalsNombreTest() {
    	setUp();
    	assertTrue(centro.equals(new Centro("Centro1",ubicacionCliente)));
    }
    
    @Test
    public void equalsFalseTest() {
    	setUp();
    	assertFalse(centro.equals(new Centro("CDD2",ubicacionCliente)));
    }
    
    @Test
    public void NombreNullTest() {
    	Ubicacion ubicacion = new Ubicacion(1,1);
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		Centro centroNombreNull = new Centro(null,ubicacion);
    	});
    }
    
    @Test
    public void NombreVacioTest() {
    	Ubicacion ubicacion = new Ubicacion(1,1);
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		Centro centroNombreVacio = new Centro("",ubicacion);
    	});
    }
}

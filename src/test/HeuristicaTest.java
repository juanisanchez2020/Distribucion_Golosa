package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import negocio.*;

class HeuristicaTest {
	Heuristica heuristica;
	Centro centro1;
	Centro centro2;
	Centro centro3;
	Cliente cliente1;
	Cliente cliente2;
	Cliente cliente3;
	Cliente cliente4;
	ArrayList<Centro> centros;
	ArrayList<Cliente> clientes;

    @Before
    public void setUp() {
    	centro1 = new Centro("Centro1",new Ubicacion(10,20));
    	centro2 = new Centro("Centro2",new Ubicacion(15,20));
    	centro3 = new Centro("Centro3",new Ubicacion(20,40));
    	
    	cliente1 = new Cliente("Cliente1",new Ubicacion(5,40));
    	cliente2 = new Cliente("Cliente2",new Ubicacion(2,40));
    	cliente3 = new Cliente("Cliente3",new Ubicacion(20,20));
    	cliente4 = new Cliente("Cliente4",new Ubicacion(40,50));
    	
    	centros = new ArrayList<Centro>();
    	clientes = new ArrayList<Cliente>();
    	heuristica = new Heuristica();
    	
    	centros.add(centro1);
    	centros.add(centro2);
    	centros.add(centro3);
    	
    	clientes.add(cliente1);
    	clientes.add(cliente2);
    	clientes.add(cliente3);
    	clientes.add(cliente4);
    }
    
    @Test
    public void happyPathTest() { //revisar, ver cual conviene más
    	setUp();
    	DatosOptimizados datosOptimizados = heuristica.optimizarCentros(2, centros, clientes);
    	assertEquals(datosOptimizados.cantidadCentrosActivos(),2);
    	//assertEquals(datosOptimizados.getCentrosActivos().size(),2);
    }
    
    @Test
    public void centroInactivoTest(){
    	setUp();
    	DatosOptimizados datosOptimizados = heuristica.optimizarCentros(2, centros, clientes);
    	assertEquals(datosOptimizados.getCentrosOptimizados().get(0).getActivo(), false);
    }
    
    @Test
    public void utilidadTest(){
    	setUp();
    	DatosOptimizados datosOptimizados = heuristica.optimizarCentros(2, centros, clientes);
    	assertEquals(datosOptimizados.getCentrosActivos().get(0).getUtilidad(), 5);
    }
    
    @Test
    public void CentroActivoTest(){
    	setUp();
    	DatosOptimizados datosOptimizados = heuristica.optimizarCentros(2, centros, clientes);
    	assertEquals(datosOptimizados.getCentrosOptimizados().get(1).getActivo(), true);
    }
    
    @Test
    public void cantidadClientesAsociadoAlCentroTest(){
    	setUp();
    	DatosOptimizados datosOptimizados = heuristica.optimizarCentros(2, centros, clientes);
    	assertEquals(datosOptimizados.getCentrosActivos().get(1).getClientesAsociados().size(), 3);
    }
    
    @Test
    public void cantidadClientesActivosTest(){
    	setUp();
    	DatosOptimizados datosOptimizados = heuristica.optimizarCentros(2, centros, clientes);
    	assertEquals(datosOptimizados.getClientesOptimizados().size(), 4);
    }
    
    @Test
    public void distanciaAlCentroTest(){
    	setUp();
    	DatosOptimizados datosOptimizados = heuristica.optimizarCentros(2, centros, clientes);
    	assertEquals(datosOptimizados.getClientesOptimizados().get(0).getDistanciaAlCentro(), 15);
    }
    
    @Test
    public void clientesNullTest() {
    	setUp();
    	ArrayList<Cliente> clientesNull = null;
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		DatosOptimizados datosOptimizados = heuristica.optimizarCentros(2, centros, clientesNull);
    	});
    }
    
    @Test
    public void centrosNullTest() {
    	setUp();
    	ArrayList<Centro> centrosNull = null;
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		DatosOptimizados datosOptimizados = heuristica.optimizarCentros(2, centrosNull, clientes);
    	});
    }
    
    @Test
    public void sinClientesTest() {
    	setUp();
    	ArrayList<Cliente> clientesVacio = new ArrayList<Cliente>();
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		DatosOptimizados datosOptimizados = heuristica.optimizarCentros(2, centros, clientesVacio);
    	});
    }
    
    @Test
    public void sinCentrosTest() {
    	setUp();
    	ArrayList<Centro> centrosVacio = new ArrayList<Centro>();
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    		DatosOptimizados datosOptimizados = heuristica.optimizarCentros(2, centrosVacio, clientes);
    	});
    }
}

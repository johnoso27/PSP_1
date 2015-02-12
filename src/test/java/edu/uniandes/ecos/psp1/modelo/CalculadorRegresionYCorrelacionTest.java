package edu.uniandes.ecos.psp1.modelo;

import edu.uniandes.ecos.psp1.controlador.App;
import java.util.LinkedList;
import junit.framework.TestCase;

/**
 * Pruebas unitarias para la clase CalculadorRegresionYCorrelacion
 *
 * @author JohnDany
 */
public class CalculadorRegresionYCorrelacionTest extends TestCase {

    public CalculadorRegresionYCorrelacionTest(String testName) {
        super(testName);
    }

    /**
     * Test of calcularRegrecionCorrelacion method, of class
     * CalculadorRegresionYCorrelacion.
     * @throws java.lang.Exception
     */
    public void testCalcularRegrecionCorrelacion() throws Exception {
        Fuente fuente = new Fuente();
        CalculadorRegresionYCorrelacion calculador = new CalculadorRegresionYCorrelacion();
        ResultadoRegrecionCorrelacion respuesta = null;
        String rutaArvhivo = App.class.getClassLoader().getResource("test1.txt").getFile();
        LinkedList<PuntoDosDimensiones> puntos = fuente.obtenerPuntosDosDimensionesDeArchivo(rutaArvhivo);
        respuesta = calculador.calcularRegrecionCorrelacion(puntos, 386.0);
        assertNotNull(null, respuesta);
        assertEquals(-22.55, respuesta.getB0(), 0.5);
        assertEquals(1.7279, respuesta.getB1(), 0.5);
        assertEquals(0.9545, respuesta.getRxy(), 0.5);
        assertEquals(0.9111, respuesta.getrCuadrado(), 0.5);
        assertEquals(644.429, respuesta.getYk(), 0.5);
        System.out.println();
        System.out.println("Regresion lineal y correlacion de los datos de " + rutaArvhivo);
        System.out.println(respuesta);
    }

}

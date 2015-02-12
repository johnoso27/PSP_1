package edu.uniandes.ecos.psp1.modelo;

import java.util.LinkedList;

/**
 * Clase que calcula la regresión lineal y la correlación de un conjunto de
 * datos
 *
 * @author JohnDany
 */
public class CalculadorRegresionYCorrelacion {

    /**
     * Lista de los datos en el eje x
     */
    private LinkedList<Double> x;

    /**
     * Lista de los datos en el eje y
     */
    private LinkedList<Double> y;
    
    /**
     * Sumatoria de los datos del eje x
     */
    private Double sumatoriaX;
    
    /**
     * Sumatoria de los datos del eje y
     */
    private Double sumatoriaY;
    
    /**
     * Sumatoria de los datos del eje x al cuadrado.
     */
    private Double sumatoriaXCuadrado;
    
    /**
     * Sumatoria del eje y al cuadrado.
     */
    private Double sumatoriaYCuadrado;
    
    /**
     * Sumatoria del los datos del eje x por los del eje y
     */
    private Double sumatoriaXporY;

    /**
     * Promedio de los datos del eje x
     */
    private Double promedioX;
    
    /**
     * Promedio de los datos del eje y
     */
    private Double promedioY;
    
    private Double b0;
    private Double b1;
    private Double rxy;
    private Double rCuadrado;
    private Double yk;
    private int n;

    /**
     * Método que calcula la regresión lineal y la correlación de datos.
     * @param datos a calcular.
     * @param proxySize tamaño de proxy
     * @return resultado con los datos calculados.
     */
    public ResultadoRegrecionCorrelacion calcularRegrecionCorrelacion(LinkedList<PuntoDosDimensiones> datos, Double proxySize) throws Exception {
        ResultadoRegrecionCorrelacion resultado = null;
        if (datos == null || datos.size() <= 0) {
            throw new Exception("No se puede calcular la regreción y correlación porque la lista de datos no tiene datos.");
        }

        if (proxySize <= 0) {
            throw new Exception("El tamaño del proxy no puede ser cero.");
        }

        this.sumatoriaX = 
        this.sumatoriaY =
        this.sumatoriaXCuadrado =
        this.sumatoriaYCuadrado =
        this.sumatoriaXporY = 
        this.promedioX =
        this.promedioY =
        this.b1 =
        this.b0 =
        this.rxy =
        this.rCuadrado =
        this.yk = 0.0;
        this.n = datos.size();
        this.x = new LinkedList<Double>();
        this.y = new LinkedList<Double>();
        
        for (PuntoDosDimensiones dato : datos) {
            this.x.add(dato.x);
            this.y.add(dato.y);
            this.sumatoriaX = this.sumatoriaX + dato.x;
            this.sumatoriaY = this.sumatoriaY + dato.y;
            this.sumatoriaXCuadrado = this.sumatoriaXCuadrado + Math.pow(dato.x, 2);
            this.sumatoriaYCuadrado = this.sumatoriaYCuadrado + Math.pow(dato.y, 2);
            this.sumatoriaXporY = this.sumatoriaXporY + (dato.x * dato.y);
        }
        
        this.promedioX = this.sumatoriaX / this.n;
        this.promedioY = this.sumatoriaY / this.n;
        this.b1 = (this.sumatoriaXporY - (this.n * this.promedioX * this.promedioY))
                / 
                (this.sumatoriaXCuadrado - (this.n * Math.pow(this.promedioX,2)));
        
        this.rxy = 
                ((this.n * this.sumatoriaXporY) - (this.sumatoriaX * this.sumatoriaY))
                /
                (Math.sqrt(
                            (this.n * this.sumatoriaXCuadrado - (Math.pow(this.sumatoriaX, 2)))*
                            (this.n * this.sumatoriaYCuadrado - (Math.pow(this.sumatoriaY, 2)))
                           )
                );
        
        this.rCuadrado = Math.pow(this.rxy, 2);
        
        this.b0 = this.promedioY - (this.b1 * this.promedioX);
        this.yk = this.b0 + (this.b1 * proxySize);
        
        resultado  = new ResultadoRegrecionCorrelacion();
        resultado.setB0(this.b0);
        resultado.setB1(this.b1);
        resultado.setRxy(this.rxy);
        resultado.setXk(proxySize);
        resultado.setYk(this.yk);
        resultado.setrCuadrado(this.rCuadrado);
        return resultado;
    }
}

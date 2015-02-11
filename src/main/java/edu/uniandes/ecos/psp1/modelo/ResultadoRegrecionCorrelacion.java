/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.psp1.modelo;

/**
 * Clase que representa el resultado de calcualar la regresión y la correlación de un conjunto de datos.
 * @author JohnDany
 */
public class ResultadoRegrecionCorrelacion {
    
    /**
     * 
     */
    private Double b0;
    
    /**
     * 
     */
    private Double b1;    
    
    /**
     * 
     */
    private Double rxy;
    
    /**
     * 
     */
    private Double rCuadrado;
    
    /**
     * 
     */
    private Double xk;
    
    /**
     * 
     */
    private Double yk;

    /**
     * @return the b0
     */
    public Double getB0() {
        return b0;
    }

    /**
     * @param b0 the b0 to set
     */
    public void setB0(Double b0) {
        this.b0 = b0;
    }

    /**
     * @return the b1
     */
    public Double getB1() {
        return b1;
    }

    /**
     * @param b1 the b1 to set
     */
    public void setB1(Double b1) {
        this.b1 = b1;
    }

    /**
     * @return the rxy
     */
    public Double getRxy() {
        return rxy;
    }

    /**
     * @param rxy the rxy to set
     */
    public void setRxy(Double rxy) {
        this.rxy = rxy;
    }

    /**
     * @return the rCuadrado
     */
    public Double getrCuadrado() {
        return rCuadrado;
    }

    /**
     * @param rCuadrado the rCuadrado to set
     */
    public void setrCuadrado(Double rCuadrado) {
        this.rCuadrado = rCuadrado;
    }

    /**
     * @return the xk
     */
    public Double getXk() {
        return xk;
    } 
    
      /**
     * @param xk the xk to set
     */
    public void setXk(Double xk) {
        this.xk = xk;
    }
    
    /**
     * @return the yk
     */
    public Double getYk() {
        return yk;
    }    
    
    /**
     * @param yk the yk to set
     */
    public void setYk(Double yk) {
        this.yk = yk;
    }

    @Override
    public String toString() {
        return String.format("B0:\t%.3f \nB1:\t%.3f \nrxy:\t%.3f \nr2:\t%.3f \nyk:\t%.3f \nxk:\t%.3f",
                this.b0, 
                this.b1, 
                this.rxy,
                this.rCuadrado,
                this.yk,
                this.xk);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares.Model;

/**
 *
 * @author mdleiton
 */
public class Item{
    /**
	 * 
	 */
	protected float precio;

	/**
	 * 
	 */
	protected String nombre;
        
        /**
	 * 
	 */
	protected String descripcion;

        /**
	 * 
	 */
	protected boolean promocion;
        
        /**
	 * 
	 */
	protected float porcentaje;

    public Item(float precio, String nombre, String descripcion, boolean promocion, float porcentaje) {
        this.precio = precio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.promocion = promocion;
        this.porcentaje = porcentaje;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isPromocion() {
        return promocion;
    }

    public void setPromocion(boolean promocion) {
        this.promocion = promocion;
    }
        
        
}
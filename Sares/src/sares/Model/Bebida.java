package sares.Model;

import java.util.*;

/**
 * 
 */
public class Bebida extends Item{

    public Bebida(String marca, float contenido, float precio, String nombre, String descripcion, boolean promocion, float porcentaje) {
        super(precio, nombre, descripcion, promocion, porcentaje);
        this.marca = marca;
        this.contenido = contenido;
    }

	/**
	 * 
	 */
	private String marca;
        /**
	 * 
	 */
	private float contenido;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getContenido() {
        return contenido;
    }

    public void setContenido(float contenido) {
        this.contenido = contenido;
    }

    
}
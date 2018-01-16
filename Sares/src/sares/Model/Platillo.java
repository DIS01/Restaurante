package sares.Model;

import java.sql.Time;
import java.util.*;

/**
 * 
 */
public class Platillo extends Item{

    public Platillo(Time tiempoEstimado, float precio, String nombre, String descripcion, boolean promocion, float porcentaje) {
        super(precio, nombre, descripcion, promocion, porcentaje);
        this.tiempoEstimado = tiempoEstimado;
    }

	/**
	 * 
	 */
	private Time tiempoEstimado;

    public Time getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(Time tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }


}
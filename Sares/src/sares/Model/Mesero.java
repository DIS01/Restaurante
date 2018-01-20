package sares.Model;

import java.util.*;

/**
 * 
 */
public class Mesero extends Empleado {
    private String nombre;
	/**
	 * Default constructor
	 */
	public Mesero(String nombre) {
            this.nombre = nombre;
	}

	/**
	 * 
	 */
	//private String  tipoMedioTransporte;

	private boolean medioPropio;





	/**
	 * @param list 
	 * @return
	 */
	public void calcularRuta(List<String> list) {
		// TODO implement here
	}

	/**
	 * 
	 */
	public void Operation1() {
		// TODO implement here
	}

	/**
	 * @return
	 */
	public Void update() {
		// TODO implement here
		return null;
	}

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
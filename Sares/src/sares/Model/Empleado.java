package sares.Model;

import java.util.*;

/**
 * 
 */
public class Empleado extends Persona {

	/**
	 * Default constructor
	 */
	public Empleado() {
	
        }
        public Empleado(String nombre) {
            super(nombre);
        }
	/**
	 * 
	 */
	protected int sueldo;

	/**
	 * 
	 */
	protected List<String> horario;



}
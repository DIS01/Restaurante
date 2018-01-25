package sares.Model;

import java.util.*;

/**
 * 
 */
public class Empleado extends Persona {
    
    /**
    * 
    */
    protected float sueldo;

    /**
    * 
    */
    protected List<String> horario;
    
    /**
     * Default constructor
     */
    public Empleado() {

    }
    
    public Empleado(String nombre) {
        super(nombre);
    }

    public Empleado(float sueldo, List<String> horario, String dni, String nombres, String apellidos, String domicilio) {
        super(dni, nombres, apellidos, domicilio);
        this.sueldo = sueldo;
        this.horario = horario;
    }

    
	


}
package sares.Model;

import java.util.*;

/**
 * 
 */
public class Empleado extends Persona {
    
    int id;
    /*
     * Default constructor
     */
    public Empleado() {

    }
    
    public Empleado(String nombre) {
        super(nombre);
    }

    public Empleado(String dni, String nombres, String apellidos, String domicilio) {
        super(dni, nombres, apellidos, domicilio);
    }

    public Empleado(int id, String dni, String nombres, String apellidos, String domicilio) {
        super(dni, nombres, apellidos, domicilio);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    
	


}
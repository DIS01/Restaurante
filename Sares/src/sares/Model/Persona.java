package sares.Model;

/**
 * 
 */
public class Persona {
    
    /**
     * 
     */
    protected String dni;

    /**
     * 
     */
    protected String psswrd;

    /**
     * 
     */
    private String nombres;

    /**
     * 
     */
    private String apellidos;
    
    /**
     * 
     */
    protected String domicilio;
    
    /**
     * Default constructor
     */
    public Persona() {
    }

    public Persona(String nombre) {
        this.nombres=nombre;
    }

    public Persona(String dni, String nombres, String apellidos, String domicilio) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

}

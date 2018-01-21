package sares.Model;

/**
 * 
 */
public class Persona {

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

	/**
	 * Default constructor
	 */
	public Persona() {
	}
        
        public Persona(String nombre) {
            this.nombres=nombre;
        }

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

	protected String domicilio;


	}

package sares.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 */
public class Cajero extends Empleado {

    /**
     * Default constructor
     */
    public Cajero() {
    }

    public Cajero(String dni,String nombres, String apellidos, String domicilio ) {
        super(dni, nombres, apellidos, domicilio);
    }

    /**
     * @param Cuenta 
     * @return
     */
    public void cobrar(Cuenta cuenta) {
            // TODO implement here
    }

    /**
     * @return
     */
    public void generarFactura() {
            // TODO implement here
    }
        
    public static Cajero getInformacionCajero(int dni) throws SQLException{
        ResultSet cajero = Conexion.consultar("Select * From Persona,Empleado where Persona.dni="+dni + " and Persona.dni=Empleado.persona ");
        cajero.next();
        return new Cajero(cajero.getString("dni"),cajero.getString("nombres"),cajero.getString("apellidos"),cajero.getString("domicilio"));

    }
}
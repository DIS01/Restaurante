package sares.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 
 */
public class Mesero extends Empleado {

    public Mesero(String dni, String nombres, String apellidos, String domicilio) {
        super(dni, nombres, apellidos, domicilio);
    }

    public Mesero(int id, String dni, String nombres, String apellidos, String domicilio) {
        super(id, dni, nombres, apellidos, domicilio);
    }
    
    /**
     * @param list
     */
    public void calcularRuta(List<String> list) {
            // TODO implement here
    }

    /**
     * @return
     */
    public Void update() {
            // TODO implement here
            return null;
    }

    public static Mesero getMesero(int dni) throws SQLException{
        ResultSet mesero = Conexion.consultar("Select * From Persona,Empleado where Persona.dni="+dni + " and Persona.dni=Empleado.persona ");
        mesero.next();
        return new Mesero(mesero.getInt("id"),mesero.getString("dni"),mesero.getString("nombres"),mesero.getString("apellidos"),mesero.getString("domicilio"));
    }
    
}
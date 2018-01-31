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
    
    /**
     * @param list 
     * @return
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

    public static Mesero getMesero(int dni,Conexion co) throws SQLException{
        ResultSet mesero = co.consultar("Select * From Persona,Empleado where Persona.dni="+dni + " and Persona.dni=Empleado.persona ");
        mesero.next();
        return new Mesero(mesero.getString("dni"),mesero.getString("nombres"),mesero.getString("apellidos"),mesero.getString("domicilio"));
    }
}
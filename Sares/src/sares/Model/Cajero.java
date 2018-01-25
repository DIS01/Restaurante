package sares.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import sares.zDiagramaModel.Cuenta;

/**
 * 
 */
public class Cajero extends Empleado {

    /**
     * Default constructor
     */
    public Cajero() {
    }

    public Cajero(String dni,String nombres, String apellidos, String domicilio,float sueldo, List<String> horario ) {
        super(sueldo, horario, dni, nombres, apellidos, domicilio);
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
        
    public static Cajero getInformacionCajero(int dni,Conexion co) throws SQLException{
        ResultSet cajero = co.consultar("Select * From Persona,Empleado,Cajero where Persona.dni="+dni + " and Persona.dni=Empleado.persona and Empleado.id=Cajero.empleado");
        cajero.next();
        LinkedList<String> horario=new LinkedList(Arrays.asList(cajero.getString("horario").split(","))); 
        return new Cajero(cajero.getString("dni"),cajero.getString("nombres"),cajero.getString("apellidos"),cajero.getString("domicilio"),cajero.getFloat("sueldo"),horario);

    }
}
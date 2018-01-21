package sares.Model;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import sares.Model.Empleado;
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
        
        public Cajero(String nombre){
            super(nombre);
            
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
            return new Cajero(cajero.getString("nombres"));
            
        }
}
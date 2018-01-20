package sares.Model;

import java.io.IOException;
import java.sql.CallableStatement;
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
        
        
         public boolean crearCliente()throws IOException, SQLException{
        Conexion co=new Conexion();
        CallableStatement statement = co.getConexion().prepareCall("{call crearCliente(?,?,?,?,?,?)}");
        statement.setInt(1,933567);
        statement.setString(2,"mauricio");
        statement.setString(3,"leiton");
        
        statement.setString(4,"data");
        statement.setDate(5,new java.sql.Date(Calendar.getInstance().getTimeInMillis()) );
        statement.setFloat(6,24.4F);
        return statement.execute();
    }

}
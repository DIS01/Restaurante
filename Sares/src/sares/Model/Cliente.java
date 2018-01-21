package sares.Model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import sares.Model.Persona;

/**
 * 
 */
public class Cliente extends Persona {

	/**
	 * Default constructor
	 */
	public Cliente() {
	}

    
    public Cliente( float descuentos, String nombre) {
        super(nombre);
        this.descuentos = descuentos;
    }

	/**
	 * 
	 */
	public java.sql.Date fechaIncorporacion;

	/**
	 * 
	 */
	public float descuentos;

        
        public static void ingresarCliente(String dni,String nombres,String apellidos,String domicilio,String descuento) throws SQLException{
            Conexion co=new Conexion();
            CallableStatement statement = co.getConexion().prepareCall("{call crearCliente(?,?,?,?,?,?)}");
            statement.setInt(1,Integer.parseInt(dni)); 
            statement.setString(2,nombres); 
            statement.setString(3,apellidos);
            statement.setString(4,domicilio);
            statement.setDate(5,new java.sql.Date(Calendar.getInstance().getTimeInMillis()) );
            statement.setFloat(6,Float.parseFloat(descuento)); 
            System.out.println(statement.execute());
        }

    public static LinkedList<Cliente> getClientes() throws SQLException, ParseException{
        LinkedList<Cliente> lista= new LinkedList();
        Conexion c=new Conexion();
        Cliente cliente;
        ResultSet clientesRS = c.consultar("SELECT * FROM Persona,Cliente where Persona.dni=Cliente.persona"); 
        while (clientesRS.next()){
            cliente= new Cliente(clientesRS.getFloat("descuentos"),clientesRS.getString("nombres"));
           lista.add(cliente);
        }
        clientesRS.close();
        return lista;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + this.getNombres() + ", apellidos=" + this.getApellidos() + '}';
    }
        
}
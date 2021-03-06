package sares.Model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

/**
 * 
 */
public class Cliente extends Persona {
    
    /**
     * 
     */
    public java.sql.Date fechaIncorporacion;
   
    /**
     * Default constructor
     */
    public Cliente() {
    }

    public Cliente(String dni, String nombres, String apellidos, String domicilio,Date fechaIncorporacion ) {
        super(dni, nombres, apellidos, domicilio);
        this.fechaIncorporacion = fechaIncorporacion;
    }

    public static void ingresarCliente(String dni,String nombres,String apellidos,String domicilio) throws SQLException{
        //Conexion co=new Conexion();
        CallableStatement statement = Conexion.getConexion().prepareCall("{call crearCliente(?,?,?,?,?)}");
        statement.setInt(1,Integer.parseInt(dni)); 
        statement.setString(2,nombres); 
        statement.setString(3,apellidos);
        statement.setString(4,domicilio);
        statement.setDate(5,new java.sql.Date(Calendar.getInstance().getTimeInMillis()) );
        System.out.println(statement.execute());
    }

    public static LinkedList<Cliente> getClientes() throws SQLException, ParseException{
        LinkedList<Cliente> lista= new LinkedList();
        Cliente cliente;
        ResultSet clientesRS = Conexion.consultar("SELECT * FROM Persona,Cliente where Persona.dni=Cliente.persona"); 
        while (clientesRS.next()){
            cliente= new Cliente(clientesRS.getString("dni"),clientesRS.getString("nombres"),clientesRS.getString("apellidos"),clientesRS.getString("domicilio"),clientesRS.getDate("fechaIncorporacion"));
           lista.add(cliente);
        }
        clientesRS.close();
        return lista;
    }

    @Override
    public String toString() {
        return this.dni +", " + this.getNombres() + ", " + this.getApellidos();
    }
        
}
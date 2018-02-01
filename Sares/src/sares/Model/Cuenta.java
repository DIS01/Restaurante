package sares.Model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

/**
 * 
 */
public class Cuenta {

    /**
     * Default constructor
     */
    public Cuenta() {
    }
    
    /**
     * 
     */
    private int id;
    /**
     * 
     */
    private boolean pagada;

    /**
     * 
     */
    private Float total;
    /**
     * 
     */
    private Mesa mesa;
    /**
    * 
    */
    private Cliente cliente;

    public Cuenta(int id,boolean pagada, Float total, Mesa mesa) {
        this.id=id;
        this.pagada = pagada;
        this.total = total;
        this.mesa = mesa;
    }

    /**
     */
    public void calcularTotal() {
            // TODO implement here
    }

    /**
     * @return
     */
    public Float getTotal() {
            // TODO implement here
            return null;
    }
    public static LinkedList<Cuenta> getCuentas() throws SQLException, ParseException{
        LinkedList<Cuenta> lista= new LinkedList();
        Cuenta cuenta;
        try (ResultSet cuentaRS = Conexion.consultar("SELECT * FROM Cuenta")) {
            while (cuentaRS.next()){
                //cuenta= new Cuenta(cuentaRS.getInt("id"),cuentaRS.getBoolean("pagada"),cuentaRS.getFloat("total"),Mesa.getMesa(cuentaRS.getInt("mesa")));
                cuenta= new Cuenta(cuentaRS.getInt("id"),cuentaRS.getBoolean("pagada"),cuentaRS.getFloat("total"),null); 
                lista.add(cuenta);
            }
        }
        return lista;
    }
    
    @Override
    public String toString() {
        return this.id +", " + this.pagada;
    }   
    
    public static int insertarCuenta(int mesa, int meseroID ) throws SQLException{
        CallableStatement statement = Conexion.getConexion().prepareCall("{call insertarCuentaMesero(?,?)}");
        statement.setInt(1,mesa); 
        statement.setInt(2,meseroID); 
        statement.execute();
        ResultSet r = statement.getResultSet();
        r.next();
        return r.getInt(1);
    }

    public static void cuentaCliente(int clienteID ,int  cuentaID ,float totalPago ) throws SQLException{
        CallableStatement statement = Conexion.getConexion().prepareCall("{call cuentaCliente(?,?,?)}");
        statement.setInt(1,clienteID); 
        statement.setInt(2,cuentaID);
        statement.setFloat(3,totalPago);
        statement.execute();
        ResultSet r = statement.getResultSet();
    }
}

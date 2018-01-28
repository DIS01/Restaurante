package sares.Model;

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
        Conexion c=new Conexion();
        Cuenta cuenta;
        try (ResultSet cuentaRS = c.consultar("SELECT * FROM Cuenta")) {
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
        return this.id +", " + this.mesa;
    }   

}
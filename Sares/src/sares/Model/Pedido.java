package sares.Model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Time;
import java.text.ParseException;

/**
 * 
 */
public class Pedido {

    /**
     * 
     */
    public int id;
    
    /**
     * 
     */
    public Time horaingreso;

    /**
     * 
     */
    public Time tiempoestimado;

    /**
     * 
     */
    public String estado;

    /**
     * 
     */
    public Cuenta cuenta;
    
    /**
     * 
     */
    public Date fecha;
    
     /**
     * Default constructor
     */
    public Pedido() {
    }

    public Pedido(int id, Time horaingreso, Time tiempoestimado, String estado, Cuenta cuenta, Date fecha) {
        this.id = id;
        this.horaingreso = horaingreso;
        this.tiempoestimado = tiempoestimado;
        this.estado = estado;
        this.cuenta = cuenta;
        this.fecha = fecha;
    }

    /**
     * @param platillo
     */
    public void getValor(Platillo platillo) {
            // TODO implement here
    }

    /**
     * @return
     */
    public void modificarPedido() {
        // TODO implement here

    }

    /**
     * @return
     */
    public void agregarAPrioridad() {
        // TODO implement here

    }
    
    public static LinkedList<Pedido> getPedidos() throws SQLException, ParseException{
        LinkedList<Pedido> lista= new LinkedList();
        Pedido pedido;
        ResultSet pedidoRS = Conexion.consultar("SELECT * FROM Pedido"); 
        while (pedidoRS.next()){
            pedido= new Pedido(pedidoRS.getInt("id"),pedidoRS.getTime("horaingreso"),pedidoRS.getTime("tiempoestimado"),pedidoRS.getString("estado"),null,pedidoRS.getDate("fecha"));
           lista.add(pedido);
        }
        pedidoRS.close();
        return lista;
    }
    
    public static LinkedList<Pedido> getPedidos(int idCuenta) throws SQLException, ParseException{
        LinkedList<Pedido> lista= new LinkedList();
        Pedido pedido;
        ResultSet pedidoRS = Conexion.consultar("SELECT * FROM Pedido where cuenta="+idCuenta); 
        while (pedidoRS.next()){
            pedido= new Pedido(pedidoRS.getInt("id"),pedidoRS.getTime("horaingreso"),pedidoRS.getTime("tiempoestimado"),pedidoRS.getString("estado"),null,pedidoRS.getDate("fecha"));
           lista.add(pedido);
        }
        pedidoRS.close();
        return lista;
    }
    
    @Override
    public String toString() {
        return this.id +", " + this.estado;
    }   
    
    public static float getTotal(LinkedList<Pedido> pedidos) throws SQLException{
        float total=0.0f;
        for (Pedido pedido:pedidos){
            total+=PedidoDetalle.getTotal(pedido);
        }
        return total;
    }
    
    public static int insertarPedido( String estado , int cuenta ) throws SQLException{
        CallableStatement statement = Conexion.getConexion().prepareCall("{call insertarPedidoMesero(?,?)}");
        statement.setString(1,estado); 
        statement.setInt(2,cuenta); 
        statement.execute();
        ResultSet r = statement.getResultSet();
        r.next();
        return r.getInt(1);
    }
    
    public static void actualizarPedidoCuenta(int cuentaID ,int pedidoID,Time tiempoEstimado1,float total1) throws SQLException{
        CallableStatement statement = Conexion.getConexion().prepareCall("{call actualizarPedidoCuenta(?,?,?,?)}");
        statement.setInt(1,cuentaID); 
        statement.setInt(2,pedidoID);
        statement.setTime(3,tiempoEstimado1);
        statement.setFloat(4,total1);
        statement.execute();
    }
}
package sares.Model;

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
     * Default constructor
     */
    public Pedido() {
    }

    public Pedido(int id,Time horaingreso, Time tiempoestimado, String estado, Mesa mesa) {
        this.horaingreso = horaingreso;
        this.tiempoestimado = tiempoestimado;
        this.estado = estado;
        this.mesa = mesa;
        this.id=id;
    }
    
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
    public Mesa mesa;
    
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
        Conexion c=new Conexion();
        Pedido pedido;
        ResultSet pedidoRS = c.consultar("SELECT * FROM Pedido"); 
        while (pedidoRS.next()){
            pedido= new Pedido(pedidoRS.getInt("id"),pedidoRS.getTime("horaingreso"),pedidoRS.getTime("tiempoestimado"),pedidoRS.getString("estado"),null);
           lista.add(pedido);
        }
        pedidoRS.close();
        return lista;
    }

    @Override
    public String toString() {
        return this.id +", " + this.estado;
    }   
}
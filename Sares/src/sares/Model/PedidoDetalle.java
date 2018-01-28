/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author mdleiton
 */
public class PedidoDetalle {
    /**
     * 
     */
    private int id;
    
    /**
     * 
     */
    private Pedido pedido;
    
    /**
     * 
     */
    private Item item;
    
    /**
     * 
     */
    private float precio;
    
    /**
     * 
     */
    private int cantidad;
    
    /**
     * 
     */
    private String detalle;

    public PedidoDetalle(int id, Pedido pedido, Item item, float precio, int cantidad, String detalle) {
        this.id = id;
        this.pedido = pedido;
        this.item = item;
        this.precio = precio;
        this.cantidad = cantidad;
        this.detalle = detalle;
    }

    public static float getTotal(Pedido pedido) throws SQLException{
        Conexion c=new Conexion();
        float subtotal=0.0f;
        ResultSet pedidoRS = c.consultar("SELECT * FROM PedidoDetalle where pedido="+pedido.id); 
        while (pedidoRS.next()){
            subtotal+=pedidoRS.getFloat("precio");
        }
        pedidoRS.close();
        return subtotal;
        
    }
}

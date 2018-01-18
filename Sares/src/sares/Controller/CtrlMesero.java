package sares.Controller;

import java.util.*;
import sares.Model.*;
import java.sql.*;

/**
 * 
 */
public class CtrlMesero {

	/**
	 * Default constructor
	 */
	public CtrlMesero() {
	}

	/**
	 * @return
	 */
	public void cargarPedidos() {
            // TODO implement here

	}

	/**
	 * @return
	 */
	public void cargarPedidosListos() {
            // TODO implement here

	}

	/**
	 * @return
	 */
	public void cargarPedidosEntregados() {
            // TODO implement here

	}

	/**
	 * @return
	 */
	public void callCtrlMaster() {
            // TODO implement here

	}

	/**
	 * @return
	 */
	public void callCtrlCajero() {
            // TODO implement here

	}

	/**
	 * @return
	 */
	public void encolarPedido() {
            // TODO implement here

	}

	/**
	 * @return
	 */
	public void crearCuenta() {
            // TODO implement here

	}

public LinkedList<Item> getItems(Categoria c,int code,Conexion co) throws SQLException{ 
        LinkedList<Item> lista = new LinkedList();
        if (c.getNombre()=="Bebidas"){
            ResultSet itemsRS = co.consultar("SELECT * FROM Item,Bebida where Item.id=Bebida.item");
            while (itemsRS.next()){
                lista.add(new Bebida(itemsRS.getString("marca") ,itemsRS.getFloat("contenido"),itemsRS.getFloat("valor"),itemsRS.getString("nombre"),itemsRS.getString("descripcion"), itemsRS.getBoolean("promo"),itemsRS.getFloat("porcentaje")));
            }  
        }else if(c.getNombre()=="Combo"){
                 
        }else{
            ResultSet itemsRS = co.consultar("SELECT * FROM Item,Platillo where Item.id=Platillo.item");
            while (itemsRS.next()){
                lista.add(new Platillo(itemsRS.getTime("tiempoEstimado") ,itemsRS.getFloat("valor"),itemsRS.getString("nombre"),itemsRS.getString("descripcion"), itemsRS.getBoolean("promo"),itemsRS.getFloat("porcentaje")));
            }
            itemsRS.close();
        }
       return lista;
     }
    
    public LinkedList<Categoria> getCategorias() throws SQLException{
        Conexion c=new Conexion();
        LinkedList<Categoria> lista = new LinkedList();
        Categoria cate;
        ResultSet categoriasRS = c.consultar("SELECT * FROM Categoria"); 
        while (categoriasRS.next()){
            cate= new Categoria();
            cate.setNombre(categoriasRS.getString("nombre"));
            cate.setListItems(this.getItems(cate,categoriasRS.getInt("id"),c));
            lista.add(cate);
        }
        categoriasRS.close();
        return lista;
    }

}
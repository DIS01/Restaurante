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

 public LinkedList<Item> getItems(Categoria c,int code,Connection conexion) throws SQLException{
        LinkedList<Item> lista = new LinkedList();
        Item item;
        Statement consulta = conexion.createStatement();
        String consultaST;
        if (c.getNombre()=="Bebidas"){
            consultaST = "SELECT * FROM Item,Bebida where Item.id=Bebida.item";
            ResultSet itemsRS = consulta.executeQuery(consultaST);
            while (itemsRS.next()){
                item= new Bebida(itemsRS.getString("marca") ,itemsRS.getFloat("contenido"),itemsRS.getFloat("valor"),itemsRS.getString("nombre"),itemsRS.getString("descripcion"), itemsRS.getBoolean("promo"),itemsRS.getFloat("porcentaje"));
                System.out.println(item.getNombre());
                lista.add(item);
            }
            itemsRS.close();
        }else if(c.getNombre()=="Combo"){
                 
        }else{
            consultaST = "SELECT * FROM Item,Platillo where Item.id=Platillo.item";
            ResultSet itemsRS = consulta.executeQuery(consultaST);
            while (itemsRS.next()){
                item= new Platillo(itemsRS.getTime("tiempoEstimado") ,itemsRS.getFloat("valor"),itemsRS.getString("nombre"),itemsRS.getString("descripcion"), itemsRS.getBoolean("promo"),itemsRS.getFloat("porcentaje"));
                System.out.println(item.getNombre());
                lista.add(item);
            }
            itemsRS.close();
        }
       return lista;
     }
    
    public LinkedList<Categoria> getCategorias() throws SQLException{
        Conexion c=new Conexion();
        Connection conexion=Conexion.getConexion();
        LinkedList<Categoria> lista = new LinkedList();
        Categoria cate;
        String consultaST = "SELECT * FROM Categoria";
        Statement consulta = conexion.createStatement();
        ResultSet categoriasRS = consulta.executeQuery(consultaST); 
        while (categoriasRS.next()){
            cate= new Categoria();
            cate.setNombre(categoriasRS.getString("nombre"));
            cate.setListItems(this.getItems(cate,categoriasRS.getInt("id"),conexion));
            System.out.format("%s\n", cate.getNombre());
            lista.add(cate);
        }
        categoriasRS.close();
        return lista;
    }

}
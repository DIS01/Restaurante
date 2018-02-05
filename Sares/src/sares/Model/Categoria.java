package sares.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

/**
 * 
 */
public class Categoria {
    
    /**
     * 
     */
    private String nombre;

    /**
     * 
     */
    private List<Item> listItem;
    
    /**
     * Default constructor
     */
    public Categoria() {
    }
    
    public Categoria(String name){
        this.nombre=name;
    }
	
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Item> getListItems() {
        return listItem;
    }

    public void setListItems(LinkedList<Item> listItems) {
        this.listItem = listItems;
    }
    
    public static Categoria getCategoria(int idCategoria) throws SQLException, ParseException{
        Categoria categoria=null;
        try (ResultSet categoriaRS = Conexion.consultar("SELECT * FROM Categoria where id="+idCategoria)) {
            if(categoriaRS.next()){
                categoria= new Categoria(categoriaRS.getString("nombre"));
            }
        }
        return categoria;
    }
    
    public static LinkedList<Item> getItems(Categoria c) throws SQLException, ParseException {
        LinkedList<Item> lista = new LinkedList();
        ResultSet itemsRS=null;
        if ("Bebidas".equals(c.getNombre())) {
            itemsRS = Conexion.consultar("SELECT * FROM Item,Bebida,Inventario  where Item.id=Bebida.item and Item.id=Inventario.item");
            while (itemsRS.next()) lista.add(new Bebida(itemsRS.getString("marca"),itemsRS.getInt("id"), itemsRS.getFloat("valor"), itemsRS.getString("nombre"), itemsRS.getBoolean("activo"), Categoria.getCategoria(itemsRS.getInt("categoria")),itemsRS.getFloat("stock")));
            return lista;
        } else if ("Combo".equals(c.getNombre())) {
            itemsRS = Conexion.consultar("SELECT * FROM Item,Combo,Inventario  where Item.id=Combo.item and Item.id=Inventario.item");
            while (itemsRS.next()) lista.add(new Combo(null, itemsRS.getFloat("tiempoEstimado"),itemsRS.getInt("id"), itemsRS.getFloat("valor"), itemsRS.getString("nombre"), itemsRS.getBoolean("activo"), Categoria.getCategoria(itemsRS.getInt("categoria")),itemsRS.getFloat("stock")));
            return lista;
        } else if ("Platillos de entrada".equals(c.getNombre())) {
            itemsRS = Conexion.consultar("SELECT * FROM Item,Categoria,Platillo,Inventario where Item.id=Inventario.item and Item.id=Platillo.item and Item.categoria=Categoria.id and Categoria.nombre=\""+c.getNombre()+"\" and stock>0");
        } else if ("Platos Fuerte".equals(c.getNombre())) {
            itemsRS = Conexion.consultar("SELECT * FROM Item,Categoria,Platillo,Inventario where Item.id=Inventario.item and Item.id=Platillo.item and Item.categoria=Categoria.id and Categoria.nombre=\""+c.getNombre()+"\" and stock>0 ");
        } else if ("Postres".equals(c.getNombre())) {
            itemsRS = Conexion.consultar("SELECT * FROM Item,Categoria,Platillo,Inventario where Item.id=Inventario.item and Item.id=Platillo.item and Item.categoria=Categoria.id and Categoria.nombre=\""+c.getNombre()+"\" and stock>0");
        }    
        while (itemsRS.next()) lista.add(new Platillo(itemsRS.getFloat("tiempoEstimado"),itemsRS.getInt("id"), itemsRS.getFloat("valor"), itemsRS.getString("nombre"), itemsRS.getBoolean("activo"), Categoria.getCategoria(itemsRS.getInt("categoria")),itemsRS.getFloat("stock")));
        return lista;
    }
}
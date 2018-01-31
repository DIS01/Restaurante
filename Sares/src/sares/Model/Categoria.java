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
	 * Default constructor
	 */
	public Categoria() {
	}
        public Categoria(String name){
            this.nombre=name;
        }
	/**
	 * 
	 */
	private String nombre;

        /**
	 * 
	 */
	private List<Item> listItem;

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
        ResultSet categoriaRS = Conexion.consultar("SELECT * FROM Categoria where id="+idCategoria);
        if(categoriaRS.next()){
            categoria= new Categoria(categoriaRS.getString("nombre"));
        }   
        categoriaRS.close();
        return categoria;
    }
    
}
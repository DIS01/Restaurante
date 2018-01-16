package sares.Model;

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
	/**
	 * 
	 */
	private String nombre;

	/**
	 * 
	 */
        private String descripcion;
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
}
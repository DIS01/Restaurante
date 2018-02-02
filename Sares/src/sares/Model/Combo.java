package sares.Model;

import java.util.*;
import java.sql.Time;

/**
 * 
 */
public class Combo extends Item{

    public Combo(LinkedList<Item> items, float tiempoEstimado, int id, float valor, String nombre, boolean activo, Categoria categoria, float Stock) {
        super(id, valor, nombre, activo, categoria, Stock);
        this.items = items;
        this.tiempoEstimado = tiempoEstimado;
    }

	

	/**
	 * 
	 */
	public LinkedList<Item> items;

	/**
	 * 
	 */
	public float tiempoEstimado;

    public LinkedList<Item> getItems() {
        return items;
    }

    public void setItems(LinkedList<Item> items) {
        this.items = items;
    }

    public float getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(float tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }
        
        



}
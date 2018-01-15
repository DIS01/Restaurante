package sares.Model;

import java.util.*;
import java.sql.Time;

/**
 * 
 */
public class Pedido {

	/**
	 * Default constructor
	 */
	public Pedido() {
	}

	/**
	 * 
	 */
	protected Platillo producto;

	/**
	 * 
	 */
	protected boolean notificado;

	/**
	 * 
	 */
	protected boolean servido;

	/**
	 * 
	 */
	public Time horaingreso;

	/**
	 * 
	 */
	public float tiempoestimado;

	/**
	 * 
	 */
	public String estado;


	public Cliente cliente;


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

}
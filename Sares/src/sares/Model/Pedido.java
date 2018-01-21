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
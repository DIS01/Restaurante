package sares.zDiagramaModel;

import sares.Model.Bebida;
import java.util.*;
import java.sql.Time;
import sares.Model.Platillo;

/**
 * 
 */
public class Combo {

	/**
	 * Default constructor
	 */
	public Combo() {
	}

	/**
	 * 
	 */
	public LinkedList<Platillo> platos;

	/**
	 * 
	 */
	public LinkedList<Bebida> bebidas;

	/**
	 * 
	 */
	public float costo;

	/**
	 * 
	 */
	public boolean promo;

	/**
	 * 
	 */
	public String nombre;


	/**
	 * 
	 */
	public float porcentaje;

	/**
	 * 
	 */
	public Time tiempoPreparación;



}
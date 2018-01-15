package sares.Model;

import java.util.*;

/**
 * 
 */
public interface PagoDinero {

	/**
	 * @param dinero 
	 * @return
	 */
	public void pay(int dinero);

	/**
	 * @return
	 */
	public Float darCambio();

}
package sares.Model;

import java.util.*;

/**
 * 
 */
public class PagoElectronico implements PagoDinero {

	/**
	 * Default constructor
	 */
	public PagoElectronico() {
	}

	/**
	 * 
	 */
	private String identificador;

	/**
	 * @return
	 */
	public Float darCambio() {
		// TODO implement here
		return null;
	}

    @Override
    public void pay(int dinero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
package sares.zDiagramaModel;

import java.util.*;

/**
 * 
 */
public class PagoTarjeta implements PagoDinero{

	/**
	 * Default constructor
	 */
	public PagoTarjeta() {
	}

	/**
	 * 
	 */
	private String numTarjeta;

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
package sares.Model;

import java.util.*;

/**
 * 
 */
public interface PedidoI {




	/**
	 * @param Mesero 
	 * @return
	 */
	public void anadir(Mesero mesero);

	/**
	 * @param Mesero 
	 * @return
	 */
	public void eliminar(Mesero mesero);

	/**
	 * @return
	 */
	public void notificar();

}
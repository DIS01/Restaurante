package sares.zDiagramaModel;

import java.util.*;
import sares.Model.Mesero;

/**
 * 
 */
public interface MenuI {




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
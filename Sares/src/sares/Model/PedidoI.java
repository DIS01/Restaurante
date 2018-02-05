package sares.Model;

import java.util.*;

/**
 * 
 */
public interface PedidoI {

    /**
     * @param mesero
     */
    public void anadir(Mesero mesero);

    /**
     * @param mesero
     */
    public void eliminar(Mesero mesero);

    /**
     */
    public void notificar();

}
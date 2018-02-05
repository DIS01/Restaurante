package sares.zDiagramaModel;

import java.sql.Time;
import java.util.Date;
import sares.Model.Cuenta;
import sares.Model.Pedido;

/**
 * 
 */
public class ServicioDomicilio extends Pedido {

    public ServicioDomicilio(String direccion, int id, Time horaingreso, float tiempoestimado, String estado, Cuenta cuenta, Date fecha) {
        super(id, horaingreso, tiempoestimado, estado, cuenta, fecha);
        this.direccion = direccion;
    }

	

	/**
	 * 
	 */
	private String direccion;

}
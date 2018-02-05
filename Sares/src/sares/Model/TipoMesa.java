package sares.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mdleiton
 */
public class TipoMesa {
    
    /**
     * 
     */
    private int stock;
    
    /**
     * 
     */
    private String tipo;
    
    /**
     * 
     */
    private int asignada;
    
    /**
     * 
     */
    private int numSillas;

    public TipoMesa() {
    }

    public TipoMesa(int stock, String tipo, int asignadas, int numSillas) {
        this.stock = stock;
        this.tipo = tipo;
        this.asignada = asignadas;
        this.numSillas = numSillas;
    }
    
    public static TipoMesa getTipoMesa(int id) throws SQLException{
        Conexion c=new Conexion();
        try (ResultSet tipoMesaRS = c.consultar("SELECT * FROM TipoMesa where id="+id)) {
            if(tipoMesaRS.next()){
                return new TipoMesa(tipoMesaRS.getInt("stock"),tipoMesaRS.getString("tipo"),tipoMesaRS.getInt("asignada"),tipoMesaRS.getInt("numSillas"));
            }
        }
        return null;
    }    
}
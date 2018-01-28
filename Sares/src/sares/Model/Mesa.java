package sares.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 
 */
public class Mesa {

    /**
     * Default constructor
     */
    public Mesa() {
    }

    public Mesa(int id2, AmbienteMesa ambienteMesa) {
        this.id2 = id2;
        this.ambienteMesa = ambienteMesa;
    }

    /**
     * 
     */
    private int id2;

    /**
     * 
     */
    private AmbienteMesa ambienteMesa;


    /**
     * @return
     */
    public void asignar() {
            // TODO implement here
    }

    public static Mesa getMesa(int id2) throws SQLException{
        Conexion c=new Conexion();
    
        try (ResultSet mesaRS = c.consultar("SELECT * FROM Mesa where id2="+id2)) {
            if(mesaRS.next()){
                return new Mesa(id2,AmbienteMesa.getAmbienteMesa(mesaRS.getInt("ambienteMesa")));
            }
        }
        return null;
    }    
}
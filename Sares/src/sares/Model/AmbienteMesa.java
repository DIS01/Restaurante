/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mdleiton
 */
public class AmbienteMesa {
    /**
     * 
     */
    private Ambiente ambiente;
    
    /**
     * 
     */
    private TipoMesa tipoMesa;
    
    /**
     * 
     */
    private int asignadas;

    
    public AmbienteMesa() {
    }

    public AmbienteMesa(Ambiente ambiente, TipoMesa tipoMesa, int asignadas) {
        this.ambiente = ambiente;
        this.tipoMesa = tipoMesa;
        this.asignadas = asignadas;
    }

    
    public static AmbienteMesa getAmbienteMesa(int id) throws SQLException{
        Conexion c=new Conexion();
        try (ResultSet AmbientemesaRS = c.consultar("SELECT * FROM AmbienteMesa where id="+id)) {
            if(AmbientemesaRS.next()){
                return new AmbienteMesa(Ambiente.getAmbiente(AmbientemesaRS.getInt("ambiente")),TipoMesa.getTipoMesa(AmbientemesaRS.getInt("ambiente")),AmbientemesaRS.getInt("asignadas"));
            }
        }
        return null;
    }    

}
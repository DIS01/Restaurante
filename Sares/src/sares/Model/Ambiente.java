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
public class Ambiente {
    /**
     * 
     */
    private String nombre;
    
    /**
     * 
     */
    private boolean activo;

    public Ambiente(String nombre, boolean activo) {
        this.nombre = nombre;
        this.activo = activo;
    }

    public static Ambiente getAmbiente(int id) throws SQLException{
        Conexion c=new Conexion();
        try (ResultSet ambienteRS = c.consultar("SELECT * FROM Ambiente where id="+id)) {
            if(ambienteRS.next()){
                return new Ambiente(ambienteRS.getString("nombre"),ambienteRS.getBoolean("activo"));
            }
        }
        return null;
    }    
}

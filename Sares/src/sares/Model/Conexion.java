
package sares.Model;

import java.sql.*;
/**
 *
 * @author mdleiton
 */
public class Conexion {

    private static Connection conexion  = null;
    private static Statement consulta = null;
    
    public static Connection getConexion() {
        String host                = "sql10.freesqldatabase.com";
        String usuario             = "sql10218656";
        String clave               = "Qpnc2WQMkA";
        int puerto                  = 3306;
        String bd                   = "sql10218656";
        String servidor= "jdbc:mysql://"+host+":"+puerto+"/"+bd;
        if(Conexion.conexion == null)
        {
            try{
                Conexion.conexion=DriverManager.getConnection(servidor,usuario,clave);
                Conexion.consulta = Conexion.conexion.createStatement();
            }catch(SQLException e){
                System.out.println(e.getSQLState());
            }
        }
        return Conexion.conexion;
    }
    
    public static ResultSet consultar(String sql){
        try{
            Conexion.consulta = Conexion.conexion.createStatement();
            return Conexion.consulta.executeQuery(sql);
        }catch(SQLException e){
            System.out.println(e.getSQLState());
        }
        return null;
    }
    
    public static boolean insertar(String sql){
        try{
            Conexion.consulta = Conexion.conexion.createStatement();
            if(Conexion.consulta.executeUpdate(sql)>0) return true;
        }catch(SQLException e){
            System.out.println(e.getSQLState());
        }
        return false;
    }
    
    public static void CerrarConexion(){
         try{
            Conexion.conexion.close();
            Conexion.consulta.close();
            Conexion.conexion = null;
            Conexion.consulta = null;
        }catch(SQLException e){
            System.out.println(e.getSQLState());
        }
    }
}


package sares.Model;

import java.sql.*;
/**
 *
 * @author mdleiton
 */
public class Conexion {
    
    private String  host;
    private String  usuario;
    private String  clave ;
    private int puerto ;
    private String bd ;
    private String  servidor ;
    private static Connection conexion  = null;
    private Statement consulta;

    public Conexion() {
        host                = "sql10.freesqldatabase.com";
        usuario             = "sql10217279";
        clave               = "6ABPslLVs2";
        puerto                  = 3306;
        bd                   = "sql10217279";
        this.servidor= "jdbc:mysql://"+this.host+":"+this.puerto+"/"+bd;
        try{
            conexion=DriverManager.getConnection(this.servidor,this.usuario, this.clave);
            consulta = conexion.createStatement();
        }catch(SQLException e){
            System.out.println(e.getSQLState());
        }
    }
    
    public Connection getConexion() {
        return conexion;
    } 
    
    public ResultSet consultar(String sql){
        try{
            return consulta.executeQuery(sql);
        }catch(SQLException e){
            System.out.println(e.getSQLState());
        }
        return null;
    }
    
    public boolean insertar(String sql){
        try{
            if(consulta.executeUpdate(sql)>0){
                return true;
            }
        }catch(SQLException e){
            System.out.println(e.getSQLState());
        }
        return false;
    }
    
    public void CerrarConexion(){
         try{
            conexion.close();
            consulta.close();
        }catch(SQLException e){
            System.out.println(e.getSQLState());
        }
    }
    
}

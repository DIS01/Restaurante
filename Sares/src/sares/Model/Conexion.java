
package sares.Model;

import java.sql.*;
/**
 *
 * @author mdleiton
 */
public class Conexion {
    Connection conexion;
    
    
    public Connection obtenerConexion(){
        try{
            conexion=DriverManager.getConnection("jdbc:mysql:sares1.000webhostapp.com:3306/id4286325_sares", "id4286325_mdleiton", "1234567890");
        }catch(Exception e){
            
        }
        return conexion;
    }
    
}

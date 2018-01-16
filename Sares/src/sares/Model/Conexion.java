
package sares.Model;

import java.sql.*;
import java.util.LinkedList;
/**
 *
 * @author mdleiton
 */
public class Conexion {
    private String  host    = "sql10.freesqldatabase.com";
    private String  usuario     = "sql10215733";
    private String  clave       = "IekRvgf4sy";
    private int puerto          = 3306;
    private String bd ="sql10215733";
    private String  servidor    = "";
    private static Connection conexion  = null;

    public Conexion() {
        this.servidor= "jdbc:mysql://"+this.host+":"+this.puerto+"/"+bd;
        try{
            conexion=DriverManager.getConnection(this.servidor,this.usuario, this.clave);
        }catch(SQLException e){
          
         System.out.println("1111");
        }
    }
    
    public LinkedList<Item> getItems(Categoria c,int code) throws SQLException{
        LinkedList<Item> lista = new LinkedList();
        Item item;
        Statement consulta = conexion.createStatement();
        String consultaST;
        if (c.getNombre()=="Bebidas"){
            consultaST = "SELECT * FROM Item,Bebida where Item.id=Bebida.item";
            ResultSet itemsRS = consulta.executeQuery(consultaST);
            while (itemsRS.next()){
                item= new Bebida(itemsRS.getString("marca") ,itemsRS.getFloat("contenido"),itemsRS.getFloat("valor"),itemsRS.getString("nombre"),itemsRS.getString("descripcion"), itemsRS.getBoolean("promo"),itemsRS.getFloat("porcentaje"));
                System.out.println(item.nombre);
                lista.add(item);
            }
            itemsRS.close();
        }else if(c.getNombre()=="Combo"){
                 
        }else{
            consultaST = "SELECT * FROM Item,Platillo where Item.id=Platillo.item";
            ResultSet itemsRS = consulta.executeQuery(consultaST);
            while (itemsRS.next()){
                item= new Platillo(itemsRS.getTime("tiempoEstimado") ,itemsRS.getFloat("valor"),itemsRS.getString("nombre"),itemsRS.getString("descripcion"), itemsRS.getBoolean("promo"),itemsRS.getFloat("porcentaje"));
                System.out.println(item.nombre);
                lista.add(item);
            }
            itemsRS.close();
        }
       return lista;
     }
    
    public LinkedList<Categoria> getCategorias() throws SQLException{
        LinkedList<Categoria> lista = new LinkedList();
        Categoria cate;
        String consultaST = "SELECT * FROM Categoria";
        Statement consulta = conexion.createStatement();
        ResultSet categoriasRS = consulta.executeQuery(consultaST); 
        while (categoriasRS.next()){
            cate= new Categoria();
            cate.setNombre(categoriasRS.getString("nombre"));
            cate.setListItems(this.getItems(cate,categoriasRS.getInt("id")));
            System.out.format("%s\n", cate.getNombre());
            lista.add(cate);
        }
        categoriasRS.close();
        return lista;
    }
    
    public static Connection getConexion() {
        return conexion;
    } 
    
    
    public static void main(String[] args) throws SQLException {
        Conexion c= new Conexion();
        Connection cn=Conexion.getConexion();
        LinkedList<Categoria > cate=c.getCategorias();
            
        
    }
}

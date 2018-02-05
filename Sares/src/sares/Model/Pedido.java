package sares.Model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Time;
import java.text.ParseException;

/**
 * 
 */
public class Pedido {

    /**
     * 
     */
    public int id;
    
    /**
     * 
     */
    public Time horaingreso;

    /**
     * 
     */
    public float tiempoestimado;

    /**
     * 
     */
    public String estado;

    /**
     * 
     */
    public Cuenta cuenta;
    
    /**
     * 
     */
    public Date fecha;
    
    /**
     * 
     */
    public LinkedList<PedidoDetalle> pedidosDetalles;
     
    public Pedido(int id, Time horaingreso, float tiempoestimado, String estado, Cuenta cuenta, Date fecha) {
        this.id = id;
        this.horaingreso = horaingreso;
        this.tiempoestimado = tiempoestimado;
        this.estado = estado;
        this.cuenta = cuenta;
        this.fecha = fecha;
    }

    /**
     * @param platillo
     */
    public void getValor(Platillo platillo) {
            // TODO implement here
    }

        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getHoraingreso() {
        return horaingreso;
    }

    public void setHoraingreso(Time horaingreso) {
        this.horaingreso = horaingreso;
    }

    public float getTiempoestimado() {
        return tiempoestimado;
    }

    public void setTiempoestimado(float tiempoestimado) {
        this.tiempoestimado = tiempoestimado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public LinkedList<PedidoDetalle> getPedidosDetalles() {
        return pedidosDetalles;
    }

    public void setPedidosDetalles(LinkedList<PedidoDetalle> pedidosDetalles) {
        this.pedidosDetalles = pedidosDetalles;
    }
    
    /**
     * @return
     */
    public void modificarPedido() {
        // TODO implement here

    }

    /**
     * @return
     */
    public void agregarAPrioridad() {
        // TODO implement here

    }
    
    public static LinkedList<Pedido> getPedidos() throws SQLException, ParseException{
        LinkedList<Pedido> lista= new LinkedList();
        Pedido pedido;
        ResultSet pedidoRS = Conexion.consultar("SELECT * FROM Pedido order by id desc"); 
        while (pedidoRS.next()){
            pedido= new Pedido(pedidoRS.getInt("id"),pedidoRS.getTime("horaingreso"),pedidoRS.getFloat("tiempoestimado"),pedidoRS.getString("estado"),null,pedidoRS.getDate("fecha"));
           lista.add(pedido);
        }
        pedidoRS.close();
        return lista;
    }
    
    public static Pedido getPedido(int id) throws SQLException, ParseException{
        Pedido pedido=null;
        ResultSet pedidoRS = Conexion.consultar("SELECT * FROM Pedido where id="+id); 
        while (pedidoRS.next()){
            pedido= new Pedido(pedidoRS.getInt("id"),pedidoRS.getTime("horaingreso"),pedidoRS.getFloat("tiempoestimado"),pedidoRS.getString("estado"),null,pedidoRS.getDate("fecha"));
        }
        pedidoRS.close();
        return pedido;
    }
    
    public static LinkedList<Pedido> getPedidos(Cuenta cuenta) throws SQLException, ParseException{
        LinkedList<Pedido> lista= new LinkedList();
        Pedido pedido;
        ResultSet pedidoRS = Conexion.consultar("SELECT * FROM Pedido where cuenta="+cuenta.getId()+ " order by id desc"); 
        while (pedidoRS.next()){
            pedido= new Pedido(pedidoRS.getInt("id"),pedidoRS.getTime("horaingreso"),pedidoRS.getFloat("tiempoestimado"),pedidoRS.getString("estado"),cuenta,pedidoRS.getDate("fecha"));
           lista.add(pedido);
        }
        pedidoRS.close();
        return lista;
    }
    
    @Override
    public String toString() {
        return this.id +", " + this.estado+", Cuenta #:"+this.cuenta;
    }   
    
    public static float getTotal(LinkedList<Pedido> pedidos) throws SQLException{
        float total=0.0f;
        for (Pedido pedido:pedidos){
            total+=PedidoDetalle.getTotal(pedido);
        }
        return total;
    }
    
    public static int insertarPedido( String estado , int cuenta ) throws SQLException{
        CallableStatement statement = Conexion.getConexion().prepareCall("{call insertarPedidoMesero(?,?)}");
        statement.setString(1,estado); 
        statement.setInt(2,cuenta); 
        statement.execute();
        ResultSet r = statement.getResultSet();
        r.next();
        return r.getInt(1);
    }
    
    public static void actualizarPedidoCuenta(int cuentaID ,int pedidoID,float tiempoEstimado1,float total1) throws SQLException{
        CallableStatement statement = Conexion.getConexion().prepareCall("{call actualizarPedidoCuenta(?,?,?,?)}");
        statement.setInt(1,cuentaID); 
        statement.setInt(2,pedidoID);
        statement.setFloat(3,tiempoEstimado1);
        statement.setFloat(4,total1);
        statement.execute();
    }
    
    public float actualizarTiempoEstimado(){
        float temax=0.0f;
        int contador=0;
        for (PedidoDetalle pd: this.pedidosDetalles){
            if(pd.getItem().getCategoria().getNombre().equals("Platillos de entrada") || pd.getItem().getCategoria().getNombre().equals("Platos Fuerte") || pd.getItem().getCategoria().getNombre().equals("Postres")){
                Platillo c=(Platillo)pd.getItem();
                if(temax<c.getTiempoEstimado()) temax=c.getTiempoEstimado();
                contador+=(Integer)pd.getCantidad();
            }else if(pd.getItem().getCategoria().getNombre().equals("Combo") ){
                Combo c=(Combo)pd.getItem();
                if(temax<c.getTiempoEstimado()) temax=c.getTiempoEstimado();
                contador+=(Integer)pd.getCantidad();
            }
        }
        temax=temax+3*(contador-1);
        if(contador==0) temax=0.0f;
        this.tiempoestimado=temax;
        return temax;
        }
}
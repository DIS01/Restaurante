package sares.Model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 
 */
public class PagoContexto {

    private MetodoPago metodoPago;
    
    public void setPaymentStrategy(MetodoPago estrategia){
        this.metodoPago = estrategia;
    }

    public MetodoPago getMetodoPago(){
        return metodoPago;
    }

    public boolean pay(float amount){
        return metodoPago.pay(amount);
    }
    
    /**
     * @return
     */
    public int getPago() {
            // TODO implement here
            return 1;
    }

    /**
     * @param pago 
     * @return
     */
    public void setPago(int pago) {
            // TODO implement here
    }

    /**
     * @param cuenta
     */
    public void pago(Cuenta cuenta) {
            // TODO implement here
    }
    
    public static LinkedList<String> getMetodoCuentas() throws SQLException{
        LinkedList<String> metodos=new LinkedList<>();
        Conexion c=new Conexion();
        try (ResultSet metodoPagoRS = Conexion.consultar("SELECT * FROM TipoPago")) {
            while(metodoPagoRS.next()){
                metodos.add(metodoPagoRS.getString("tipo"));
            }
        return metodos;
        }
    }    
    
    public static void registrarPagoCuenta(int tipoPagoID ,int  cuentaID ,float cantidad ) throws SQLException{
        CallableStatement statement = Conexion.getConexion().prepareCall("{call registrarPagoCuenta(?,?,?)}");
        statement.setInt(1,tipoPagoID); 
        statement.setInt(2,cuentaID);
        statement.setFloat(3,cantidad);
        statement.execute();
        ResultSet r = statement.getResultSet();
    }
}
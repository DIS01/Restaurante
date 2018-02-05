package sares.Model;

/**
 * 
 */
public class PagoTarjeta implements MetodoPago{
	
    /**
     * 
     */
    private String numTarjeta;

    public PagoTarjeta() {
    }
    
    
    public PagoTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }
    
    
    
    @Override
    public boolean pay(float dinero) {
        //validar pago por tarjeta de credito
         return true;
    }

}
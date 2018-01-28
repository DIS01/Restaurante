package sares.Model;

/**
 * 
 */
public class PagoElectronico implements MetodoPago {

    /**
     * 
     */
    private String identificador;

    public PagoElectronico() {
    }

    
    public PagoElectronico(String identificador) {
        this.identificador = identificador;
    }

    
    @Override
    public boolean pay(float dinero) {
        //validar pago por dinero electronico
        return true;
    }

}
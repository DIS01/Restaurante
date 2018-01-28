package sares.Model;

/**
 * 
 */
public class PagoEfectivo implements MetodoPago {

    @Override
    public boolean pay(float dinero) {
        return true;
    }

    /**
    * @return
    */
    public Float darCambio() {
        // TODO implement here
        return null;
    }
}
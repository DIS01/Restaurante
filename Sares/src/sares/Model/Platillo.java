package sares.Model;

import java.sql.Time;

/**
 * 
 */
public class Platillo extends Item{

    /**
     *  
    */
    private Time tiempoEstimado;
        
    public Platillo(Time tiempoEstimado, float valor, String nombre, boolean activo, Categoria categoria) {
        super(valor, nombre, activo, categoria);
        this.tiempoEstimado = tiempoEstimado;
    }

    public Platillo(Time tiempoEstimado, int id, float valor, String nombre, boolean activo, Categoria categoria) {
        super(id, valor, nombre, activo, categoria);
        this.tiempoEstimado = tiempoEstimado;
    }
    
    public Time getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(Time tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }


}
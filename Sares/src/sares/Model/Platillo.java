package sares.Model;

import java.sql.Time;

/**
 * 
 */
public class Platillo extends Item{

    /**
     *  
    */
    private float tiempoEstimado;
        
    public Platillo(float tiempoEstimado, float valor, String nombre, boolean activo, Categoria categoria) {
        super(valor, nombre, activo, categoria);
        this.tiempoEstimado = tiempoEstimado;
    }

    public Platillo(float tiempoEstimado, int id, float valor, String nombre, boolean activo, Categoria categoria) {
        super(id, valor, nombre, activo, categoria);
        this.tiempoEstimado = tiempoEstimado;
    }
    
    public float getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(float tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }


}
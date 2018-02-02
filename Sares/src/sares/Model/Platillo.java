package sares.Model;

/**
 * 
 */
public class Platillo extends Item{

    /**
     *  
    */
    private float tiempoEstimado;

    public Platillo(float tiempoEstimado, int id, float valor, String nombre, boolean activo, Categoria categoria, float Stock) {
        super(id, valor, nombre, activo, categoria, Stock);
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
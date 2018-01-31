package sares.Model;

/**
 * 
 */
public class Bebida extends Item{
    
    /**
    * 
    */
    private String marca;

    public Bebida(String marca, int id, float valor, String nombre, boolean activo, Categoria categoria) {
        super(id, valor, nombre, activo, categoria);
        this.marca = marca;
    }
        
    public Bebida(String marca, float valor, String nombre, boolean activo, Categoria categoria) {
        super(valor, nombre, activo, categoria);
        this.marca = marca;
    }
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
}
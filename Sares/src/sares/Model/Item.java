/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares.Model;

/**
 *
 * @author mdleiton
 */
public class Item{
    /**
    * 
    */
    protected int id;
    /**
    * 
    */
    protected float valor;

   /**
    * 
    */
   protected String nombre;

   /**
    * 
    */
   protected boolean activo;

   /**
    * 
    */
   protected Categoria categoria;

    public Item(float valor, String nombre, boolean activo, Categoria categoria) {
        this.valor = valor;
        this.nombre = nombre;
        this.activo = activo;
        this.categoria = categoria;
    }

    public Item(int id, float valor, String nombre, boolean activo, Categoria categoria) {
        this.id = id;
        this.valor = valor;
        this.nombre = nombre;
        this.activo = activo;
        this.categoria = categoria;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        
   
}
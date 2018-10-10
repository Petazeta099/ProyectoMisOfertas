/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Raul
 */
public class Producto {
    
    String sku;
    String nombre;
    String descripcion;
    int categoria_id;
    int marca_id;

public void Producto() {}

    public String getSku() {
        return sku;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public int getCategoria_id() {
        return categoria_id;
    }
    public int getMarca_id() {
        return marca_id;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }
    public void setMarca_id(int marca_id) {
        this.marca_id = marca_id;
    }
    
}

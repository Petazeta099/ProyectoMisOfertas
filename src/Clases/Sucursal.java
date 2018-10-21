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
public class Sucursal {
    
    int id;
    String nombre;
    String direccion;
    int comuna_id;
    String retail_rut;
    String nombreComuna;
    String nombreRetail;

    public Sucursal() {
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDireccion() {
        return direccion;
    }
    public int getComuna_id() {
        return comuna_id;
    }
    public String getRetail_rut() {
        return retail_rut;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void setComuna_id(int comuna_id) {
        this.comuna_id = comuna_id;
    }
    public void setRetail_rut(String retail_rut) {
        this.retail_rut = retail_rut;
    }
    
    
    public String getNombreComuna() {
        return nombreComuna;
    }

    public void setNombreComuna(String nombreComuna) {
        this.nombreComuna = nombreComuna;
    }

    public String getNombreRetail() {
        return nombreRetail;
    }

    public void setNombreRetail(String nombreRetail) {
        this.nombreRetail = nombreRetail;
    }
    
}

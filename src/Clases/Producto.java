/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template the editor.
 */
package Clases;

import Conexion.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    String tipo_cat;
    String nombre_marca;

    

 
    
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

     public String getTipo_cat() {
        return tipo_cat;
    }

    public void setTipo_cat(String tipo_cat) {
        this.tipo_cat = tipo_cat;
    }

    public String getNombre_marca() {
        return nombre_marca;
    }

    public void setNombre_marca(String nombre_marca) {
        this.nombre_marca = nombre_marca;
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
    public Categoria obtenerCategoria(int idCat){
    Database cn = new Database();
        String sql = "SELECT * FROM categoria WHERE id='" + idCat + "' LIMIT 1";
                        Categoria c = new Categoria();

        ResultSet lista = null;
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            
                c.setId(lista.getInt(1));
                c.setTipo(lista.getString(2));
                
            }catch(Exception ex){}finally{
            try{
                ps.close();
                lista.close();
                cn.desconectar();
            }catch(Exception ex){}} System.out.println(c.tipo);
        return c;
        
}
}

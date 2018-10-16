/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Conexion.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Jonathan
 */
public class Categoria {
    
     int id;
    String tipo;

    public Categoria() {
    }

    public Categoria(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
 private Categoria obtenerCategoria(int idCat){
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
            }catch(Exception ex){}
        return c;
}
    

    
}

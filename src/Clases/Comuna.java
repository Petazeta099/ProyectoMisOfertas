/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Conexion.Database;
import java.sql.ResultSet;
import javax.swing.JComboBox;

/**
 *
 * @author Raul
 */
public class Comuna {
    
    int id;
    String nombre;
    int region_id;

    public Comuna() {
    }

    public Comuna(int id, String nombre, int region_id) {
        this.id = id;
        this.nombre = nombre;
        this.region_id = region_id;
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public int getRegion_id() {
        return region_id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }
    
    public void mostrarComuna(JComboBox<Comuna> cb_comuna){
        
        cb_comuna.removeAllItems();
        ResultSet lista = Database.crearConsulta("select id, nombre, region_id from comuna");        
        
        try{
            while(lista.next()){
                cb_comuna.addItem(new Comuna(lista.getInt("id"), lista.getString("nombre"), lista.getInt("region_id")));                
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}

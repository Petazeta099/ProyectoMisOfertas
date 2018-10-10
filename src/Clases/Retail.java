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
public class Retail {
    
    String rut;
    String razon_social;

    public Retail(String rut, String razon_social) {
        this.rut = rut;
        this.razon_social = razon_social;
    }

    public Retail() {
    }

    public String getRut() {
        return rut;
    }
    public String getRazon_social() {
        return razon_social;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }
    
    public void mostrarRetail(JComboBox<Retail> cb_retail){
        
        cb_retail.removeAllItems();
        ResultSet lista = Database.crearConsulta("select rut, razon_social from retail");        
        
        try{
            while(lista.next()){
                cb_retail.addItem(new Retail(lista.getString("rut"), lista.getString("razon_social")));                
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        
    }

    @Override
    public String toString() {
        return razon_social;
    }
        
}

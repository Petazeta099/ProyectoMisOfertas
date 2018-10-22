/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Conexion.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Raul
 */
public class CRUDretail {
    
    public ArrayList<Retail> Listar_Retail(){
        ArrayList<Retail> list = new ArrayList<Retail>();
        Database cn = new Database();
        String sql = "SELECT * FROM retail";
        ResultSet lista = null;
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while(lista.next()){
                Retail rt = new Retail();
                rt.setRut(lista.getString(1));
                rt.setRazon_social(lista.getString(2));
                list.add(rt);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                lista.close();
                cn.desconectar();
            }catch(Exception ex){}
        }
        return list;
    }


/*Metodo agregar*/
    public void Agregar_Retail(Retail rt){
        Database cn = new Database();
        String sql = "INSERT INTO retail (rut, razon_social) VALUES(?,?)";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setString(1, rt.getRut());
            ps.setString(2, rt.getRazon_social());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "El Retail se a agregado correctamente");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "El retail ya existe en el sistema", "Aviso", JOptionPane.ERROR_MESSAGE);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                cn.desconectar();
            }catch(Exception ex){}
        }
    }


/*Metodo Modificar*/
    public void Modificar_Retail(Retail rt){
        Database cn = new Database();
        String sql = "UPDATE retail SET razon_social = ? WHERE rut = ?";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setString(1, rt.getRazon_social());
            ps.setString(2, rt.getRut());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "El Retail se a modificado correctamente");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                cn = null;
            }catch(Exception ex){}
        }
    }


/*Metodo Eliminar*/
    public void Eliminar_Retail(Retail rt){
        Database cn = new Database();
        String sql = "DELETE FROM retail WHERE rut = ?";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setString(1, rt.getRut());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "El retail se a eliminado correctamente");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                cn = null;
            }catch(Exception ex){}
        }
    }
    
}

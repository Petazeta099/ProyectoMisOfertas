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

/**
 *
 * @author Raul
 */
public class CRUDencargado {
    
    public ArrayList<Encargado> Listar_Encargado(){
        ArrayList<Encargado> list = new ArrayList<Encargado>();
        Database cn = new Database();
        String sql = "SELECT * FROM encargado";
        ResultSet lista = null;
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while(lista.next()){
                Encargado enc = new Encargado();
                enc.setRun(lista.getString(1));
                enc.setP_nombre(lista.getString(2));
                enc.setS_nombre(lista.getString(3));
                enc.setApellido_p(lista.getString(4));
                enc.setApellido_m(lista.getString(5));
                enc.setClave(lista.getString(6));
                enc.setActivo(lista.getString(7).charAt(0));
                enc.setRetail_rut(lista.getString(8));
                list.add(enc);
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
    public void Agregar_Encargado(Encargado enc){
        Database cn = new Database();
        String sql = "INSERT INTO encargado (run,p_nombre,s_nombre,apellido_p,apellido_m,clave,activo,retail_rut) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setString(1, enc.getRun());
            ps.setString(2, enc.getP_nombre());
            ps.setString(3, enc.getS_nombre());
            ps.setString(4, enc.getApellido_p());
            ps.setString(5, enc.getApellido_m());
            ps.setString(6, enc.getClave());
            ps.setString(7, String.valueOf(enc.getActivo()));
            ps.setString(8, enc.getRetail_rut());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
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
    public void Modificar_Encargado(Encargado enc){
        Database cn = new Database();
        String sql = "UPDATE encargado SET p_nombre = ?, s_nombre = ?, apellido_p = ?, apellido_m = ?, clave = ?, activo = ?, retail_rut = ? WHERE run = ?";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setString(1, enc.getP_nombre());
            ps.setString(2, enc.getS_nombre());
            ps.setString(3, enc.getApellido_p());
            ps.setString(4, enc.getApellido_m());
            ps.setString(5, enc.getClave());
            ps.setString(6, String.valueOf(enc.getActivo()));
            ps.setString(7, enc.getRetail_rut());
            ps.setString(8, enc.getRun());
            ps.executeUpdate();
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
    public void Eliminar_Encargado(Encargado enc){
        Database cn = new Database();
        String sql = "DELETE FROM encargado WHERE run = ?";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setString(1, enc.getRun());
            ps.executeUpdate();
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

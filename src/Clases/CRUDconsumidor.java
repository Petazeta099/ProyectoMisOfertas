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
public class CRUDconsumidor {
    
    public ArrayList<Consumidor> Listar_Consumidor(){
        ArrayList<Consumidor> list = new ArrayList<Consumidor>();
        Database cn = new Database();
        String sql = "SELECT * FROM consumidor";
        ResultSet lista = null;
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while(lista.next()){
                Consumidor cons = new Consumidor();
                cons.setRun(lista.getString(1));
                cons.setP_nombre(lista.getString(2));
                cons.setS_nombre(lista.getString(3));
                cons.setApellido_p(lista.getString(4));
                cons.setApellido_m(lista.getString(5));
                cons.setEmail(lista.getString(6));
                cons.setClave(lista.getString(7));
                cons.setPuntaje(lista.getInt(8));
                cons.setActivo(lista.getString(9).charAt(0));
                cons.setSuscrito(lista.getString(10).charAt(0));
                cons.setComuna_id(lista.getInt(11));
                list.add(cons);
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
    public void Agregar_Consumidor(Consumidor cons){
        Database cn = new Database();
        String sql = "INSERT INTO consumidor (run,p_nombre,s_nombre,apellido_p,apellido_m,email,clave,puntaje,activo,suscrito,comuna_id) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setString(1, cons.getRun());
            ps.setString(2, cons.getP_nombre());
            ps.setString(3, cons.getS_nombre());
            ps.setString(4, cons.getApellido_p());
            ps.setString(5, cons.getApellido_m());
            ps.setString(6, cons.getEmail());
            ps.setString(7, cons.getClave());
            ps.setInt(8, cons.getPuntaje());
            ps.setString(9, String.valueOf(cons.getActivo()));
            ps.setString(10, String.valueOf(cons.getSuscrito()));
            ps.setInt(11, cons.getComuna_id());
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
    public void Modificar_Consumidor(Consumidor cons){
        Database cn = new Database();
        String sql = "UPDATE consumidor SET p_nombre = ?, s_nombre = ?, apellido_p = ?, apellido_m = ?, email = ?, clave = ?, puntaje = ?, activo = ?, suscrito = ?, comuna_id = ? WHERE run = ?";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setString(1, cons.getP_nombre());
            ps.setString(2, cons.getS_nombre());
            ps.setString(3, cons.getApellido_p());
            ps.setString(4, cons.getApellido_m());
            ps.setString(5, cons.getEmail());
            ps.setString(6, cons.getClave());
            ps.setInt(7, cons.getPuntaje());
            ps.setString(8, String.valueOf(cons.getActivo()));
            ps.setString(9, String.valueOf(cons.getSuscrito()));
            ps.setInt(10, cons.getComuna_id());
            ps.setString(11, cons.getRun());
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
    public void Eliminar_Consumidor(Consumidor cons){
        Database cn = new Database();
        String sql = "DELETE FROM consumidor WHERE run = ?";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setString(1, cons.getRun());
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Conexion.Database;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Raul
 */
public class CRUDoferta {
    
    public ArrayList<Oferta> Listar_Oferta(){
        ArrayList<Oferta> list = new ArrayList<Oferta>();
        Database cn = new Database();
        String sql = "SELECT * FROM oferta order by id asc";
        ResultSet lista = null;
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while(lista.next()){
                Oferta of = new Oferta();
                of.setId(lista.getInt(1));
                of.setTitulo(lista.getString(2));
                of.setDescripcion(lista.getString(3));
                of.setFecha_inicio(Date.valueOf(lista.getString(4)));
                of.setFecha_termino(Date.valueOf(lista.getString(5)));
                of.setPrecio_normal(lista.getInt(6));
                of.setPrecio_oferta(lista.getInt(7));
                of.setCompra_min(lista.getInt(8));
                of.setCompra_max(9);
                of.setActiva(lista.getString(10).charAt(0));
                of.setRut_encargado(lista.getString(11));
                list.add(of);
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

    public void PublicarOferta(Oferta of){
        Database cn = new Database();
        String sql = "UPDATE oferta SET activa = ?, WHERE id = ? AND encargado_run = ?";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setString(1, String.valueOf(of.getActiva()));
            ps.setString(2, String.valueOf(of.getId()));
            ps.setString(3, of.getRut_encargado());
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
    public void ActDescuento(int precioOferta,int idOferta){
       Database cn = new Database();
        String sql = "UPDATE oferta SET precio_oferta = ? WHERE id = ?";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setInt(1, precioOferta);
            ps.setInt(2, idOferta);
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

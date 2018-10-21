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
public class CRUDsucursal {
    
    public ArrayList<Sucursal> Listar_Sucursal(){
        ArrayList<Sucursal> list = new ArrayList<Sucursal>();
        Database cn = new Database();
        String sql = "SELECT s.id,s.nombre,s.direccion,c.id,r.rut,c.nombre,r.razon_social FROM sucursal s INNER JOIN retail r ON retail_rut = r.rut INNER JOIN comuna c ON s.comuna_id = c.id";
        ResultSet lista = null;
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while(lista.next()){
                Sucursal sc = new Sucursal();
                sc.setId(lista.getInt(1));
                sc.setNombre(lista.getString(2));
                sc.setDireccion(lista.getString(3));
                sc.setComuna_id(lista.getInt(4));
                sc.setRetail_rut(lista.getString(5));
                sc.setNombreComuna(lista.getString(6));
                sc.setNombreRetail(lista.getString(7));
                list.add(sc);
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
    public void Agregar_Sucursal(Sucursal sc){
        Database cn = new Database();
        String sql = "INSERT INTO sucursal (id,nombre,direccion,comuna_id,retail_rut) VALUES(?,?,?,?,?)";
        PreparedStatement ps = null, coleccionSucursal;
        int ultimoId = 0;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            coleccionSucursal= cn.getConnection().prepareStatement("SELECT * FROM sucursal");   
                    ResultSet lista = coleccionSucursal.executeQuery();
                    while (lista.next())  //recorre
                    {
                     ultimoId=lista.getInt(1);
                    }
                    ultimoId=ultimoId+1;
            System.out.println("el ultimo id es:  "+ultimoId);
            ps.setInt(1, ultimoId);
            ps.setString(2, sc.getNombre());
            ps.setString(3, sc.getDireccion());
            ps.setInt(4, sc.getComuna_id());
            ps.setString(5, sc.getRetail_rut());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sucursal agregada correctamente");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Sucursal ya existe en el sistema", "Aviso", JOptionPane.ERROR_MESSAGE);
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
    public void Modificar_Sucursal(Sucursal sc){
        Database cn = new Database();
        String sql = "UPDATE sucursal SET nombre = ?, direccion = ?, comuna_id = ? , retail_rut = ? WHERE id = ?";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setString(1, sc.getNombre());
            ps.setString(2, sc.getDireccion());
            ps.setInt(3, sc.getComuna_id());
            ps.setString(4, sc.getRetail_rut());
            ps.setInt(5, sc.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sucursal modificada correctamente");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            //JOptionPane.showMessageDialog(null, "Usuario ya existe en el sistema", "Aviso", JOptionPane.ERROR_MESSAGE);
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
    public void Eliminar_Sucursal(Sucursal sc){
        Database cn = new Database();
        String sql = "DELETE FROM sucursal WHERE id = ?";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setInt(1, sc.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sucursal eliminada correctamente");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            //JOptionPane.showMessageDialog(null, "Usuario ya existe en el sistema", "Aviso", JOptionPane.ERROR_MESSAGE);
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

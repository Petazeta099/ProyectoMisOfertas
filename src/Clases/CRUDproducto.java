/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Conexion.Database;
import javax.swing.JOptionPane;

/**
 *
 * @author Raul
 */
public class CRUDproducto {
    
    public ArrayList<Producto> Listar_Producto(){
        ArrayList<Producto> list = new ArrayList<Producto>();
              //  ArrayList<Object> list2 = new ArrayList<Object>();

        Database cn = new Database();
        String sql = "select p.sku,p.nombre,p.descripcion,c.id,m.id,c.tipo,m.nombre from producto \n" +
"p INNER JOIN categoria c ON p.categoria_id = c.id INNER JOIN marca m ON m.id = p.marca_id";
        ResultSet lista = null;
        PreparedStatement ps = null;
        try{
           ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while(lista.next()){
                Producto pro = new Producto();
                pro.setSku(lista.getString(1));
                pro.setNombre(lista.getString(2));
                pro.setDescripcion(lista.getString(3));
                pro.setCategoria_id(lista.getInt(4));
                pro.setMarca_id(lista.getInt(5));
                pro.setTipo_cat(lista.getString(6));
                pro.setNombre_marca(lista.getString(7));
                list.add(pro);
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
    public void Agregar_Producto(Producto pro){
        Database cn = new Database();
        String sql = "INSERT INTO producto (sku, nombre, descripcion, categoria_id, marca_id) VALUES(?,?,?,?,?)";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setString(1, pro.getSku());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getDescripcion());
            ps.setInt(4, pro.getCategoria_id());
            ps.setInt(5, pro.getMarca_id());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Producto ya existe en el sistema", "Aviso", JOptionPane.ERROR_MESSAGE);
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
    public void Modificar_Producto(Producto pro){
       Database cn = new Database();
        String sql = "UPDATE producto SET nombre = ?, descripcion = ?, categoria_id = ?, marca_id = ? WHERE sku = ?";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setString(1, pro.getNombre());
            ps.setString(2, pro.getDescripcion());
            ps.setInt(3, pro.getCategoria_id());
            ps.setInt(4, pro.getMarca_id());
            ps.setString(5, pro.sku);
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
    public void Eliminar_Producto(Producto pro){
        Database cn = new Database();
        String sql = "DELETE FROM producto WHERE sku = ?";
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            ps.setString(1, pro.getSku());
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

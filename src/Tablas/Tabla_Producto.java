/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Clases.CRUDproducto;
import Clases.Producto;
import Clases.Categoria;
import Conexion.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raul
 */
public class Tabla_Producto {
    
    CRUDproducto crud_pro = null;

    public void visualizar_Producto(JTable tabla){
        
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };    
        dt.addColumn("SKU");
        dt.addColumn("NOMBRE");
        dt.addColumn("DESCRIPCION");
        dt.addColumn("CATEGORIA");
        dt.addColumn("MARCA");
        dt.addColumn("MODIFICAR");
        dt.addColumn("ELIMINAR");
        
        JButton btn_modificar = new JButton("MODIFICAR");
        btn_modificar.setName("mod");
        JButton btn_eliminar = new JButton("ELIMINAR");
        btn_eliminar.setName("eli");

        crud_pro = new CRUDproducto();
        Producto pro = new Producto();
        ArrayList<Producto> list = crud_pro.Listar_Producto();

        if(list.size() > 0){
            for(int i=0; i<list.size(); i++){
                Categoria c= new Categoria();
                
                Object fila[] = new Object[7];
                pro = list.get(i);
                c.setId(pro.getCategoria_id());
               // c = c.obtenerCategoria(pro.getCategoria_id());
               
                                      //  pro.setCat(c.obtenerCategoria(pro.getCategoria_id()));
                                        //System.out.println(c.obtenerCategoria(pro.getCategoria_id()));
                fila[0] = pro.getSku();
                fila[1] = pro.getNombre();
                fila[2] = pro.getDescripcion();
                fila[3] = pro.getTipo_cat();
                fila[4] = pro.getNombre_marca();
                System.out.println(obtenerCategoria(i));
                fila[5] = btn_modificar;
                fila[6] = btn_eliminar;
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(20);
        }
    }

    private String obtenerCategoria(int idCat){
    Database cn = new Database();
        String sql = "SELECT * FROM categoria WHERE id='" + idCat + "' LIMIT 1";
                        Categoria c = new Categoria();
            String h="hh";

        ResultSet lista = null;
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            
                c.setId(lista.getInt(1));
                c.setTipo(lista.getString(2));
                h= lista.getString(2);
            }catch(Exception ex){}
        return h;
}
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Clases.CRUDsucursal;
import Clases.Sucursal;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raul
 */
public class Tabla_Sucursal {
    
    CRUDsucursal crud_sc = null;

    public void visualizar_Sucursal(JTable tabla){
        
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };    
        dt.addColumn("ID");
        dt.addColumn("NOMBRE");
        dt.addColumn("DIRECCION");
        dt.addColumn("COMUNA");
        dt.addColumn("RETAIL");
        dt.addColumn("MODIFICAR");
        dt.addColumn("ELIMINAR");
        
        JButton btn_modificar = new JButton("MODIFICAR");
        btn_modificar.setName("mod");
        JButton btn_eliminar = new JButton("ELIMINAR");
        btn_eliminar.setName("eli");

        crud_sc = new CRUDsucursal();
        Sucursal sc = new Sucursal();
        ArrayList<Sucursal> list = crud_sc.Listar_Sucursal();

        if(list.size() > 0){
            for(int i=0; i<list.size(); i++){
                Object fila[] = new Object[7];
                sc = list.get(i);
                fila[0] = sc.getId();
                fila[1] = sc.getNombre();
                fila[2] = sc.getDireccion();
                fila[3] = sc.getNombreComuna();
                fila[4] = sc.getNombreRetail();
                //fila[3] = sc.getComuna_id();
                //fila[4] = sc.getRetail_rut();
                fila[5] = btn_modificar;
                fila[6] = btn_eliminar;
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(20);
        }
    }
    
}

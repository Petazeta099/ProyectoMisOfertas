/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Clases.CRUDretail;
import Clases.Retail;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raul
 */
public class Tabla_Retail {
    
    CRUDretail crud_rt = null;

    public void visualizar_Retail(JTable tabla){
        
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };    
        dt.addColumn("RUT");
        dt.addColumn("RAZON SOCIAL");
        dt.addColumn("MODIFICAR");
        dt.addColumn("ELIMINAR");
        
        JButton btn_modificar = new JButton("MODIFICAR");
        btn_modificar.setName("mod");
        JButton btn_eliminar = new JButton("ELIMINAR");
        btn_eliminar.setName("eli");

        crud_rt = new CRUDretail();
        Retail rt = new Retail();
        ArrayList<Retail> list = crud_rt.Listar_Retail();

        if(list.size() > 0){
            for(int i=0; i<list.size(); i++){
                Object fila[] = new Object[4];
                rt = list.get(i);
                fila[0] = rt.getRut();
                fila[1] = rt.getRazon_social();
                fila[2] = btn_modificar;
                fila[3] = btn_eliminar;
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(20);
        }
    }
    
}

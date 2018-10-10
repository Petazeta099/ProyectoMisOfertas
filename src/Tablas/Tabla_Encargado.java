/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Clases.CRUDencargado;
import Clases.Encargado;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raul
 */
public class Tabla_Encargado {
    
    CRUDencargado crud_enc = null;

    public void visualizar_Encargado(JTable tabla){
        
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };    
        dt.addColumn("RUT");
        dt.addColumn("P. NOMBRE");
        dt.addColumn("S. NOMBRE");
        dt.addColumn("APELLIDO P.");
        dt.addColumn("APELLIDO M.");
        dt.addColumn("CLAVE");
        dt.addColumn("ACTIVO");
        dt.addColumn("RETAIL RUT");
        dt.addColumn("MODIFICAR");
        dt.addColumn("ELIMINAR");
        
        JButton btn_modificar = new JButton("MODIFICAR");
        btn_modificar.setName("mod");
        JButton btn_eliminar = new JButton("ELIMINAR");
        btn_eliminar.setName("eli");

        crud_enc = new CRUDencargado();
        Encargado enc = new Encargado();
        ArrayList<Encargado> list = crud_enc.Listar_Encargado();

        if(list.size() > 0){
            for(int i=0; i<list.size(); i++){
                Object fila[] = new Object[10];
                enc = list.get(i);
                fila[0] = enc.getRun();
                fila[1] = enc.getP_nombre();
                fila[2] = enc.getS_nombre();
                fila[3] = enc.getApellido_p();
                fila[4] = enc.getApellido_m();
                fila[5] = enc.getClave();
                fila[6] = enc.getActivo();
                fila[7] = enc.getRetail_rut();
                fila[8] = btn_modificar;
                fila[9] = btn_eliminar;
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(20);
        }
    }
    
}

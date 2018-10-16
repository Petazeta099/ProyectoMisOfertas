/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Clases.CRUDconsumidor;
import Clases.Consumidor;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raul
 */
public class Tabla_Consumidor {
    
    CRUDconsumidor crud_cons = null;

    public void visualizar_Consumidor(JTable tabla){
        
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
        dt.addColumn("EMAIL");
        dt.addColumn("CLAVE");
        dt.addColumn("PUNTAJE");
        dt.addColumn("ACTIVO");
        dt.addColumn("SUSCRITO");
        dt.addColumn("COMUNA");
        dt.addColumn("MODIFICAR");
        dt.addColumn("ELIMINAR");
        
        JButton btn_modificar = new JButton("MODIFICAR");
        btn_modificar.setName("mod");
        JButton btn_eliminar = new JButton("ELIMINAR");
        btn_eliminar.setName("eli");

        crud_cons = new CRUDconsumidor();
        Consumidor cons = new Consumidor();
        ArrayList<Consumidor> list = crud_cons.Listar_Consumidor();

        if(list.size() > 0){
            for(int i=0; i<list.size(); i++){
                Object fila[] = new Object[13];
                cons = list.get(i);
                fila[0] = cons.getRun();
                fila[1] = cons.getP_nombre();
                fila[2] = cons.getS_nombre();
                fila[3] = cons.getApellido_p();
                fila[4] = cons.getApellido_m();
                fila[5] = cons.getEmail();
                fila[6] = cons.getClave();
                fila[7] = cons.getPuntaje();
                String act = ""+cons.getActivo();
                if(act.equals("1")){
                    fila[8]="Si";
                }else{
                    fila[8]="No";
                }
                //fila[8] = cons.getActivo();
                String sus = ""+cons.getSuscrito();
                if(sus.equals("1")){
                    fila[9]="Si";
                }else{
                    fila[9]="No";
                }
                //fila[9] = cons.getSuscrito();
                fila[10] = cons.getComuna_id();
                fila[11] = btn_modificar;
                fila[12] = btn_eliminar;
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(20);
        }
    }
    
}

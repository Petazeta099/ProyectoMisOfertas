/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Clases.CRUDoferta;
import Clases.Oferta;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raul
 */
public class Tabla_Ofertas {

    CRUDoferta crud_of = null;

    public void visualizar_Oferta(JTable tabla){
        
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel dt = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };    
        dt.addColumn("ID");
        dt.addColumn("TITULO");
        dt.addColumn("DESCRIPCION");
        dt.addColumn("F. INICIO");
        dt.addColumn("F. TERMINO");
        dt.addColumn("P. NORMAL");
        dt.addColumn("P. OFERTA");
        dt.addColumn("C. MINIMA.");
        dt.addColumn("C. MAXIMA");
        dt.addColumn("ACTIVA");
        dt.addColumn("PUBLICAR");
        
        JButton btn_publicar = new JButton("PUBLICAR");
        btn_publicar.setName("pub");

        crud_of = new CRUDoferta();
        Oferta of = new Oferta();
        ArrayList<Oferta> list = crud_of.Listar_Oferta();

        if(list.size() > 0){
            for(int i=0; i<list.size(); i++){
                Object fila[] = new Object[11];
                of = list.get(i);
                fila[0] = of.getId();
                fila[1] = of.getTitulo();
                fila[2] = of.getDescripcion();
                fila[3] = of.getFecha_inicio();
                fila[4] = of.getFecha_termino();
                fila[5] = of.getPrecio_normal();
                fila[6] = of.getPrecio_oferta();
                fila[7] = of.getCompra_min();
                fila[8] = of.getCompra_max();
                fila[9] = of.getActiva();
                fila[10] = btn_publicar;
                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(20);
        }
    }
    
}

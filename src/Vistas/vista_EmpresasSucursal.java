/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.CRUDsucursal;
import Clases.Comuna;
import Clases.Limpiar;
import Clases.Retail;
import Clases.Sucursal;
import Conexion.Database;
import Tablas.Tabla_Sucursal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Raul
 */
public class vista_EmpresasSucursal extends javax.swing.JFrame {

    static home login = new home();
    
    Tabla_Sucursal ts = new Tabla_Sucursal();    
    Limpiar lim = new Limpiar();    
    CRUDsucursal crud_sc;
    int clic_tabla = 0;
    /**
     * Creates new form vista_EmpresasSucursal
     */
    public vista_EmpresasSucursal() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        
        setResizable(false);
        ts.visualizar_Sucursal(tab_sucursal);
        visibilidadModificacion(false,false,true);
        Comuna comuna = new Comuna();
        comuna.mostrarComuna(cb_comuna);
        Retail retail = new Retail();
        retail.mostrarRetail(cb_retail);
                
        activa_boton(true,false,false,true);
        tab_sucursal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public void agregar(){
        crud_sc = new CRUDsucursal();
        Sucursal sc = new Sucursal();
        
        sc.setNombre(txt_nombre.getText());
        sc.setDireccion(txt_direccion.getText());
        sc.setComuna_id(Integer.parseInt(lbl_comuna_id.getText()));
        sc.setRetail_rut(lbl_rutRetail.getText());
        
        crud_sc.Agregar_Sucursal(sc);
    }
    
    //Metodo modificar sucursal
    public void modificar(){
        crud_sc = new CRUDsucursal();
        Sucursal sc = new Sucursal();
        
        sc.setNombre(txt_nombre.getText());
        sc.setDireccion(txt_direccion.getText());
        sc.setComuna_id(Integer.parseInt(lbl_comuna_id.getText()));
        sc.setRetail_rut(lbl_rutRetail.getText());
        sc.setId(Integer.parseInt(lbl_id.getText()));
        
        crud_sc.Modificar_Sucursal(sc);
    }
    
    //Metodo eliminar sucursal
    public void eliminar(){
        crud_sc = new CRUDsucursal();
        Sucursal sc = new Sucursal();
        
        sc.setId(Integer.parseInt(lbl_id.getText()));
        
        crud_sc.Eliminar_Sucursal(sc);
    }
    
    public void activa_boton(boolean a1, boolean a2, boolean a3, boolean a4){
        btn_agregar.setEnabled(a1);
        btn_modificar.setEnabled(a2);
        btn_eliminar.setEnabled(a3);
//        txt_id.setEnabled(a4);
    }
    
    public int obtenerIdComunaConsumidor(String nombreComuna){
         ArrayList<String> list = new ArrayList<String>();
         int[] numCom = new int[1];
              //  ArrayList<Object> list2 = new ArrayList<Object>();
        
        Database cn = new Database();
        String sql = "select id from comuna where nombre like '" + nombreComuna + "'"; //scar numero
        ResultSet lista = null;
        PreparedStatement ps = null;
        int idCom=0;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while(lista.next()){
                numCom[0]=lista.getInt(1);
                idCom = numCom[0];
                list.add(lista.getString(1));
                //idCom = lista.getInt(1);  //entrega el id
                // System.out.println(idCom);
                //cb_comuna.setSelectedIndex(idCom);
               // list2.add(o);
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
        System.out.println(idCom);
       return idCom;
    }
    
    public String obtenerRutRetailEncargado(String nombreRetail){
         ArrayList<String> list = new ArrayList<String>();
         String[] rrtl = new String[1];
              //  ArrayList<Object> list2 = new ArrayList<Object>();
        
        Database cn = new Database();
        String sql = "select rut from retail where razon_social like '" + nombreRetail + "'"; //scar numero
        ResultSet lista = null;
        PreparedStatement ps = null;
        String rutRet="";
        try{
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while(lista.next()){
                rrtl[0]=lista.getString("rut");
                rutRet = rrtl[0];
                //idCom = lista.getInt(1);  //entrega el id
                // System.out.println(idCom);
                //cb_comuna.setSelectedIndex(idCom);
               // list2.add(o);
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
       // System.out.println(rutRet);
       return rutRet;
    }
    
     public int entregarPosicionRetail(String rutRetail){
         
        ArrayList<String> list = new ArrayList<String>();
         String[] rrtl = new String[1];
              //  ArrayList<Object> list2 = new ArrayList<Object>();
        
        Database cn = new Database();
        String sql = "select rut from retail order by razon_social asc"; //selecciona todos los registros
        ResultSet lista = null;
        PreparedStatement ps = null;
        int posicion = 1;
        int prtl = 0;
        String nombre="";
        try{
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while(lista.next()){
                nombre=lista.getString("rut");
                if(nombre.equals(rutRetail)){
                    prtl = prtl+posicion;
                }else{
                    prtl = prtl+0;
                }
                posicion++;
                //idCom = lista.getInt(1);  //entrega el id
                System.out.println(prtl);
                //cb_comuna.setSelectedIndex(idCom);
               // list2.add(o);
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
       // System.out.println(rutRet);
       return prtl;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        btn_agregar = new javax.swing.JButton();
        lbl_clave = new javax.swing.JLabel();
        lbl_apellidos = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        lbl_nombres = new javax.swing.JLabel();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        txt_direccion = new javax.swing.JTextField();
        lbl_retail = new javax.swing.JLabel();
        cb_retail = new javax.swing.JComboBox<>();
        lbl_rutRetail = new javax.swing.JLabel();
        cb_comuna = new javax.swing.JComboBox<>();
        lbl_comuna_id = new javax.swing.JLabel();
        lbl_id = new javax.swing.JLabel();
        lbIdMod = new javax.swing.JLabel();
        txtIdMod = new javax.swing.JTextField();
        btn_salir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_sucursal = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.setBackground(new java.awt.Color(204, 204, 204));

        btn_agregar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        lbl_clave.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_clave.setText("Comuna:");

        lbl_apellidos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_apellidos.setText("Direccion:");

        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });
        txt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombreKeyTyped(evt);
            }
        });

        lbl_nombres.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_nombres.setText("Nombre:");

        btn_modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });

        btn_eliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_limpiar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_limpiar.setText("Limpiar");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        txt_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_direccionKeyTyped(evt);
            }
        });

        lbl_retail.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_retail.setText("Retail");

        cb_retail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_retailActionPerformed(evt);
            }
        });

        cb_comuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_comunaActionPerformed(evt);
            }
        });

        lbIdMod.setText("Id de sucursal a modificar:");

        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap(26, Short.MAX_VALUE)
                        .addComponent(lbl_comuna_id, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(lbl_id)
                        .addGap(577, 577, 577))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_apellidos)
                                            .addComponent(lbl_clave)
                                            .addComponent(lbl_retail))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelLayout.createSequentialGroup()
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cb_retail, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cb_comuna, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(259, 259, 259))
                                            .addComponent(txt_direccion)))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(lbl_nombres)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_nombre))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_agregar)
                                    .addComponent(btn_modificar)
                                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btn_limpiar)
                                        .addComponent(btn_eliminar))))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(lbIdMod)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIdMod, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 399, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(lbl_rutRetail))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(lbl_rutRetail))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbIdMod)
                            .addComponent(txtIdMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_nombres)
                            .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_agregar))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_apellidos)
                            .addComponent(txt_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_modificar))
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_clave)
                                    .addComponent(cb_comuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_retail)
                                    .addComponent(cb_retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(btn_eliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_limpiar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_id)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_comuna_id, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(btn_salir)
                .addGap(25, 25, 25))
        );

        tab_sucursal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tab_sucursal.getTableHeader().setReorderingAllowed(false);
        tab_sucursal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_sucursalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tab_sucursal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        int errores=0;
        if(txt_nombre.getText().equals("") || txt_direccion.getText().equals("")){
            errores = errores+1;
        }
        
        if(errores>=1){
            JOptionPane.showMessageDialog(null, "Ingrese informacion faltante en el formulario", "Aviso", JOptionPane.ERROR_MESSAGE);
        }else{
        try {
                agregar();
            } catch (Exception ex) {
                Logger.getLogger(vista_Consumidores.class.getName()).log(Level.SEVERE, null, ex);
            }
        ts.visualizar_Sucursal(tab_sucursal);
        activa_boton(true,false,false,true);
        lim.limpiar_texto(panel);
        }
        
        
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int errores=0;
        if(txt_nombre.getText().equals("") || txt_direccion.getText().equals("")){
            errores = errores+1;
        }
        
        if(errores>=1){
            JOptionPane.showMessageDialog(null, "Ingrese informacion faltante en el formulario", "Aviso", JOptionPane.ERROR_MESSAGE);
        }else{
        try {
                modificar();
            } catch (Exception ex) {
                Logger.getLogger(vista_Consumidores.class.getName()).log(Level.SEVERE, null, ex);
            }
        ts.visualizar_Sucursal(tab_sucursal);
        activa_boton(true,false,false,false);
        lim.limpiar_texto(panel);
        visibilidadModificacion(false,false,false);
        }
        
        
        
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed

        int s = JOptionPane.showConfirmDialog(null, "Eliminar sucursal","Si/no",JOptionPane.YES_NO_OPTION);
        if(s == 0){
            eliminar();
            ts.visualizar_Sucursal(tab_sucursal);
            activa_boton(true,false,false,true);
            lim.limpiar_texto(panel);
            visibilidadModificacion(false,false,false);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        lim.limpiar_texto(panel);
        activa_boton(true,false,false,true);
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void cb_retailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_retailActionPerformed
        lbl_rutRetail.hide();
        lbl_rutRetail.setText(cb_retail.getItemAt(cb_retail.getSelectedIndex()).getRut());
    }//GEN-LAST:event_cb_retailActionPerformed

    private void tab_sucursalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_sucursalMouseClicked
        lbl_id.hide();
        clic_tabla = this.tab_sucursal.rowAtPoint(evt.getPoint());

        int id = (int)tab_sucursal.getValueAt(clic_tabla, 0);
        String nombre = ""+tab_sucursal.getValueAt(clic_tabla, 1);
        String direccion = ""+tab_sucursal.getValueAt(clic_tabla, 2);
        String comuna = ""+tab_sucursal.getValueAt(clic_tabla, 3);
        String razon_social = ""+tab_sucursal.getValueAt(clic_tabla, 4);
        
        
     //   int comuna = comuna_id;

        lbl_id.setText(""+id);
        txt_nombre.setText(nombre);
        txt_direccion.setText(direccion);
        cb_comuna.setSelectedIndex(obtenerIdComunaConsumidor(comuna)-1);
        cb_retail.setSelectedIndex(entregarPosicionRetail(obtenerRutRetailEncargado(razon_social))-1);
        txtIdMod.setText(Integer.toString(id));
        
        /*
        int id = (int)tab_sucursal.getValueAt(clic_tabla, 0);
        String nombre = ""+tab_sucursal.getValueAt(clic_tabla, 1);
        String direccion = ""+tab_sucursal.getValueAt(clic_tabla, 2);
        int comuna_id = (int)tab_sucursal.getValueAt(clic_tabla, 3);
        String razon_social = ""+tab_sucursal.getValueAt(clic_tabla, 4);
        
        
        int comuna = comuna_id;

        lbl_id.setText(""+id);
        txt_nombre.setText(nombre);
        txt_direccion.setText(direccion);
        cb_comuna.setSelectedIndex(comuna-1);
        cb_retail.setSelectedIndex(1);
        */

        int column = tab_sucursal.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tab_sucursal.getRowHeight();

        if(row < tab_sucursal.getRowCount() && row >= 0 && column < tab_sucursal.getColumnCount() && column >= 0){
            Object value = tab_sucursal.getValueAt(row, column);
            if(value instanceof JButton){
                ((JButton)value).doClick();
                JButton boton = (JButton) value;

                if(boton.getName().equals("mod")){
                    System.out.println("Click en el boton modificar");
                    //EVENTOS MODIFICAR
                    activa_boton(true,true,false,false);
                    visibilidadModificacion(true,true,false);
                }
                if(boton.getName().equals("eli")){
                    //JOptionPane.showConfirmDialog(null, "Desea eliminar este registro", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
                    System.out.println("Click en el boton eliminar");
                    //EVENTOS ELIMINAR
                    activa_boton(true,false,true,true);
                    visibilidadModificacion(true,true,false);
                }
            }

        }
    }//GEN-LAST:event_tab_sucursalMouseClicked

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        new vista_Admin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void cb_comunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_comunaActionPerformed
        lbl_comuna_id.hide();
        lbl_comuna_id.setText(""+cb_comuna.getItemAt(cb_comuna.getSelectedIndex()).getId());
    }//GEN-LAST:event_cb_comunaActionPerformed

    private void txt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyTyped
        
        int maximoCaracter = 29;
        char validarCaracter=evt.getKeyChar();
        if(txt_nombre.getText().length()>=maximoCaracter ){
            evt.consume();
        }
        
    }//GEN-LAST:event_txt_nombreKeyTyped

    private void txt_direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_direccionKeyTyped
        int maximoCaracter = 99;
        char validarCaracter=evt.getKeyChar();
        if(txt_direccion.getText().length()>=maximoCaracter ){
            evt.consume();
        }
    }//GEN-LAST:event_txt_direccionKeyTyped

    public void visibilidadModificacion(boolean visi1,boolean visi2,boolean visi3){
    lbIdMod.setVisible(visi1);
    txtIdMod.setVisible(visi2);
    txtIdMod.setEnabled(visi3);
    }
    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JComboBox<Comuna> cb_comuna;
    private javax.swing.JComboBox<Retail> cb_retail;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbIdMod;
    private javax.swing.JLabel lbl_apellidos;
    private javax.swing.JLabel lbl_clave;
    private javax.swing.JLabel lbl_comuna_id;
    private javax.swing.JLabel lbl_id;
    private javax.swing.JLabel lbl_nombres;
    private javax.swing.JLabel lbl_retail;
    private javax.swing.JLabel lbl_rutRetail;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tab_sucursal;
    private javax.swing.JTextField txtIdMod;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables
}

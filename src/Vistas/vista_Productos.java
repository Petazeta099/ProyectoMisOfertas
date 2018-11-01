/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.CRUDproducto;
import Conexion.Database;
import Clases.Limpiar;
import Clases.Producto;
import Tablas.Tabla_Producto;
import Tablas.Tabla_Producto;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raul
 */
public class vista_Productos extends javax.swing.JFrame {
    static home login = new home();
    
  
   // 
    Tabla_Producto tp = new Tabla_Producto();    
    Limpiar lim = new Limpiar();    
    CRUDproducto crud_pro;
    int clic_tabla = 0;
    boolean skuCorrecto=false;
    //
    
    
    /**
     * Creates new form listaProductos
     */
    public vista_Productos() {
        initComponents();
         listaCategorias();
        listaMarcas();
        tp.visualizar_Producto(tab_producto);
        //obtenerProductos();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        
        setResizable(false);
        activa_boton(true,false,false,true);
        tab_producto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
   
    
    
    

    
    
    public void agregar(){
        crud_pro = new CRUDproducto();
        Producto pro = new Producto();
        
        pro.setSku(txt_sku.getText());
        pro.setNombre(txt_nombre.getText());
        pro.setDescripcion(txt_descripcion.getText());
        pro.setCategoria_id(txt_categoria.getSelectedIndex()+1);
        pro.setMarca_id(txt_marca.getSelectedIndex()+1);
        
        crud_pro.Agregar_Producto(pro);
        JOptionPane.showMessageDialog(null, "El producto se a agregado correctamente");
    }
    
    //Metodo eliminar producto
    public void modificar(){
        int errores=0;
        if(txt_nombre.getText().equals("") || txt_descripcion.getText().equals("") ||  txt_sku.getText().equals("")){
            errores = errores+1;
        }
        
        if(errores>=1){
        JOptionPane.showMessageDialog(null, "Ingrese informacion faltante en el formulario", "Aviso", JOptionPane.ERROR_MESSAGE);
        }else{        
        crud_pro = new CRUDproducto();
        Producto pro = new Producto();
        
        pro.setNombre(txt_nombre.getText());
        pro.setDescripcion(txt_descripcion.getText());
        pro.setCategoria_id(txt_categoria.getSelectedIndex()+1);
        pro.setMarca_id(txt_marca.getSelectedIndex()+1);
        pro.setSku(txt_sku.getText());
        
        crud_pro.Modificar_Producto(pro);
        JOptionPane.showMessageDialog(null, "El producto se a modificado correctamente");
        }
    }
    
    //Metodo eliminar producto
    public void eliminar(){
        crud_pro = new CRUDproducto();
        Producto pro = new Producto();
        
        pro.setSku(txt_sku.getText());
        
        crud_pro.Eliminar_Producto(pro);
        JOptionPane.showMessageDialog(null, "El producto se a eliminado correctamente");
    }
    
    public void activa_boton(boolean a1, boolean a2, boolean a3, boolean a4){
        btn_agregar.setEnabled(a1);
        btn_modificar.setEnabled(a2);
        btn_eliminar.setEnabled(a3);
        txt_sku.setEnabled(a4);
    }
    
    
    public void obtenerProductos(){
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet lista = Database.crearConsulta("SELECT * FROM PRODUCTO");
        modelo.setColumnIdentifiers(new Object[]{"SKU","NOMBRE","DESCRIPCION","CATEGORIA","MARCA"});
        
        try{
            while(lista.next()){
                modelo.addRow(new Object[]{lista.getString("sku"),lista.getString("nombre"),lista.getString("descripcion"),lista.getInt("categoria_id"),lista.getInt("marca_id")});
            }
            tab_producto.setModel(modelo);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
     public void listaCategorias(){
         ArrayList<String> list = new ArrayList<String>();
              //  ArrayList<Object> list2 = new ArrayList<Object>();

        Database cn = new Database();
        String sql = "select tipo from categoria";
        ResultSet lista = null;
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while(lista.next()){
                list.add(lista.getString(1));
                String n = lista.getString("tipo");
                txt_categoria.addItem(lista.getString(1));
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
       
    }
    
        public void listaMarcas(){
         ArrayList<String> list = new ArrayList<String>();
              //  ArrayList<Object> list2 = new ArrayList<Object>();

        Database cn = new Database();
        String sql = "select nombre from marca";
        ResultSet lista = null;
        PreparedStatement ps = null;
        try{
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while(lista.next()){
                list.add(lista.getString(1));
                String n = lista.getString("nombre");
                txt_marca.addItem(lista.getString(1));
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
       
    }

     public int obtenerIdCategoriaConsumidor(String nombreCategoria) {
        ArrayList<String> list = new ArrayList<String>();
        int[] numCat = new int[1];
        //  ArrayList<Object> list2 = new ArrayList<Object>();

        Database cn = new Database();
        String sql = "select id from categoria where tipo like '" + nombreCategoria + "'"; //scar numero
        ResultSet lista = null;
        PreparedStatement ps = null;
        int idCat = 0;
        try {
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while (lista.next()) {
                numCat[0] = lista.getInt(1);
                idCat = numCat[0];
                list.add(lista.getString(1));
                //idCom = lista.getInt(1);  //entrega el id
                // System.out.println(idCom);
                //cb_comuna.setSelectedIndex(idCom);
                // list2.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                lista.close();
                cn.desconectar();
            } catch (Exception ex) {
            }
        }
        System.out.println(idCat);
        return idCat;
    }
     
     public int obtenerIdMarca(String nombreMarca) {
        ArrayList<String> list = new ArrayList<String>();
        int[] numMarca = new int[1];
        //  ArrayList<Object> list2 = new ArrayList<Object>();

        Database cn = new Database();
        String sql = "select id from marca where nombre like '" + nombreMarca + "'"; //scar numero
        ResultSet lista = null;
        PreparedStatement ps = null;
        int idMar = 0;
        try {
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while (lista.next()) {
                numMarca[0] = lista.getInt(1);
                idMar = numMarca[0];
                list.add(lista.getString(1));
                //idCom = lista.getInt(1);  //entrega el id
                // System.out.println(idCom);
                //cb_comuna.setSelectedIndex(idCom);
                // list2.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                lista.close();
                cn.desconectar();
            } catch (Exception ex) {
            }
        }
        System.out.println(idMar);
        return idMar;
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tab_producto = new javax.swing.JTable();
        panel = new javax.swing.JPanel();
        btn_agregar = new javax.swing.JButton();
        lbl_categoria = new javax.swing.JLabel();
        lbl_desc = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        txt_sku = new javax.swing.JTextField();
        lbl_sku = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        lbl_marca = new javax.swing.JLabel();
        txt_categoria = new javax.swing.JComboBox<>();
        txt_marca = new javax.swing.JComboBox<>();
        btn_salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Productos");

        tab_producto.setModel(new javax.swing.table.DefaultTableModel(
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
        tab_producto.getTableHeader().setReorderingAllowed(false);
        tab_producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_productoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tab_producto);

        btn_agregar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        lbl_categoria.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_categoria.setText("Categoria:");

        lbl_desc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_desc.setText("Descripcion:");

        txt_descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_descripcionKeyTyped(evt);
            }
        });

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

        txt_sku.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_skuFocusLost(evt);
            }
        });
        txt_sku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_skuKeyTyped(evt);
            }
        });

        lbl_sku.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_sku.setText("SKU:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nombre:");

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

        lbl_marca.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_marca.setText("Marca:");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_sku)
                            .addComponent(jLabel2))
                        .addGap(31, 31, 31)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_sku)
                            .addComponent(txt_nombre)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_desc)
                            .addComponent(lbl_categoria)
                            .addComponent(lbl_marca))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_descripcion)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(btn_agregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_limpiar)
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_sku)
                    .addComponent(txt_sku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_desc)
                    .addComponent(txt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_categoria)
                    .addComponent(txt_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_marca)
                    .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_agregar)
                    .addComponent(btn_modificar)
                    .addComponent(btn_eliminar)
                    .addComponent(btn_limpiar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_salir))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btn_salir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        int errores=0;
        
        String categoria = txt_categoria.getSelectedItem()+"";
        String marca = txt_marca.getSelectedItem()+"";
        
        if(txt_nombre.getText().equals("") || txt_descripcion.getText().equals("") || categoria.equals("")
           || marca.equals("") || txt_sku.getText().equals("") || skuCorrecto==false){
            errores = errores+1;
        }        
        if(errores>=1){
            JOptionPane.showMessageDialog(null, "Ingrese informacion faltante en el formulario", "Aviso", JOptionPane.ERROR_MESSAGE);
        }else{
        agregar();
        tp.visualizar_Producto(tab_producto);
        activa_boton(true,false,false,true);
        lim.limpiar_texto(panel);
        }
        
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int errores=0;
        if(txt_nombre.getText().equals("") || txt_descripcion.getText().equals("") || txt_sku.getText().equals("")){
            errores = errores+1;
        }        
        if(errores>=1){
            JOptionPane.showMessageDialog(null, "Ingrese informacion faltante en el formulario", "Aviso", JOptionPane.ERROR_MESSAGE);
        }else{
        modificar();
        tp.visualizar_Producto(tab_producto);
        activa_boton(true,false,false,false);
        lim.limpiar_texto(panel);
        }
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed

        int s = JOptionPane.showConfirmDialog(null, "Eliminar producto","Si/no",JOptionPane.YES_NO_OPTION);
        if(s == 0){
            eliminar();
            tp.visualizar_Producto(tab_producto);
            activa_boton(true,false,false,true);
            lim.limpiar_texto(panel);
        }

    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        lim.limpiar_texto(panel);
        activa_boton(true,false,false,true);
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void tab_productoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_productoMouseClicked
          clic_tabla = this.tab_producto.rowAtPoint(evt.getPoint());
        
        String sku = ""+tab_producto.getValueAt(clic_tabla, 0);
        String nombre = ""+tab_producto.getValueAt(clic_tabla, 1);
        String descripcion = ""+tab_producto.getValueAt(clic_tabla, 2);
        String categoria = ""+tab_producto.getValueAt(clic_tabla, 3);
        String marca = ""+tab_producto.getValueAt(clic_tabla, 4);
        
        txt_sku.setText(sku);
        txt_nombre.setText(nombre);
        txt_descripcion.setText(descripcion);
        txt_categoria.setSelectedIndex(obtenerIdCategoriaConsumidor(categoria)-1);
        txt_marca.setSelectedIndex(obtenerIdMarca(marca)-1);
        
        int column = tab_producto.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tab_producto.getRowHeight();
        
        if(row < tab_producto.getRowCount() && row >= 0 && column < tab_producto.getColumnCount() && column >= 0){
            Object value = tab_producto.getValueAt(row, column);
            if(value instanceof JButton){
                ((JButton)value).doClick();
                JButton boton = (JButton) value;

                if(boton.getName().equals("mod")){
                    System.out.println("Click en el boton modificar");
                    //EVENTOS MODIFICAR
                    activa_boton(true,true,false,false);
                }
                if(boton.getName().equals("eli")){
                    //JOptionPane.showConfirmDialog(null, "Desea eliminar este registro", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
                    System.out.println("Click en el boton eliminar");
                    //EVENTOS ELIMINAR
                    activa_boton(true,false,true,true);
                }
            }

        }
    }//GEN-LAST:event_tab_productoMouseClicked

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        new vista_Admin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void txt_skuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_skuKeyTyped
        int maximoCaracter = 14;
        char validarCaracter=evt.getKeyChar();
        if((txt_sku.getText().length()>=maximoCaracter) ){
            evt.consume();
        }
    }//GEN-LAST:event_txt_skuKeyTyped

    private void txt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyTyped
        int maximoCaracter = 29;
        char validarCaracter=evt.getKeyChar();
        if((txt_nombre.getText().length()>=maximoCaracter) ){
            evt.consume();
        }
    }//GEN-LAST:event_txt_nombreKeyTyped

    private void txt_descripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descripcionKeyTyped
        int maximoCaracter = 29;
        char validarCaracter=evt.getKeyChar();
        if((txt_descripcion.getText().length()>=maximoCaracter) ){
            evt.consume();
        }
    }//GEN-LAST:event_txt_descripcionKeyTyped

    private void txt_skuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_skuFocusLost
        int minimo=4;
        if(txt_sku.getText().length()<minimo){
            txt_sku.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, "Ingrese sku", "Aviso", JOptionPane.ERROR_MESSAGE);
            skuCorrecto=false;
        }else{
            txt_sku.setForeground(Color.black);
            skuCorrecto=true;
        }
    }//GEN-LAST:event_txt_skuFocusLost

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_categoria;
    private javax.swing.JLabel lbl_desc;
    private javax.swing.JLabel lbl_marca;
    private javax.swing.JLabel lbl_sku;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tab_producto;
    private javax.swing.JComboBox<String> txt_categoria;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JComboBox<String> txt_marca;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_sku;
    // End of variables declaration//GEN-END:variables
}

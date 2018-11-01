/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.CRUDretail;
import Clases.Limpiar;
import Clases.Retail;
import Tablas.Tabla_Retail;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Raul
 */
public class vista_EmpresasRetail extends javax.swing.JFrame {

    static home login = new home();
    
    Tabla_Retail rt = new Tabla_Retail();    
    Limpiar lim = new Limpiar();    
    CRUDretail crud_rt;
    int clic_tabla = 0;
    boolean rutCorrecto=false;
    /**
     * Creates new form vista_Empresas
     */
    public vista_EmpresasRetail() {
        initComponents();
        rt.visualizar_Retail(tab_retail);
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        
        setResizable(false);
        //obtenerProductos();
        activa_boton(true,false,false,true);
        tab_retail.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public void agregar(){
        
        int errores=0;
        if(txt_rut.getText().equals("") || txt_razonSocial.getText().equals("") || rutCorrecto==false){
            errores = errores+1;
        }
        
        if(errores>=1){
            JOptionPane.showMessageDialog(null, "Ingrese informacion faltante en el formulario", "Aviso", JOptionPane.ERROR_MESSAGE);
        }else{
            crud_rt = new CRUDretail();
        Retail rt = new Retail();
        
        String rutsinDigito = txt_rut.getText().substring(0,txt_rut.getText().length()-1);
        String rutDigito =txt_rut.getText().substring(txt_rut.getText().length()-1);  
         String rutFinal = rutsinDigito+"-"+rutDigito;
        
        rt.setRut(rutFinal);
        rt.setRazon_social(txt_razonSocial.getText());
        
        crud_rt.Agregar_Retail(rt);
        }
        
        
    }
    
    //Metodo eliminar producto
    public void modificar(){
        int errores=0;
        if(txt_rut.getText().equals("") || txt_razonSocial.getText().equals("")){
            errores = errores+1;
        }
        
        if(errores>=1){
            JOptionPane.showMessageDialog(null, "Ingrese informacion faltante en el formulario", "Aviso", JOptionPane.ERROR_MESSAGE);
        }else{
        crud_rt = new CRUDretail();
        Retail rt = new Retail();
        
        String rutsinDigito = txt_rut.getText().substring(0,txt_rut.getText().length()-1);
        String rutDigito =txt_rut.getText().substring(txt_rut.getText().length()-1);  
        String rutFinal = rutsinDigito+"-"+rutDigito;
        
        rt.setRazon_social(txt_razonSocial.getText());
        rt.setRut(rutFinal);
        
        crud_rt.Modificar_Retail(rt);
        }
        
    }
    
    //Metodo eliminar producto
    public void eliminar(){
        crud_rt = new CRUDretail();
        Retail rt = new Retail();
        String rutsinDigito = txt_rut.getText().substring(0,txt_rut.getText().length()-1);
        String rutDigito =txt_rut.getText().substring(txt_rut.getText().length()-1);  
        String rutFinal = rutsinDigito+"-"+rutDigito;
        rt.setRut(rutFinal);
        crud_rt.Eliminar_Retail(rt);
    }
    
    public void activa_boton(boolean a1, boolean a2, boolean a3, boolean a4){
        btn_agregar.setEnabled(a1);
        btn_modificar.setEnabled(a2);
        btn_eliminar.setEnabled(a3);
        txt_rut.setEnabled(a4);

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
        tab_retail = new javax.swing.JTable();
        panel = new javax.swing.JPanel();
        btn_agregar = new javax.swing.JButton();
        txt_razonSocial = new javax.swing.JTextField();
        txt_rut = new javax.swing.JTextField();
        lbl_rut = new javax.swing.JLabel();
        lbl_razonSocial = new javax.swing.JLabel();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        lbl_rutRetail = new javax.swing.JLabel();
        btn_salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tab_retail.setModel(new javax.swing.table.DefaultTableModel(
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
        tab_retail.getTableHeader().setReorderingAllowed(false);
        tab_retail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_retailMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tab_retail);

        btn_agregar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        txt_razonSocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_razonSocialActionPerformed(evt);
            }
        });
        txt_razonSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_razonSocialKeyTyped(evt);
            }
        });

        txt_rut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_rutFocusLost(evt);
            }
        });
        txt_rut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_rutKeyTyped(evt);
            }
        });

        lbl_rut.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_rut.setText("RUT:");

        lbl_razonSocial.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_razonSocial.setText("Razon Social:");

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
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_rut)
                    .addComponent(lbl_razonSocial))
                .addGap(10, 10, 10)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_razonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                        .addComponent(txt_rut)))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(btn_salir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_rutRetail))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_modificar)
                            .addComponent(btn_eliminar))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_rut)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_agregar)))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_razonSocial)
                    .addComponent(txt_razonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_modificar))
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(lbl_rutRetail)
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_eliminar)
                            .addComponent(btn_limpiar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_salir))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tab_retailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_retailMouseClicked
        clic_tabla = this.tab_retail.rowAtPoint(evt.getPoint());

        String rut = ""+tab_retail.getValueAt(clic_tabla, 0);
        String razon_social = ""+tab_retail.getValueAt(clic_tabla, 1);

        String rutAcortado = rut.substring( 0, rut.indexOf("-"));
        String digitoRut = rut.substring(rut.length() - 1);
        

        txt_rut.setText(rutAcortado+digitoRut);
        txt_razonSocial.setText(razon_social);

        int column = tab_retail.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tab_retail.getRowHeight();

        if(row < tab_retail.getRowCount() && row >= 0 && column < tab_retail.getColumnCount() && column >= 0){
            Object value = tab_retail.getValueAt(row, column);
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
                    activa_boton(true,false,true,false);
                }
            }

        }
    }//GEN-LAST:event_tab_retailMouseClicked

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        new vista_Admin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        lim.limpiar_texto(panel);
        activa_boton(true,false,false,true);
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed

        int s = JOptionPane.showConfirmDialog(null, "Eliminar retail","Si/no",JOptionPane.YES_NO_OPTION);
        if(s == 0){
            eliminar();
            rt.visualizar_Retail(tab_retail);
            activa_boton(true,false,false,true);
            lim.limpiar_texto(panel);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        modificar();
        rt.visualizar_Retail(tab_retail);
        activa_boton(true,false,false,true);
        lim.limpiar_texto(panel);
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void txt_razonSocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_razonSocialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_razonSocialActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        agregar();
        rt.visualizar_Retail(tab_retail);
        activa_boton(true,false,false,true);
        lim.limpiar_texto(panel);
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void txt_rutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rutKeyTyped
        int maximorut=8;
           int errores=0;
           char validarCaracter=evt.getKeyChar();
           ArrayList<Character> lista=retornarListaCaracteres();
           
           for (int i = 0; i < lista.size(); i++) {
               char caracter = lista.get(i);
               if(validarCaracter==caracter ){
               errores= errores+1;
               }
           }
           
           if((Character.isLetter(validarCaracter)&& validarCaracter != 'k') || txt_rut.getText().length()>=maximorut || errores > 0 ){
               evt.consume();
           }
    }//GEN-LAST:event_txt_rutKeyTyped

    private void txt_razonSocialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_razonSocialKeyTyped
        int maximoCaracter = 29;
        char validarCaracter=evt.getKeyChar();
        if(txt_razonSocial.getText().length()>=maximoCaracter ){
            evt.consume();
            
        }
    }//GEN-LAST:event_txt_razonSocialKeyTyped

    private void txt_rutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rutFocusLost
        int minimo=9;
        if(txt_rut.getText().length()<minimo){
            txt_rut.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, "Ingrese rut con su maximo de caracteres correcto EJ:(123456781)", "Aviso", JOptionPane.ERROR_MESSAGE);
            rutCorrecto=false;
        }else{
            txt_rut.setForeground(Color.black);
            rutCorrecto=true;
        }
    }//GEN-LAST:event_txt_rutFocusLost

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_razonSocial;
    private javax.swing.JLabel lbl_rut;
    private javax.swing.JLabel lbl_rutRetail;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tab_retail;
    private javax.swing.JTextField txt_razonSocial;
    private javax.swing.JTextField txt_rut;
    // End of variables declaration//GEN-END:variables

    private ArrayList<Character> retornarListaCaracteres(){
        ArrayList<Character> validaciones = new ArrayList<Character>();
           validaciones.add('.');
           validaciones.add('/');
           validaciones.add('|');
           validaciones.add('=');
           validaciones.add('?');
           validaciones.add('¿');
           validaciones.add('´');
           validaciones.add('¨');
           validaciones.add('{');
           validaciones.add('}');
           validaciones.add(';');
           validaciones.add(':');
           validaciones.add('_');
           validaciones.add('^');
           validaciones.add('-');
           validaciones.add('!');
           validaciones.add('"');
           validaciones.add('#');
           validaciones.add('$');
           validaciones.add('%');
           validaciones.add('&');
           validaciones.add('(');
           validaciones.add(')');
           validaciones.add('¡');
           validaciones.add(']');
           validaciones.add('*');
           validaciones.add('[');
           validaciones.add(',');
           validaciones.add('°');
           
        return  validaciones;
    }
    

}

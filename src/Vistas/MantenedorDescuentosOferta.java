/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.CRUDoferta;
import Clases.Oferta;
import Conexion.Database;
import Tablas.Tabla_Ofertas;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raul
 */
public class MantenedorDescuentosOferta extends javax.swing.JFrame {

    static home login = new home();

    Tabla_Ofertas to = new Tabla_Ofertas();
    CRUDoferta crud_of;
    int clic_tabla = 0;
    
     Database cn;
    Statement st;
    Database bd = new Database("");
    String ruta,nombre;
    int contador=0;
    Connection reg;
    
    
    /**
     * Creates new form 
     */
    public MantenedorDescuentosOferta() {
        initComponents();
        jTextField1.hide();
        txtDescentoActual.setEditable(false);
        txtPrecioNormal.setEditable(false);
        txtPrecioOfertaActual.setEditable(false);
        cn = new Database();
        mostrarOfertas();
        reg = cn.getConnection();
        jLabel6.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        txtd.hide();
        tblOfertas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
    }

    public void mostrarOfertas() {
        String encargado = login_Encargado.rut_encargado;

        String rutsinDigito = encargado.substring(0, encargado.length() - 1);
        String rutDigito = encargado.substring(encargado.length() - 1);
        String rutFinal = rutsinDigito + "-" + rutDigito;

        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = Database.crearConsulta("SELECT * FROM oferta where encargado_run = '" + rutFinal + "' order by id asc");

        modelo.setColumnIdentifiers(new Object[]{"ID", "TITULO", "DESCRIPCION", "F. INICIO", "F. TERMINO", "P. NORMAL", "P. OFERTA", "C. MINIMA", "C. MAXIMA", "ACTIVA"});

        try {

            while (rs.next()) {
                String act = "";
                if (rs.getString("activa").equals("1")) {
                    act = "Si";
                } else {
                    act = "No";
                }
                modelo.addRow(new Object[]{rs.getString("id"), rs.getString("titulo"), rs.getString("descripcion"), rs.getString("fecha_inicio"), rs.getString("fecha_termino"),
                    rs.getString("precio_normal"), rs.getString("precio_oferta"), rs.getString("compra_min"), rs.getString("compra_max"), act});

            }
            tblOfertas.setModel(modelo);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }
    
     public void modificar(){
         crud_of = new CRUDoferta();

         //int precOf = Integer.parseInt(txtNuevoDescuento.getText());
         if (txtNuevoDescuento.getText().length() == 0) {
             JOptionPane.showMessageDialog(this, "Ingrese un valor", "", JOptionPane.ERROR_MESSAGE);
         } else {
             int idOf = Integer.parseInt(txtd.getText());
             float descuento = 0;
             descuento = (Float.parseFloat(txtNuevoDescuento.getText()) / 100); //porcentaje de descuento
             float pNormal = Float.parseFloat(txtPrecioNormal.getText());   //precio normal
             int pOferta = (int) (pNormal - (pNormal * (descuento)));
             
             crud_of.ActDescuento(pOferta, idOf);
             JOptionPane.showMessageDialog(this, "Oferta '" + idOf + "' actualizada correctamente");
         }

       
    }
     
    public void modificarIMG() throws FileNotFoundException, SQLException {
        //-------------------------------
        FileInputStream fis = null;
        PreparedStatement pst = null;
        ruta = jTextField1.getText();
        String IdStr = txtd.getText();
        if (txtd.getText().length() == 0 ) {
           
            JOptionPane.showMessageDialog(this, "Seleccione una oferta", "", JOptionPane.ERROR_MESSAGE);
        } else {
            if(ruta.length() == 0){
                JOptionPane.showMessageDialog(this, "Seleccione imagen", "", JOptionPane.ERROR_MESSAGE);
            }else{
                File file = new File(ruta);
             fis = new FileInputStream(file);
            pst = reg.prepareStatement("UPDATE oferta SET imagen = ? WHERE id = ?");
            int idIMG = Integer.parseInt(IdStr);

            pst.setBinaryStream(1, fis, (int) file.length());
            pst.setInt(2, idIMG);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Imagen actualizada");
            //-------------------------------
            
            }
             
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblOfertas = new javax.swing.JTable();
        btn_salir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPrecioNormal = new javax.swing.JTextField();
        txtDescentoActual = new javax.swing.JTextField();
        txtPrecioOfertaActual = new javax.swing.JTextField();
        txtNuevoDescuento = new javax.swing.JTextField();
        txtd = new javax.swing.JTextField();
        btnActDesc = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnCargarImg = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblOfertas.setModel(new javax.swing.table.DefaultTableModel(
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
        tblOfertas.getTableHeader().setReorderingAllowed(false);
        tblOfertas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOfertasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblOfertas);

        btn_salir.setBackground(new java.awt.Color(204, 204, 255));
        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        jLabel1.setText("Precio normal:");

        jLabel2.setText("Precio Oferta actual:");

        jLabel3.setText("% descuento actual:");

        jLabel4.setText("Nuevo % descuento:");

        txtNuevoDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoDescuentoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPrecioNormal, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(txtDescentoActual)
                    .addComponent(txtPrecioOfertaActual)
                    .addComponent(txtNuevoDescuento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(txtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPrecioNormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDescentoActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPrecioOfertaActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNuevoDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnActDesc.setBackground(new java.awt.Color(51, 255, 51));
        btnActDesc.setText("OK");
        btnActDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActDescActionPerformed(evt);
            }
        });

        jLabel5.setText("Actualizar descuento");

        btnCargarImg.setBackground(new java.awt.Color(51, 204, 255));
        btnCargarImg.setText("Cargar foto");
        btnCargarImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarImgActionPerformed(evt);
            }
        });

        btnOK.setBackground(new java.awt.Color(51, 255, 102));
        btnOK.setText("Act IMG");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnActDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCargarImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnOK)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCargarImg))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnActDesc)
                                        .addGap(27, 27, 27)
                                        .addComponent(btn_salir))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        this.dispose();
        new vista_crearOferta().setVisible(true);
    }//GEN-LAST:event_btn_salirActionPerformed

    private void tblOfertasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOfertasMouseClicked
        clic_tabla = this.tblOfertas.rowAtPoint(evt.getPoint());
        String id = "" + tblOfertas.getValueAt(clic_tabla, 0);
        String precioNormal = "" + tblOfertas.getValueAt(clic_tabla, 5);
        String precioOferta = "" + tblOfertas.getValueAt(clic_tabla, 6);
        
        int idInt = Integer.parseInt(id);
        int precioNormInt = Integer.parseInt(precioNormal);
        int precioOfInt = Integer.parseInt(precioOferta);
        int porcentajeDescuento = 100 - ((100 * precioOfInt)/precioNormInt);

        txtd.setText(id + "");
        txtPrecioNormal.setText(precioNormal + "");
        txtPrecioOfertaActual.setText(precioOferta + "");
        txtDescentoActual.setText(porcentajeDescuento+"");

        int column = tblOfertas.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tblOfertas.getRowHeight();

        if (row < tblOfertas.getRowCount() && row >= 0 && column < tblOfertas.getColumnCount() && column >= 0) {
            Object value = tblOfertas.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

            }
        }
    }//GEN-LAST:event_tblOfertasMouseClicked

    private void btnActDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActDescActionPerformed
        if(txtDescentoActual.getText().equals("") || txtd.getText().equals("") || txtNuevoDescuento.equals("") || txtPrecioNormal.getText().equals("") || txtPrecioNormal.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Dato(s) vacios", "", JOptionPane.ERROR_MESSAGE);
        }else{
            modificar();
            mostrarOfertas();
            txtd.setText("");
            txtDescentoActual.setText("");
            txtNuevoDescuento.setText("");
            txtPrecioNormal.setText("");
            txtPrecioOfertaActual.setText("");
        }
        
    }//GEN-LAST:event_btnActDescActionPerformed

    private void txtNuevoDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoDescuentoKeyTyped
       int maximoPuntaje = 2;
        ArrayList<Character> lista = retornarListaCaracteres();
        int errores = 0;
        char validarCaracter = evt.getKeyChar();
        for (int i = 0; i < lista.size(); i++) {
            char caracter = lista.get(i);
            if (validarCaracter == caracter) {
                errores = errores + 1;
            }
        }

        if (Character.isLetter(validarCaracter) || errores > 0 || txtNuevoDescuento.getText().length() >= maximoPuntaje) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNuevoDescuentoKeyTyped

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        try {
            modificarIMG();
            txtd.setText("");
            txtDescentoActual.setText("");
            txtNuevoDescuento.setText("");
            txtPrecioNormal.setText("");
            txtPrecioOfertaActual.setText("");
            jTextField1.setText("");
            jLabel6.setIcon(null);
            jLabel6.setText("");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MantenedorDescuentosOferta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MantenedorDescuentosOferta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnCargarImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarImgActionPerformed
        final JFileChooser elegirImagen = new JFileChooser();
        elegirImagen.setMultiSelectionEnabled(false);
        int o = elegirImagen.showOpenDialog(this);
        if(o == JFileChooser.APPROVE_OPTION){
            ruta = elegirImagen.getSelectedFile().getAbsolutePath();
            nombre = elegirImagen.getSelectedFile().getName();
            jTextField1.setText(ruta);
            Image preview = Toolkit.getDefaultToolkit().getImage(ruta);
            if(preview != null){
                jLabel6.setText("");
                ImageIcon icon = new ImageIcon(preview.getScaledInstance(jLabel6.getWidth(), jLabel6.getHeight(), Image.SCALE_DEFAULT));
                jLabel6.setIcon(icon);
            }
        }
    }//GEN-LAST:event_btnCargarImgActionPerformed

    private ArrayList<Character> retornarListaCaracteres() {
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

        return validaciones;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActDesc;
    private javax.swing.JButton btnCargarImg;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblOfertas;
    private javax.swing.JTextField txtDescentoActual;
    private javax.swing.JTextField txtNuevoDescuento;
    private javax.swing.JTextField txtPrecioNormal;
    private javax.swing.JTextField txtPrecioOfertaActual;
    private javax.swing.JTextField txtd;
    // End of variables declaration//GEN-END:variables
}

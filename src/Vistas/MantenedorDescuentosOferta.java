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
        setResizable(false);
        
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
    
     public void modificarSKU() throws SQLException {
        //-------------------------------
       
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        
       pst2 = reg.prepareStatement("select * from oferta_producto"); //selcciono todos
       ResultSet lista = pst2.executeQuery();
       String skuUsar="";
       int ofertaID=0;
       while (lista.next()) //recorre
                {
                   skuUsar=lista.getString("producto_sku");
                   ofertaID = lista.getInt("oferta_id");
                   pst = reg.prepareStatement("update oferta set producto_sku = ? where id = ?");
                   pst.setString(1,skuUsar);
                   pst.setInt(2,ofertaID);
                   pst.executeUpdate();
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
        jLabel5 = new javax.swing.JLabel();
        btnActDesc = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnOK = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        btnCargarImg = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mantenedor de descuentos");

        tblOfertas.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
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

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        jLabel1.setText("Precio normal:");

        jLabel2.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        jLabel2.setText("Precio Oferta actual: $");

        jLabel3.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        jLabel3.setText("% descuento actual: $");

        jLabel4.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        jLabel4.setText("Nuevo % descuento:");

        txtPrecioNormal.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N

        txtDescentoActual.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N

        txtPrecioOfertaActual.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N

        txtNuevoDescuento.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        txtNuevoDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoDescuentoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel5.setText("Actualizar descuento");

        btnActDesc.setBackground(new java.awt.Color(51, 255, 51));
        btnActDesc.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        btnActDesc.setText("Actualizar");
        btnActDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActDescActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescentoActual, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioOfertaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNuevoDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtd, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActDesc))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPrecioNormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 45, Short.MAX_VALUE)
                        .addComponent(btnActDesc)
                        .addGap(67, 67, 67)
                        .addComponent(txtd, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );

        btn_salir.setBackground(new java.awt.Color(204, 204, 255));
        btn_salir.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        btnOK.setBackground(new java.awt.Color(51, 255, 102));
        btnOK.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        btnOK.setText("Act IMG");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnCargarImg.setBackground(new java.awt.Color(51, 204, 255));
        btnCargarImg.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        btnCargarImg.setText("Cargar foto");
        btnCargarImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarImgActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel7.setText("Actualizar imagen");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargarImg, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(131, 131, 131))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnCargarImg, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(25, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_salir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_salir))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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

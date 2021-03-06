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
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raul
 */
public class verOfertas extends javax.swing.JFrame {

    static home login = new home();

    /**
     * Creates new form verOfertas
     */
    public verOfertas() {
        initComponents();
        lblIdM.hide();
        mostrarOfertas();
        habilitacionDeTextos(false);
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setResizable(false);
        

    }

    public void mostrarOfertas() {
        lblIdM.setVisible(false);
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

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblOfertas = new javax.swing.JTable();
        btn_salir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnMantDesc = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        lblIdM = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblTituloM = new javax.swing.JLabel();
        lblDescrip = new javax.swing.JLabel();
        lbldescripM = new javax.swing.JLabel();
        lblFechaI = new javax.swing.JLabel();
        lblFechaIM = new javax.swing.JLabel();
        lblFechaT = new javax.swing.JLabel();
        lblFechaTM = new javax.swing.JLabel();
        lblPrecioN = new javax.swing.JLabel();
        lblPrecioO = new javax.swing.JLabel();
        lblCompaMin = new javax.swing.JLabel();
        lblCompraMax = new javax.swing.JLabel();
        lblActividad = new javax.swing.JLabel();
        lblPrecioNM = new javax.swing.JLabel();
        lblPrecioOM = new javax.swing.JLabel();
        lblCompraMinM = new javax.swing.JLabel();
        lblCompraMaxM = new javax.swing.JLabel();
        lblActividadM = new javax.swing.JLabel();
        lblTituloInicial = new javax.swing.JLabel();
        btn_Publicar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jlReportes = new javax.swing.JLabel();
        btnObtenerReportes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ofertas hechas por usted");

        tblOfertas.setBackground(new java.awt.Color(153, 153, 153));
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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOfertas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOfertasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblOfertas);

        btn_salir.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jLabel1.setText("Mantenedor de descuentos");

        btnMantDesc.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        btnMantDesc.setText("IR");
        btnMantDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMantDescActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(btnMantDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnMantDesc)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        lblId.setText("   ");

        lblTitulo.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lblTitulo.setText("Titulo:");

        lblTituloM.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lblTituloM.setText("TituloMuestra");

        lblDescrip.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lblDescrip.setText("Descripción:");

        lbldescripM.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lbldescripM.setText("descripMuestra");

        lblFechaI.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lblFechaI.setText("Fecha Inicio:");

        lblFechaIM.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lblFechaIM.setText("jLabel2");

        lblFechaT.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lblFechaT.setText("Fecha Termino:");

        lblFechaTM.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lblFechaTM.setText("jLabel3");

        lblPrecioN.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lblPrecioN.setText("Precio Normal: $");

        lblPrecioO.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lblPrecioO.setText("Precio Oferta: $");

        lblCompaMin.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lblCompaMin.setText("Compra Mínima:");

        lblCompraMax.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lblCompraMax.setText("Compra Máxima:");

        lblActividad.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lblActividad.setText("Se encuentra:");

        lblPrecioNM.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lblPrecioNM.setText("jLabel2");

        lblPrecioOM.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lblPrecioOM.setText("jLabel2");

        lblCompraMinM.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lblCompraMinM.setText("jLabel2");

        lblCompraMaxM.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lblCompraMaxM.setText("jLabel2");

        lblActividadM.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lblActividadM.setText("jLabel2");

        lblTituloInicial.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        lblTituloInicial.setText("Descripción de la oferta:");

        btn_Publicar.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        btn_Publicar.setText("PUBLICAR / BAJAR");
        btn_Publicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PublicarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTitulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTituloM)
                                .addGap(267, 267, 267)
                                .addComponent(lblId)
                                .addGap(18, 18, 18)
                                .addComponent(lblIdM))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblDescrip)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbldescripM))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblCompaMin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCompraMinM))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblCompraMax)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCompraMaxM))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblActividad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblActividadM))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPrecioO)
                                    .addComponent(lblPrecioN))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPrecioNM)
                                    .addComponent(lblPrecioOM)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblFechaI)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblFechaIM))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblFechaT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblFechaTM)))
                        .addGap(0, 147, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTituloInicial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Publicar)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTituloInicial)
                            .addComponent(btn_Publicar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTitulo)
                            .addComponent(lblTituloM))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblId)
                            .addComponent(lblIdM))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescrip)
                    .addComponent(lbldescripM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaI)
                    .addComponent(lblFechaIM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaT)
                    .addComponent(lblFechaTM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecioN)
                    .addComponent(lblPrecioNM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecioO)
                    .addComponent(lblPrecioOM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCompaMin)
                    .addComponent(lblCompraMinM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCompraMax)
                    .addComponent(lblCompraMaxM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActividad)
                    .addComponent(lblActividadM))
                .addGap(36, 36, 36))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jlReportes.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jlReportes.setText("Obtención de reportes");

        btnObtenerReportes.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        btnObtenerReportes.setText("IR");
        btnObtenerReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObtenerReportesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jlReportes))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(btnObtenerReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlReportes)
                .addGap(18, 18, 18)
                .addComponent(btnObtenerReportes)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btn_salir)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
        int fila = tblOfertas.getSelectedRow();
        lblIdM.setVisible(false);
        int id = Integer.parseInt(tblOfertas.getValueAt(fila, 0).toString());

        String titulo = tblOfertas.getValueAt(fila, 1).toString();
        String descrip = tblOfertas.getValueAt(fila, 2).toString();
        String fechaInicio = tblOfertas.getValueAt(fila, 3).toString();
        String fechaTermino = tblOfertas.getValueAt(fila, 4).toString();
        String precioNormal = tblOfertas.getValueAt(fila, 5).toString();
        String precioOferta = tblOfertas.getValueAt(fila, 6).toString();
        String compraMin = tblOfertas.getValueAt(fila, 7).toString();
        String compraMax = tblOfertas.getValueAt(fila, 8).toString();

        String act = tblOfertas.getValueAt(fila, 9).toString();

        try {
            mostrarDatos(id, titulo, descrip, fechaInicio, fechaTermino, precioNormal, precioOferta, compraMin, compraMax, act);
        } catch (ParseException ex) {
            Logger.getLogger(verOfertas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblOfertasMouseClicked

    private void btn_PublicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PublicarActionPerformed

        int itemSeleccionado = tblOfertas.getSelectedColumn();
        if (itemSeleccionado <= 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            int fila = tblOfertas.getSelectedRow();
            int id = Integer.parseInt(tblOfertas.getValueAt(fila, 0).toString());

            String titulo = tblOfertas.getValueAt(fila, 1).toString();
            String descrip = tblOfertas.getValueAt(fila, 2).toString();
            String fechaInicio = tblOfertas.getValueAt(fila, 3).toString();
            String fechaTermino = tblOfertas.getValueAt(fila, 4).toString();
            String precioNormal = tblOfertas.getValueAt(fila, 5).toString();
            String precioOferta = tblOfertas.getValueAt(fila, 6).toString();
            String compraMin = tblOfertas.getValueAt(fila, 7).toString();
            String compraMax = tblOfertas.getValueAt(fila, 8).toString();

            String act = tblOfertas.getValueAt(fila, 9).toString();

            try {
                mostrarDatos(id, titulo, descrip, fechaInicio, fechaTermino, precioNormal, precioOferta, compraMin, compraMax, act);
            } catch (ParseException ex) {
                Logger.getLogger(verOfertas.class.getName()).log(Level.SEVERE, null, ex);
            }

            char act2;
            System.out.println("valor id: " + id + " valor de activo: " + act);

            if (act.equals("Si")) {
                act2 = '0';

                JOptionPane.showMessageDialog(this, "Oferta cambiada a NO PUBLICADA.");
            } else {
                act2 = '1';
                JOptionPane.showMessageDialog(this, "Oferta publicada.");
            }

            System.out.println("NUEVOS -> valor id: " + id + " valor de activo: " + act);

            Database cn = new Database();
            String sql = "UPDATE oferta SET activa = ? WHERE id = ?";
            PreparedStatement ps = null;
            try {
                ps = cn.getConnection().prepareStatement(sql);
                ps.setString(1, String.valueOf(act2));
                ps.setInt(2, id);
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } finally {
                try {
                    ps.close();
                    cn = null;
                } catch (Exception ex) {
                }
            }

            mostrarOfertas();
        }


    }//GEN-LAST:event_btn_PublicarActionPerformed

    private void btnMantDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMantDescActionPerformed
        this.dispose();
        new MantenedorDescuentosOferta().setVisible(true);

    }//GEN-LAST:event_btnMantDescActionPerformed

    private void btnObtenerReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObtenerReportesActionPerformed
        try {
            crearReporte();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(verOfertas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(verOfertas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnObtenerReportesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMantDesc;
    private javax.swing.JButton btnObtenerReportes;
    private javax.swing.JButton btn_Publicar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlReportes;
    private javax.swing.JLabel lblActividad;
    private javax.swing.JLabel lblActividadM;
    private javax.swing.JLabel lblCompaMin;
    private javax.swing.JLabel lblCompraMax;
    private javax.swing.JLabel lblCompraMaxM;
    private javax.swing.JLabel lblCompraMinM;
    private javax.swing.JLabel lblDescrip;
    private javax.swing.JLabel lblFechaI;
    private javax.swing.JLabel lblFechaIM;
    private javax.swing.JLabel lblFechaT;
    private javax.swing.JLabel lblFechaTM;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdM;
    private javax.swing.JLabel lblPrecioN;
    private javax.swing.JLabel lblPrecioNM;
    private javax.swing.JLabel lblPrecioO;
    private javax.swing.JLabel lblPrecioOM;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloInicial;
    private javax.swing.JLabel lblTituloM;
    private javax.swing.JLabel lbldescripM;
    private javax.swing.JTable tblOfertas;
    // End of variables declaration//GEN-END:variables

    public void habilitacionDeTextos(boolean habili) {
        if (habili == false) {
            lblTituloInicial.setText("Seleccione oferta para ver descripción");
        } else {
            lblTituloInicial.setText("Descripción de la oferta");

        }

        lblIdM.setVisible(habili);
        lblTitulo.setVisible(habili);
        lblTituloM.setVisible(habili);
        lblDescrip.setVisible(habili);
        lbldescripM.setVisible(habili);
        setVisible(habili);
        lblFechaI.setVisible(habili);
        lblFechaIM.setVisible(habili);
        lblFechaT.setVisible(habili);
        lblFechaTM.setVisible(habili);
        lblPrecioN.setVisible(habili);
        lblPrecioNM.setVisible(habili);
        lblPrecioO.setVisible(habili);
        lblPrecioOM.setVisible(habili);
        lblCompaMin.setVisible(habili);
        lblCompraMinM.setVisible(habili);
        lblCompraMax.setVisible(habili);
        lblCompraMaxM.setVisible(habili);
        lblActividad.setVisible(habili);
        lblActividadM.setVisible(habili);

    }

    public void mostrarDatos(int id, String titulo, String descrip, String fechai, String fechaT, String precioNor, String precioO, String compMin,
            String compMax, String acti) throws ParseException {
        habilitacionDeTextos(true);
        lblIdM.setText(Integer.toString(id));
        lblIdM.setVisible(false);
        lblTituloM.setText(titulo);
        lbldescripM.setText(descrip);

        SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss");
        Date date = dt.parse(fechai);
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
        lblFechaIM.setText(dt1.format(date));

        SimpleDateFormat dt2 = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss");
        Date date2 = dt2.parse(fechaT);
        SimpleDateFormat dt21 = new SimpleDateFormat("dd-MM-yyyy");
        lblFechaTM.setText(dt21.format(date2));

        lblPrecioNM.setText(precioNor);
        lblPrecioOM.setText(precioO);
        lblCompraMinM.setText(compMin);
        lblCompraMaxM.setText(compMax);
        if (acti.equals("Si")) {
            lblActividadM.setText("Activa");
        } else {
            lblActividadM.setText("No activa");
        }

    }

    private void crearReporte() throws FileNotFoundException, DocumentException {
        String rutEncargadoSolicitante = login_Encargado.rut_encargado;
        String rutsinDigito = rutEncargadoSolicitante.substring(0, rutEncargadoSolicitante.length() - 1);
        String rutDigito = rutEncargadoSolicitante.substring(rutEncargadoSolicitante.length() - 1);
        String rutFinal = rutsinDigito + "-" + rutDigito;

        System.out.println(rutFinal);
        Document documento = new Document();
        
        
        File desktopDir = new File(System.getProperty("user.home"), "Desktop");
        System.out.println(desktopDir.getPath() + " " + desktopDir.exists());
        String pathToDesktop = desktopDir.getPath();
        //FileOutputStream out =  new FileOutputStream(new File(pathToDesktop+System.getProperty("file.separator")+"pdf de prueba.pdf"));
        PdfWriter.getInstance(documento, new FileOutputStream(new File(pathToDesktop+System.getProperty("file.separator")+"Reporte valorizaciones.pdf")));
        
        documento.open();
        documento.add(new Paragraph("Descripción de valorizaciones"));
        documento.add(new Paragraph("   "));

        Database cnOfertas = new Database();
        Database cnValoracion = new Database();
        Database cnEvaluacion = new Database();
        int contador = 0;

        String sqlOfertas = "select * from oferta WHERE encargado_run = ?";
        ResultSet listaOfertas = null;
        PreparedStatement psOfertas = null;

        String sqlValoracion = "select * from valoracion WHERE oferta_id = ?";
        ResultSet listaValoracion = null;
        PreparedStatement psValoracion = null;
        
        String sqlEvaluacion = "select * from evaluacion WHERE id = ?";
        ResultSet listaEvaluacion = null;
        PreparedStatement psEvaluacion = null;

        try {

            psOfertas = cnOfertas.getConnection().prepareStatement(sqlOfertas);
            psOfertas.setString(1, rutFinal);

            listaOfertas = psOfertas.executeQuery();
            while (listaOfertas.next()) {

                com.itextpdf.text.Font dataRedFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.RED);
                com.itextpdf.text.Font dataBlueFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLUE);
                com.itextpdf.text.Font dataBlackFont = FontFactory.getFont("Garamond", 13, BaseColor.BLACK);
                com.itextpdf.text.Font dataBoldFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
                
                Paragraph p = new Paragraph();
                Paragraph p1 = new Paragraph();
                Paragraph p2 = new Paragraph();
                Paragraph p3 = new Paragraph();

                p.add(new Chunk("ID DE LA OFERTA: ", dataRedFont));
                p.add(new Chunk(listaOfertas.getString(1), dataBlackFont));
                documento.add(p);
                p1.add(new Chunk("TITULO: ", dataRedFont));
                p1.add(new Chunk(listaOfertas.getString(2), dataBlackFont));
                documento.add(p1);
                p2.add(new Chunk("DESCRIPCIÓN: ", dataRedFont));
                p2.add(new Chunk(listaOfertas.getString(3), dataBlackFont));
                documento.add(p2);
                
                psValoracion = cnValoracion.getConnection().prepareStatement(sqlValoracion);
                psValoracion.setInt(1, Integer.parseInt(listaOfertas.getString(1)));
                listaValoracion = psValoracion.executeQuery();

                while (listaValoracion.next()) {
                    contador = contador + 1;
                    
                    documento.add(new Paragraph("  "));
                    p3.add(new Chunk("   " + contador, dataBlueFont));
                    p3.add(new Chunk(".- Valoracion id: " + listaValoracion.getString(1) + ", calificación: " + listaValoracion.getString(2), dataBlackFont));
                    documento.add(p3);
                    
                    psEvaluacion = cnEvaluacion.getConnection().prepareStatement(sqlEvaluacion);
                    psEvaluacion.setInt(1, Integer.parseInt(listaValoracion.getString(1)));
                    listaEvaluacion = psEvaluacion.executeQuery();
                    
                    while(listaEvaluacion.next()){
                    documento.add(new Paragraph("   Comentario: "+listaEvaluacion.getString(4)));
                    
                    documento.add(new Paragraph("   Con fecha: "+listaEvaluacion.getString(2).substring(0,listaEvaluacion.getString(2).indexOf(" "))));
                    documento.add(new Paragraph("  "));
                    }

                }
                listaValoracion = psValoracion.executeQuery();
                if (listaValoracion.next() !=true) {
                    
                    documento.add(new Paragraph("  "));
                    documento.add(new Paragraph("No existen valoraciones",dataBoldFont));
                }

                documento.add(new Paragraph(" "));
                contador = 0;
            }

            JOptionPane.showMessageDialog(this, "Documento creado en el escritorio con nombre *Reporte valorizaciones*.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(this, "Error al crear el documento.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(this, "Error al crear el documento.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
        }
        documento.close();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Conexion.Database;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonathan
 */
public class vista_Gerente extends javax.swing.JFrame {

    /**
     * Creates new form vista_Gerente
     */
    public vista_Gerente() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnReporte = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblCajatexto2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú gerencia");
        setResizable(false);

        btnReporte.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        btnReporte.setText("Obtener reporte");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Nirmala UI", 1, 24)); // NOI18N
        jLabel1.setText("Menú Principal Gerencia");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Nirmala UI", 0, 24)); // NOI18N
        jLabel2.setText("Bienvenido, en este menú se permite obtener el ");

        jLabel3.setFont(new java.awt.Font("Nirmala UI", 3, 24)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Nirmala UI", 0, 24)); // NOI18N
        jLabel4.setText("reporte final de toda la información empresarial");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        lblCajatexto2.setFont(new java.awt.Font("Nirmala UI Semilight", 3, 24)); // NOI18N
        lblCajatexto2.setText("Porfavor haga click en obtener reporte!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(lblCajatexto2))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(219, 219, 219)
                                .addComponent(btnReporte)))
                        .addGap(0, 23, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCajatexto2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(btnReporte)
                .addGap(19, 19, 19)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        try {
            CrearResumenTienda();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(vista_Gerente.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al crear el documento, contacte con administrador.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (DocumentException ex) {
            Logger.getLogger(vista_Gerente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(vista_Gerente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
        new home().setVisible(true);
    }//GEN-LAST:event_btnSalirActionPerformed

    /**
     * @param args the command line arguments
     */

    public String obtRut() {
        String rutGerente = login_Gerente.rut_gerente;
        String rutsinDigito = rutGerente.substring(0, rutGerente.length() - 1);
        String rutDigito = rutGerente.substring(rutGerente.length() - 1);
        String rutFinal = rutsinDigito + "-" + rutDigito;

        System.out.println(rutFinal);
        return rutFinal;
    }
    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCajatexto2;
    // End of variables declaration//GEN-END:variables

    private void CrearResumenTienda() throws FileNotFoundException, DocumentException, SQLException {
        Document documento = new Document();
        com.itextpdf.text.Font dataRedFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.RED);
        com.itextpdf.text.Font dataBlueFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLUE);
        com.itextpdf.text.Font dataBlackFont = FontFactory.getFont("Garamond", 13, BaseColor.BLACK);
        com.itextpdf.text.Font dataBoldFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
        com.itextpdf.text.Font dataGrayFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.GRAY);

        File desktopDir = new File(System.getProperty("user.home"), "Desktop");
        System.out.println(desktopDir.getPath() + " " + desktopDir.exists());
        String pathToDesktop = desktopDir.getPath();
        //FileOutputStream out =  new FileOutputStream(new File(pathToDesktop+System.getProperty("file.separator")+"pdf de prueba.pdf"));
        PdfWriter.getInstance(documento, new FileOutputStream(new File(pathToDesktop + System.getProperty("file.separator") + "Reporte final gerencia.pdf")));

        documento.open();
        
        Database conex = new Database();
        
        // Contar retail
        String sqlRet = "select * from retail";
        ResultSet listRet = null;
        PreparedStatement psRet = null;
        psRet = conex.getConnection().prepareStatement(sqlRet);
        listRet = psRet.executeQuery();
        
        int contRet = 0;
        while (listRet.next()) {
            contRet++;
        }
        
        // Contar consumidores
        String sqlCons = "select * from consumidor";
        ResultSet listCons = null;
        PreparedStatement psCons = null;
        psCons = conex.getConnection().prepareStatement(sqlCons);
        listCons = psCons.executeQuery();
        
        int contCons = 0;
        while (listCons.next()) {
            contCons++;
        }
        
        // Contar correos
        String sqlCorr = "select * from consumidor where suscrito = '1'";
        ResultSet listCorr = null;
        PreparedStatement psCorr = null;
        psCorr = conex.getConnection().prepareStatement(sqlCorr);
        listCorr = psCorr.executeQuery();
        
        int contCorr = 0;
        while (listCorr.next()) {
            contCorr++;
        }
        
        // Contar valoraciones
        String sqlVal = "select * from valoracion";
        ResultSet listVal = null;
        PreparedStatement psVal = null;
        psVal = conex.getConnection().prepareStatement(sqlVal);
        listVal = psVal.executeQuery();
        
        int contVal = 0;
        while (listVal.next()) {
            contVal++;
        }
        
         documento.add(new Paragraph("Cantidad de retail: " + contRet));
        documento.add(new Paragraph("Cantidad de consumidores: " + contCons));
        documento.add(new Paragraph("Número de consumidores suscritos para enviar correos: " + contCorr));
       // documento.add(new Paragraph("Número de correos enviados: " + numeroMails));
        documento.add(new Paragraph("Valoraciones realizadas: " + contVal));
        
        
        documento.add(new Paragraph("-------------------------------------------------------------------------------------"));
        
        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("Reporte con resumen por tienda"));
        documento.add(new Paragraph("   "));

        String sqlRetail = "select * from retail";
        ResultSet listaRetail = null;
        PreparedStatement psRetail = null;

        String sqlSucur = "select * from sucursal WHERE retail_rut = ?";
        ResultSet listaSucur = null;
        PreparedStatement psSucur = null;

        String sqlEncargado = "select * from encargado WHERE retail_rut = ?";
        ResultSet listaEncargado = null;
        PreparedStatement psEncargado = null;

        String sqlOfertas = "select * from oferta WHERE encargado_run = ?";
        ResultSet listaOfertas = null;
        PreparedStatement psOfertas = null;

        psRetail = conex.getConnection().prepareStatement(sqlRetail);
        listaRetail = psRetail.executeQuery();

        while (listaRetail.next()) {
            Paragraph RetailTitulo = new Paragraph();
            RetailTitulo.add(new Chunk("Información de Retail: ", dataRedFont));
            documento.add(RetailTitulo);
            documento.add(new Paragraph(" "));

            Paragraph p = new Paragraph();
            Paragraph p1 = new Paragraph();
            Paragraph p2 = new Paragraph();
            Paragraph p3 = new Paragraph();

            p.add(new Chunk("ID DE RETAIL: ", dataBlackFont));
            p.add(new Chunk(listaRetail.getString(1), dataBlackFont));
            documento.add(p);
            p1.add(new Chunk("RAZON SOCIAL: ", dataBlackFont));
            p1.add(new Chunk(listaRetail.getString(2), dataBlackFont));
            documento.add(p1);
            documento.add(new Paragraph(" "));
            psSucur = conex.getConnection().prepareStatement(sqlSucur);
            psSucur.setString(1, listaRetail.getString(1));
            listaSucur = psSucur.executeQuery();

            Paragraph SucursalTitulo = new Paragraph();
            SucursalTitulo.add(new Chunk("Sucursales: ", dataRedFont));
            documento.add(SucursalTitulo);
            documento.add(new Paragraph(" "));
            if (listaSucur.next() != true) {

                documento.add(new Paragraph("No existen Sucursales", dataBoldFont));
                documento.add(new Paragraph("  "));
            }
            while (listaSucur.next()) {
                Paragraph pSucur = new Paragraph();
                Paragraph pSucur1 = new Paragraph();
                pSucur.add(new Chunk("  ID DE SUCURSAL: ", dataBlackFont));
                pSucur.add(new Chunk(listaSucur.getString(1), dataBlackFont));
                documento.add(pSucur);
                pSucur1.add(new Chunk("  Nombre: ", dataBlackFont));
                pSucur1.add(new Chunk(listaSucur.getString(2), dataBlackFont));
                documento.add(pSucur1);
                documento.add(new Paragraph(" "));
            }

            psEncargado = conex.getConnection().prepareStatement(sqlEncargado);
            psEncargado.setString(1, listaRetail.getString(1));
            listaEncargado = psEncargado.executeQuery();
            while (listaEncargado.next()) {

                Paragraph EncargadoTitulo = new Paragraph();
                EncargadoTitulo.add(new Chunk("Encargado a cargo del Retail: ", dataRedFont));
                documento.add(EncargadoTitulo);
                documento.add(new Paragraph(" "));

                Paragraph pEncar = new Paragraph();
                Paragraph pEnca1 = new Paragraph();
                pEncar.add(new Chunk("Rut del encargado: ", dataBlackFont));
                pEncar.add(new Chunk(listaEncargado.getString(1), dataBlackFont));
                documento.add(pEncar);
                pEnca1.add(new Chunk("Nombre: ", dataBlackFont));
                pEnca1.add(new Chunk(listaEncargado.getString(2) + " ", dataBlackFont));
                pEnca1.add(new Chunk(listaEncargado.getString(4), dataBlackFont));
                documento.add(pEnca1);
                documento.add(new Paragraph(" "));

                psOfertas = conex.getConnection().prepareStatement(sqlOfertas);
                psOfertas.setString(1, listaEncargado.getString(1));
                listaOfertas = psOfertas.executeQuery();

                Paragraph OfertasTitulo = new Paragraph();
                OfertasTitulo.add(new Chunk("Ofertas Realizadas por este encargado: ", dataRedFont));
                documento.add(OfertasTitulo);
                documento.add(new Paragraph(" "));

                while (listaOfertas.next()) {
                    Paragraph pOfer = new Paragraph();
                    Paragraph pOfer1 = new Paragraph();
                    Paragraph pOfer2 = new Paragraph();
                    pOfer.add(new Chunk("ID DE LA OFERTA: ", dataBlackFont));
                    pOfer.add(new Chunk(listaOfertas.getString(1), dataBlackFont));
                    documento.add(pOfer);
                    pOfer1.add(new Chunk("TITULO: ", dataBlackFont));
                    pOfer1.add(new Chunk(listaOfertas.getString(2), dataBlackFont));
                    documento.add(pOfer1);
                    pOfer2.add(new Chunk("DESCRIPCIÓN: ", dataBlackFont));
                    pOfer2.add(new Chunk(listaOfertas.getString(3), dataBlackFont));
                    documento.add(pOfer2);
                    documento.add(new Paragraph(" "));

                }

            }
            documento.add(new Paragraph("-------------------------------------------------------------------------------------"));
        }
        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("-------------------------------------------------------------------------------------"));

        String sqlConsumidor = "select * from consumidor";
        ResultSet listaConsumidor = null;
        PreparedStatement psConsumidor = null;

        String sqlValoracion = "select * from valoracion WHERE consumidor_run = ? order by id";
//        String sqlValoracion = "select * from valoracion";
        ResultSet listaValoracion = null;
        PreparedStatement psValoracion = null;

        String sqlEvaluacion = "select * from evaluacion WHERE id = ?";
        ResultSet listaEvaluacion = null;
        PreparedStatement psEvaluacion = null;

        psConsumidor = conex.getConnection().prepareStatement(sqlConsumidor);
        listaConsumidor = psConsumidor.executeQuery();

        Paragraph consumidorTitulo = new Paragraph();
        consumidorTitulo.add(new Chunk("Consumidores registrados en el sistema: ", dataRedFont));
        documento.add(consumidorTitulo);
        documento.add(new Paragraph(" "));

        while (listaConsumidor.next()) {
            documento.add(new Paragraph("Info consumidor", dataBlueFont));
            documento.add(new Paragraph(" "));
            Paragraph parrafoConsumidor = new Paragraph();
            Paragraph parrafoConsumidor1 = new Paragraph();
            parrafoConsumidor.add(new Chunk("Run Consumidor: ", dataBlackFont));
            parrafoConsumidor.add(new Chunk(listaConsumidor.getString(1), dataBlackFont));
            documento.add(parrafoConsumidor);
            parrafoConsumidor1.add(new Chunk("Nombre completo: ", dataBlackFont));
            parrafoConsumidor1.add(new Chunk(listaConsumidor.getString(2) + " " + listaConsumidor.getString(3) + " " + listaConsumidor.getString(4) + " " + listaConsumidor.getString(5), dataBlackFont));
            documento.add(parrafoConsumidor1);
            documento.add(new Paragraph(" "));

            psValoracion = conex.getConnection().prepareStatement(sqlValoracion);
            psValoracion.setString(1, listaConsumidor.getString(1));
            listaValoracion = psValoracion.executeQuery();

            if (listaValoracion.next() != true) {

                documento.add(new Paragraph("No existen valoraciones", dataBlackFont));
                documento.add(new Paragraph("  "));
            } else {
                Paragraph valoracionTitulo = new Paragraph();
                valoracionTitulo.add(new Chunk("Valoraciones hechas por el consumidor: ", dataBlackFont));
                documento.add(valoracionTitulo);
                documento.add(new Paragraph(" "));

                while (listaValoracion.next()) {
                    Paragraph parraValoracion = new Paragraph();
                    Paragraph parraValoracion1 = new Paragraph();
                    parraValoracion.add(new Chunk("   Id valoración: ", dataBlackFont));
                    parraValoracion.add(new Chunk(listaValoracion.getString(1), dataBlackFont));
                    documento.add(parraValoracion);
                    parraValoracion1.add(new Chunk("   Escala: ", dataBlackFont));
                    parraValoracion1.add(new Chunk(listaValoracion.getString(2), dataBlackFont));
                    documento.add(parraValoracion1);
                    documento.add(new Paragraph("  "));
                    
                    psEvaluacion = conex.getConnection().prepareStatement(sqlEvaluacion);
                    psEvaluacion.setInt(1, Integer.parseInt(listaValoracion.getString(3)));
                    listaEvaluacion = psEvaluacion.executeQuery();
                    
                    while(listaEvaluacion.next()){
                    documento.add(new Paragraph("   Comentario: "+listaEvaluacion.getString(4)));
                    
                    documento.add(new Paragraph("   Con fecha: "+listaEvaluacion.getString(2).substring(0,listaEvaluacion.getString(2).indexOf(" "))));
                    documento.add(new Paragraph("  "));
                    }

                }
            }

        }
        documento.add(new Paragraph("-------------------------------------------------------------------------------------"));
        documento.close();
        JOptionPane.showMessageDialog(this, "Documento creado en el escritorio con nombre *Reporte final gerencia*.");
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Consumidor;
import Conexion.Database;
import static Vistas.login_Admin.home;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristobal
 */
public class vista_reportes extends javax.swing.JFrame {

    /**
     * Creates new form vista_reportes
     */
    public vista_reportes() throws FileNotFoundException, DocumentException {
        initComponents();
        this.setLocationRelativeTo(null);
        crearReporte();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnObReporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btnObReporte.setText("Obtener reporte");
        btnObReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(btnObReporte)
                .addContainerGap(298, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(btnObReporte)
                .addContainerGap(249, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnObReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObReporteActionPerformed

    }//GEN-LAST:event_btnObReporteActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnObReporte;
    // End of variables declaration//GEN-END:variables

    private void crearReporte() throws FileNotFoundException, DocumentException {
        Document documento = new Document();
        PdfWriter.getInstance(documento, new FileOutputStream("pdf de prueba.pdf"));
        documento.open();
        documento.add(new Paragraph("parrafo de prueba"));
        Database cn = new Database();

        String sql = "select * from valoracion";
        ResultSet lista = null;
        PreparedStatement ps = null;
        /*
        String sqlOfertas = "select * from ofertas";
        ResultSet listaOferta = null;
        PreparedStatement psOferta = null;
         */
        try {
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while (lista.next()) {
                documento.add(new Paragraph("Valoracion Id: " + lista.getString(1) + " la valoracion es " + lista.getString(2) + ""));
                
                
//                String sqlOfertas = "select * from ofertas WHERE id = ?";
//                PreparedStatement psOferta = null;
//                psOferta = cn.getConnection().prepareStatement(sql);
//                //psOferta.setInt(1, );
//                ResultSet listaOferta=psOferta.executeQuery();
//                documento.add(new Paragraph("Id oferta: " + listaOferta.getString(1) + " nombre " + listaOferta.getString(2) + ""));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
        }
        documento.close();

    }
}

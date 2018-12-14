/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.CRUDencargado;
import Clases.Encargado;
import Conexion.Database;
import Clases.Limpiar;
import Clases.Producto;
import Clases.Retail;
import Tablas.Tabla_Encargado;
import Tablas.Tabla_Encargado;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raul
 */
public class vista_Encargados extends javax.swing.JFrame {

    static home login = new home();

    Tabla_Encargado te = new Tabla_Encargado();
    Limpiar lim = new Limpiar();
    CRUDencargado crud_enc;
    int clic_tabla = 0;
    boolean rutCorrecto = false;
    boolean contraCorrecta = false;

    /**
     * Creates new form listaProductos
     */
    public vista_Encargados() {
        initComponents();
        te.visualizar_Encargado(tab_encargados);
        Retail retail = new Retail();
        retail.mostrarRetail(cb_retail);
        txt_password.setText("");
        setExtendedState(JFrame.MAXIMIZED_HORIZ);

        setResizable(false);
        activa_boton(true, false, false, true);
        tab_encargados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void agregar() {
        crud_enc = new CRUDencargado();
        Encargado enc = new Encargado();

        String rutsinDigito = txt_rut.getText().substring(0, txt_rut.getText().length() - 1);
        String rutDigito = txt_rut.getText().substring(txt_rut.getText().length() - 1);
        String rutFinal = rutsinDigito + "-" + rutDigito;
        enc.setRun(rutFinal);
        enc.setP_nombre(txt_nombre1.getText());
        enc.setS_nombre(txt_nombre2.getText());
        enc.setApellido_p(txt_apellido1.getText());
        enc.setApellido_m(txt_apellido2.getText());
        enc.setClave(txt_password.getText());
        if (chk_activo.isSelected()) {
            enc.setActivo('1');
        } else {
            enc.setActivo('0');
        }
        enc.setRetail_rut(lbl_rutRetail.getText());

        crud_enc.Agregar_Encargado(enc);
    }

    //Metodo eliminar producto
    public void modificar() {
        crud_enc = new CRUDencargado();
        Encargado enc = new Encargado();

        enc.setP_nombre(txt_nombre1.getText());
        enc.setS_nombre(txt_nombre2.getText());
        enc.setApellido_p(txt_apellido1.getText());
        enc.setApellido_m(txt_apellido2.getText());
        enc.setClave(txt_password.getText());
        if (chk_activo.isSelected()) {
            enc.setActivo('1');
        } else {
            enc.setActivo('0');
        }
        enc.setRetail_rut(lbl_rutRetail.getText());
        String rutsinDigito = txt_rut.getText().substring(0, txt_rut.getText().length() - 1);
        String rutDigito = txt_rut.getText().substring(txt_rut.getText().length() - 1);
        String rutFinal = rutsinDigito + "-" + rutDigito;
        enc.setRun(rutFinal);

        crud_enc.Modificar_Encargado(enc);
    }

    //Metodo eliminar producto
    public void eliminar() {
        crud_enc = new CRUDencargado();
        Encargado enc = new Encargado();
        String rutsinDigito = txt_rut.getText().substring(0, txt_rut.getText().length() - 1);
        String rutDigito = txt_rut.getText().substring(txt_rut.getText().length() - 1);
        String rutFinal = rutsinDigito + "-" + rutDigito;
        enc.setRun(rutFinal);

        crud_enc.Eliminar_Encargado(enc);
    }

    public void activa_boton(boolean a1, boolean a2, boolean a3, boolean a4) {
        btn_agregar.setEnabled(a1);
        btn_modificar.setEnabled(a2);
        btn_eliminar.setEnabled(a3);
        txt_rut.setEnabled(a4);
    }

    public String obtenerRutRetailEncargado(String nombreRetail) {
        ArrayList<String> list = new ArrayList<String>();
        String[] rrtl = new String[1];
        //  ArrayList<Object> list2 = new ArrayList<Object>();

        Database cn = new Database();
        String sql = "select rut from retail where razon_social like '" + nombreRetail + "'"; //scar numero
        ResultSet lista = null;
        PreparedStatement ps = null;
        String rutRet = "";
        try {
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while (lista.next()) {
                rrtl[0] = lista.getString("rut");
                rutRet = rrtl[0];
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
        // System.out.println(rutRet);
        return rutRet;
    }

    public int entregarPosicionRetail(String rutRetail) {

        ArrayList<String> list = new ArrayList<String>();
        String[] rrtl = new String[1];
        //  ArrayList<Object> list2 = new ArrayList<Object>();

        Database cn = new Database();
        String sql = "select rut from retail order by razon_social asc"; //selecciona todos los registros
        ResultSet lista = null;
        PreparedStatement ps = null;
        int posicion = 1;
        int prtl = 0;
        String nombre = "";
        try {
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while (lista.next()) {
                nombre = lista.getString("rut");
                if (nombre.equals(rutRetail)) {
                    prtl = prtl + posicion;
                } else {
                    prtl = prtl + 0;
                }
                posicion++;
                //idCom = lista.getInt(1);  //entrega el id
                System.out.println(prtl);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tab_encargados = new javax.swing.JTable();
        panel = new javax.swing.JPanel();
        btn_agregar = new javax.swing.JButton();
        lbl_clave = new javax.swing.JLabel();
        lbl_apellidos = new javax.swing.JLabel();
        txt_apellido2 = new javax.swing.JTextField();
        txt_nombre1 = new javax.swing.JTextField();
        txt_rut = new javax.swing.JTextField();
        lbl_rut = new javax.swing.JLabel();
        lbl_nombres = new javax.swing.JLabel();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        lbl_activo = new javax.swing.JLabel();
        txt_nombre2 = new javax.swing.JTextField();
        txt_apellido1 = new javax.swing.JTextField();
        chk_activo = new javax.swing.JCheckBox();
        lbl_retail = new javax.swing.JLabel();
        cb_retail = new javax.swing.JComboBox<>();
        lbl_rutRetail = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        btn_salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Encargados");

        tab_encargados.setBackground(new java.awt.Color(153, 153, 153));
        tab_encargados.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        tab_encargados.setModel(new javax.swing.table.DefaultTableModel(
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
        tab_encargados.getTableHeader().setReorderingAllowed(false);
        tab_encargados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_encargadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tab_encargados);

        panel.setBackground(new java.awt.Color(153, 153, 153));
        panel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel.setForeground(new java.awt.Color(153, 153, 153));

        btn_agregar.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        lbl_clave.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lbl_clave.setText("Clave:");

        lbl_apellidos.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lbl_apellidos.setText("Apellidos:");

        txt_apellido2.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        txt_apellido2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_apellido2KeyTyped(evt);
            }
        });

        txt_nombre1.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        txt_nombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombre1ActionPerformed(evt);
            }
        });
        txt_nombre1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombre1KeyTyped(evt);
            }
        });

        txt_rut.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
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

        lbl_rut.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        lbl_rut.setText("RUT");

        lbl_nombres.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lbl_nombres.setText("Nombres:");

        btn_modificar.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });

        btn_eliminar.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_limpiar.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        btn_limpiar.setText("Limpiar");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        lbl_activo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_activo.setText("Activo:");

        txt_nombre2.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        txt_nombre2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombre2KeyTyped(evt);
            }
        });

        txt_apellido1.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        txt_apellido1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_apellido1KeyTyped(evt);
            }
        });

        chk_activo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chk_activo.setSelected(true);
        chk_activo.setText("SI/NO");
        chk_activo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_activoActionPerformed(evt);
            }
        });

        lbl_retail.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        lbl_retail.setText("Retail");

        cb_retail.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        cb_retail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_retailActionPerformed(evt);
            }
        });

        txt_password.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        txt_password.setText("jPasswordField1");
        txt_password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_passwordFocusLost(evt);
            }
        });
        txt_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_passwordKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_rut)
                            .addComponent(lbl_nombres))
                        .addGap(10, 10, 10)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(txt_rut)
                                .addGap(152, 152, 152))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(txt_nombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_nombre2))))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(btn_agregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(btn_eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_limpiar))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_apellidos)
                            .addComponent(lbl_clave)
                            .addComponent(lbl_retail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_apellido1)
                            .addComponent(txt_password)
                            .addComponent(cb_retail, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_apellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(lbl_activo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chk_activo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_rutRetail)))))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_rut)
                    .addComponent(txt_rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nombres)
                    .addComponent(txt_nombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nombre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_apellidos)
                        .addComponent(txt_apellido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_apellido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_clave)
                            .addComponent(lbl_activo)
                            .addComponent(chk_activo)
                            .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(lbl_rutRetail)
                        .addGap(6, 6, 6)))
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_retail)
                    .addComponent(cb_retail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_agregar)
                    .addComponent(btn_modificar)
                    .addComponent(btn_eliminar)
                    .addComponent(btn_limpiar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_salir.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_salir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        int errores = 0;
        if (txt_rut.getText().equals("") || txt_nombre1.getText().equals("") || txt_nombre2.getText().equals("") || txt_apellido1.getText().equals("")
                || txt_apellido2.getText().equals("") || txt_password.getText().equals("") || rutCorrecto == false || contraCorrecta == false) {
            errores = errores + 1;
        }

        if (errores >= 1) {
            JOptionPane.showMessageDialog(null, "Ingrese informacion faltante en el formulario", "Aviso", JOptionPane.ERROR_MESSAGE);
        } else {
            agregar();
            te.visualizar_Encargado(tab_encargados);
            activa_boton(true, false, false, true);
            lim.limpiar_texto(panel);
        }


    }//GEN-LAST:event_btn_agregarActionPerformed

    private void txt_nombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombre1ActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        int errores = 0;
        if (txt_rut.getText().equals("") || txt_nombre1.getText().equals("") || txt_nombre2.getText().equals("") || txt_apellido1.getText().equals("")
                || txt_apellido2.getText().equals("") || txt_password.getText().equals("")) {
            errores = errores + 1;
        }

        if (errores >= 1) {
            JOptionPane.showMessageDialog(null, "Ingrese informacion faltante en el formulario", "Aviso", JOptionPane.ERROR_MESSAGE);
        } else {
            modificar();
            te.visualizar_Encargado(tab_encargados);
            activa_boton(true, false, false, true);

            lim.limpiar_texto(panel);
        }


    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed

        int s = JOptionPane.showConfirmDialog(null, "Eliminar encargado", "Si/no", JOptionPane.YES_NO_OPTION);
        if (s == 0) {
            eliminar();
            te.visualizar_Encargado(tab_encargados);
            activa_boton(true, false, false, true);
            lim.limpiar_texto(panel);
        }

    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        lim.limpiar_texto(panel);
        activa_boton(true, false, false, true);
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void tab_encargadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_encargadosMouseClicked
        clic_tabla = this.tab_encargados.rowAtPoint(evt.getPoint());

        String run = "" + tab_encargados.getValueAt(clic_tabla, 0);
        String p_nombre = "" + tab_encargados.getValueAt(clic_tabla, 1);
        String s_nombre = "" + tab_encargados.getValueAt(clic_tabla, 2);
        String apellido_p = "" + tab_encargados.getValueAt(clic_tabla, 3);
        String apellido_m = "" + tab_encargados.getValueAt(clic_tabla, 4);
        String clave = "" + tab_encargados.getValueAt(clic_tabla, 5);

        String activoStr = "" + tab_encargados.getValueAt(clic_tabla, 6);
        char activo;
        if (activoStr.equals("Si")) {
            activo = '1';
        } else {
            activo = '0';
        }

        String retail_rut = "" + tab_encargados.getValueAt(clic_tabla, 7);

        //String rutAcortado = run.substring(0,8);
        String rutAcortado = run.substring(0, run.indexOf("-"));
        String digitoRut = run.substring(run.length() - 1);

        txt_rut.setText(rutAcortado + digitoRut);
        txt_nombre1.setText(p_nombre);
        txt_nombre2.setText(s_nombre);
        txt_apellido1.setText(apellido_p);
        txt_apellido2.setText(apellido_m);
        txt_password.setText(String.valueOf(clave));

        if (activo == '1') {
            chk_activo.setSelected(true);
        } else {
            chk_activo.setSelected(false);
        }

        int ene = entregarPosicionRetail(obtenerRutRetailEncargado(retail_rut));

        //System.out.println(entregarPosicionRetail(obtenerRutRetailEncargado(retail_rut)));
        cb_retail.setSelectedIndex(entregarPosicionRetail(obtenerRutRetailEncargado(retail_rut)) - 1);

        int column = tab_encargados.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tab_encargados.getRowHeight();

        if (row < tab_encargados.getRowCount() && row >= 0 && column < tab_encargados.getColumnCount() && column >= 0) {
            Object value = tab_encargados.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

                if (boton.getName().equals("mod")) {
                    System.out.println("Click en el boton modificar");
                    //EVENTOS MODIFICAR
                    activa_boton(true, true, false, false);
                }
                if (boton.getName().equals("eli")) {
                    //JOptionPane.showConfirmDialog(null, "Desea eliminar este registro", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
                    System.out.println("Click en el boton eliminar");
                    //EVENTOS ELIMINAR
                    activa_boton(true, false, true, false);
                }
            }

        }
    }//GEN-LAST:event_tab_encargadosMouseClicked

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        new vista_Admin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void chk_activoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_activoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_activoActionPerformed

    private void cb_retailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_retailActionPerformed
        lbl_rutRetail.hide();
        lbl_rutRetail.setText(cb_retail.getItemAt(cb_retail.getSelectedIndex()).getRut());
    }//GEN-LAST:event_cb_retailActionPerformed

    private void txt_rutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rutKeyTyped

        int maximorut = 9;
        int errores = 0;
        char validarCaracter = evt.getKeyChar();
        ArrayList<Character> lista = retornarListaCaracteres();

        for (int i = 0; i < lista.size(); i++) {
            char caracter = lista.get(i);
            if (validarCaracter == caracter) {
                errores = errores + 1;
            }
        }

        if ((Character.isLetter(validarCaracter) && validarCaracter != 'k') || txt_rut.getText().length() >= maximorut || errores > 0) {
            evt.consume();
        }

    }//GEN-LAST:event_txt_rutKeyTyped

    private void txt_nombre1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombre1KeyTyped

        int maximoCaracter = 15;
        char validarCaracter = evt.getKeyChar();
        if ((validarCaracter < 'a' || validarCaracter > 'z') && (validarCaracter < 'A' || validarCaracter > 'Z') || txt_nombre1.getText().length() >= maximoCaracter) {
            evt.consume();
        }

    }//GEN-LAST:event_txt_nombre1KeyTyped

    private void txt_nombre2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombre2KeyTyped
        int maximoCaracter = 15;
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') || txt_nombre2.getText().length() >= maximoCaracter) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_nombre2KeyTyped

    private void txt_apellido1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellido1KeyTyped
        int maximoCaracter = 15;
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') || txt_apellido1.getText().length() >= maximoCaracter) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_apellido1KeyTyped

    private void txt_apellido2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellido2KeyTyped
        int maximoCaracter = 15;
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') || txt_apellido2.getText().length() >= maximoCaracter) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_apellido2KeyTyped

    private void txt_passwordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyTyped
        int maximoCaracter = 24;
        char c = evt.getKeyChar();
        if (txt_password.getText().length() >= maximoCaracter) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_passwordKeyTyped

    private void txt_rutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rutFocusLost

        int minimo = 7;
        if (txt_rut.getText().length() > 0) {
            if (txt_rut.getText().length() < minimo) {
                txt_rut.setForeground(Color.red);
                JOptionPane.showMessageDialog(null, "Ingrese rut con su maximo de caracteres correcto EJ:(123456781)", "Aviso", JOptionPane.ERROR_MESSAGE);
                rutCorrecto = false;
            } else {
                txt_rut.setForeground(Color.black);
                rutCorrecto = true;
            }
        }

    }//GEN-LAST:event_txt_rutFocusLost

    private void txt_passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_passwordFocusLost
        int minimo = 8;
        if (txt_password.getText().length() > 0) {
            if (txt_password.getText().length() < minimo) {
                txt_password.setForeground(Color.red);
                JOptionPane.showMessageDialog(null, "Minimo de caracteres de clave es 8", "Aviso", JOptionPane.ERROR_MESSAGE);
                contraCorrecta = false;
            } else {
                txt_password.setForeground(Color.black);
                contraCorrecta = true;
            }
        }

    }//GEN-LAST:event_txt_passwordFocusLost

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JComboBox<Retail> cb_retail;
    private javax.swing.JCheckBox chk_activo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_activo;
    private javax.swing.JLabel lbl_apellidos;
    private javax.swing.JLabel lbl_clave;
    private javax.swing.JLabel lbl_nombres;
    private javax.swing.JLabel lbl_retail;
    private javax.swing.JLabel lbl_rut;
    private javax.swing.JLabel lbl_rutRetail;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tab_encargados;
    private javax.swing.JTextField txt_apellido1;
    private javax.swing.JTextField txt_apellido2;
    private javax.swing.JTextField txt_nombre1;
    private javax.swing.JTextField txt_nombre2;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_rut;
    // End of variables declaration//GEN-END:variables

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

}

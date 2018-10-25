/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Conexion.Database;
import Clases.*;
import Tablas.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raul
 */
public class vista_Consumidores extends javax.swing.JFrame {

    static home login = new home();

    Tabla_Consumidor tc = new Tabla_Consumidor();
    Limpiar lim = new Limpiar();
    CRUDconsumidor crud_cons;
    int clic_tabla = 0;
    boolean rutCorrecto = false;
    boolean contraCorrecta = false;

    /**
     * Creates new form listaProductos
     */
    public vista_Consumidores() {
        initComponents();
        
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        
        setResizable(false);
        
        txt_password.setText("");
        mensajePanelInfo(false);
        tc.visualizar_Consumidor(tab_consumidores);
        Comuna comuna = new Comuna();
        comuna.mostrarComuna(cb_comuna);
        activa_boton(true, false, false, true, false, false);
    }

    public void agregar() throws Exception {

        try {
            crud_cons = new CRUDconsumidor();
            Consumidor cons = new Consumidor();

            String rutsinDigito = txt_rut.getText().substring(0, txt_rut.getText().length() - 1);
            String rutDigito = txt_rut.getText().substring(txt_rut.getText().length() - 1);
            String rutFinal = rutsinDigito + "-" + rutDigito;

            cons.setRun(rutFinal);
            cons.setP_nombre(txt_nombre1.getText());
            cons.setS_nombre(txt_nombre2.getText());
            cons.setApellido_p(txt_apellido1.getText());
            cons.setApellido_m(txt_apellido2.getText());
            cons.setEmail(txt_email.getText());
            cons.setClave(txt_password.getText());
            cons.setPuntaje(Integer.parseInt(txt_puntaje.getText()));
            if (chk_activo.isSelected()) {
                cons.setActivo('1');
            } else {
                cons.setActivo('0');
            }
            if (chk_suscrito.isSelected()) {
                cons.setSuscrito('1');
            } else {
                cons.setSuscrito('0');
            }
            cons.setComuna_id(Integer.parseInt(lbl_comuna_id.getText()));

            crud_cons.Agregar_Consumidor(cons);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    //Metodo eliminar producto
    public void modificar() {

        int errores = 0;
        if (txt_rut.getText().equals("") || txt_puntaje.getText().equals("") || txt_nombre1.getText().equals("")
                || txt_nombre2.getText().equals("") || txt_apellido1.getText().equals("") || txt_apellido2.getText().equals("")
                || txt_email.getText().equals("") || txt_password.getText().equals("")) {
            errores = errores + 1;
        }

        if (errores >= 1) {
            JOptionPane.showMessageDialog(null, "Ingrese informacion faltante en el formulario", "Aviso", JOptionPane.ERROR_MESSAGE);
        } else {
            crud_cons = new CRUDconsumidor();
            Consumidor cons = new Consumidor();

            cons.setP_nombre(txt_nombre1.getText());
            cons.setS_nombre(txt_nombre2.getText());
            cons.setApellido_p(txt_apellido1.getText());
            cons.setApellido_m(txt_apellido2.getText());
            cons.setEmail(txt_email.getText());
            cons.setClave(txt_password.getText());
            cons.setPuntaje(Integer.parseInt(txt_puntaje.getText()));
            if (chk_activo.isSelected()) {
                cons.setActivo('1');
            } else {
                cons.setActivo('0');
            }
            if (chk_suscrito.isSelected()) {
                cons.setSuscrito('1');
            } else {
                cons.setSuscrito('0');
            }
            cons.setComuna_id(Integer.parseInt(lbl_comuna_id.getText()));

            String rutsinDigito = txt_rut.getText().substring(0, txt_rut.getText().length() - 1);
            String rutDigito = txt_rut.getText().substring(txt_rut.getText().length() - 1);
            String rutFinal = rutsinDigito + "-" + rutDigito;

            cons.setRun(rutFinal);
            crud_cons.Modificar_Consumidor(cons);
            txt_rut.setEnabled(true);
            

        }

    }

    //Metodo eliminar producto
    public void eliminar() {
        crud_cons = new CRUDconsumidor();
        Consumidor cons = new Consumidor();

        String rutsinDigito = txt_rut.getText().substring(0, txt_rut.getText().length() - 1);
        String rutDigito = txt_rut.getText().substring(txt_rut.getText().length() - 1);
        String rutFinal = rutsinDigito + "-" + rutDigito;

        cons.setRun(rutFinal);

        crud_cons.Eliminar_Consumidor(cons);
        

    }

    public void activa_boton(boolean a1, boolean a2, boolean a3, boolean a4, boolean a5, boolean a6) {
        btn_agregar.setEnabled(a1);
        btn_modificar.setEnabled(a2);
        btn_eliminar.setEnabled(a3);
        txt_rut.setEnabled(a4);
        btn_modificar.setVisible(a5);
        btn_eliminar.setVisible(a6);

    }

    public int obtenerIdComunaConsumidor(String nombreComuna) {
        ArrayList<String> list = new ArrayList<String>();
        int[] numCom = new int[1];
        //  ArrayList<Object> list2 = new ArrayList<Object>();

        Database cn = new Database();
        String sql = "select id from comuna where nombre like '" + nombreComuna + "'"; //scar numero
        ResultSet lista = null;
        PreparedStatement ps = null;
        int idCom = 0;
        try {
            ps = cn.getConnection().prepareStatement(sql);
            lista = ps.executeQuery();
            while (lista.next()) {
                numCom[0] = lista.getInt(1);
                idCom = numCom[0];
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
        System.out.println(idCom);
        return idCom;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_consumidores = new javax.swing.JTable();
        pInfo = new javax.swing.JPanel();
        lblInfo = new javax.swing.JLabel();
        lblmostrarNomreCom = new javax.swing.JLabel();
        lblnombreCompleto = new javax.swing.JLabel();
        lblMostrarContra = new javax.swing.JLabel();
        lblContra = new javax.swing.JLabel();
        lnlmostrarCoomuna = new javax.swing.JLabel();
        lblInfoConsumi = new javax.swing.JLabel();
        lblComuna = new javax.swing.JLabel();
        lblActividad = new javax.swing.JLabel();
        lblmostrarSubcrip = new javax.swing.JLabel();
        lblSuscrito = new javax.swing.JLabel();
        lblApellidoscom = new javax.swing.JLabel();
        lblRutMostrar = new javax.swing.JLabel();
        lblRut = new javax.swing.JLabel();
        lblMostraremail = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        btn_agregar = new javax.swing.JButton();
        lbl_clave = new javax.swing.JLabel();
        lbl_apellidos = new javax.swing.JLabel();
        txt_apellido2 = new javax.swing.JTextField();
        txt_nombre1 = new javax.swing.JTextField();
        txt_rut = new javax.swing.JTextField();
        lbl_rut = new javax.swing.JLabel();
        lbl_nombres = new javax.swing.JLabel();
        btn_limpiar = new javax.swing.JButton();
        lbl_activo = new javax.swing.JLabel();
        txt_nombre2 = new javax.swing.JTextField();
        txt_apellido1 = new javax.swing.JTextField();
        chk_activo = new javax.swing.JCheckBox();
        lbl_comuna = new javax.swing.JLabel();
        cb_comuna = new javax.swing.JComboBox<>();
        lbl_comuna_id = new javax.swing.JLabel();
        lbl_suscrito = new javax.swing.JLabel();
        chk_suscrito = new javax.swing.JCheckBox();
        lbl_email = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        lbl_puntaje = new javax.swing.JLabel();
        txt_puntaje = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consumidores");
        setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        tab_consumidores.setBackground(new java.awt.Color(204, 204, 204));
        tab_consumidores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
        tab_consumidores.setModel(new javax.swing.table.DefaultTableModel(
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
        tab_consumidores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_consumidoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tab_consumidores);

        pInfo.setBackground(new java.awt.Color(204, 204, 204));
        pInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));

        lblInfo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblInfo.setText("Informacion detallada del consumidor:");

        lblmostrarNomreCom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblmostrarNomreCom.setText("Nombre completo:");

        lblnombreCompleto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblnombreCompleto.setText("NombresCompleto");

        lblMostrarContra.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblMostrarContra.setText("Contraseña:");

        lblContra.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblContra.setText("Contraseña");

        lnlmostrarCoomuna.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lnlmostrarCoomuna.setText("Comuna:");

        lblInfoConsumi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblInfoConsumi.setText("El consumidor se encuentra");

        lblComuna.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblComuna.setText("comuna");

        lblActividad.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblActividad.setText("actividad");

        lblmostrarSubcrip.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblmostrarSubcrip.setText("y");

        lblSuscrito.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSuscrito.setText("Subcrip");

        lblApellidoscom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblApellidoscom.setText("ApellidosCompleto");

        lblRutMostrar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblRutMostrar.setText("Rut:");

        lblRut.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblRut.setText("rutConsu");

        lblMostraremail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblMostraremail.setText("Email:");

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblEmail.setText("EmailMostrar");

        javax.swing.GroupLayout pInfoLayout = new javax.swing.GroupLayout(pInfo);
        pInfo.setLayout(pInfoLayout);
        pInfoLayout.setHorizontalGroup(
            pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInfoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pInfoLayout.createSequentialGroup()
                        .addComponent(lblRutMostrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblRut))
                    .addGroup(pInfoLayout.createSequentialGroup()
                        .addComponent(lblInfoConsumi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblActividad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblmostrarSubcrip)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSuscrito))
                    .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pInfoLayout.createSequentialGroup()
                        .addComponent(lnlmostrarCoomuna)
                        .addGap(18, 18, 18)
                        .addComponent(lblComuna))
                    .addGroup(pInfoLayout.createSequentialGroup()
                        .addComponent(lblMostrarContra)
                        .addGap(18, 18, 18)
                        .addComponent(lblContra))
                    .addGroup(pInfoLayout.createSequentialGroup()
                        .addComponent(lblMostraremail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEmail))
                    .addGroup(pInfoLayout.createSequentialGroup()
                        .addComponent(lblmostrarNomreCom)
                        .addGap(18, 18, 18)
                        .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblApellidoscom)
                            .addComponent(lblnombreCompleto))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        pInfoLayout.setVerticalGroup(
            pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRutMostrar)
                    .addComponent(lblRut))
                .addGap(18, 18, 18)
                .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmostrarNomreCom)
                    .addComponent(lblnombreCompleto))
                .addGap(18, 18, 18)
                .addComponent(lblApellidoscom)
                .addGap(15, 15, 15)
                .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMostraremail)
                    .addComponent(lblEmail))
                .addGap(20, 20, 20)
                .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMostrarContra)
                    .addComponent(lblContra))
                .addGap(18, 18, 18)
                .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lnlmostrarCoomuna)
                    .addComponent(lblComuna))
                .addGap(18, 18, 18)
                .addGroup(pInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfoConsumi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblActividad)
                    .addComponent(lblmostrarSubcrip)
                    .addComponent(lblSuscrito))
                .addGap(55, 55, 55))
        );

        panel.setBackground(new java.awt.Color(204, 204, 204));
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));

        btn_agregar.setBackground(new java.awt.Color(0, 255, 0));
        btn_agregar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        lbl_clave.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_clave.setText("Clave:");

        lbl_apellidos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_apellidos.setText("Apellidos:");

        txt_apellido2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_apellido2KeyTyped(evt);
            }
        });

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

        txt_rut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_rutFocusLost(evt);
            }
        });
        txt_rut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rutActionPerformed(evt);
            }
        });
        txt_rut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_rutKeyTyped(evt);
            }
        });

        lbl_rut.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_rut.setText("RUT: ");

        lbl_nombres.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_nombres.setText("Nombres:");

        btn_limpiar.setBackground(new java.awt.Color(255, 255, 255));
        btn_limpiar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_limpiar.setText("Limpiar");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        lbl_activo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_activo.setText("Activo:");

        txt_nombre2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombre2KeyTyped(evt);
            }
        });

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

        lbl_comuna.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_comuna.setText("Comuna:");

        cb_comuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_comunaActionPerformed(evt);
            }
        });

        lbl_suscrito.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_suscrito.setText("Suscrito:");

        chk_suscrito.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chk_suscrito.setSelected(true);
        chk_suscrito.setText("SI/NO");
        chk_suscrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_suscritoActionPerformed(evt);
            }
        });

        lbl_email.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_email.setText("Email:");

        txt_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_emailFocusLost(evt);
            }
        });
        txt_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_emailKeyTyped(evt);
            }
        });

        lbl_puntaje.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_puntaje.setText("Puntaje:");

        txt_puntaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_puntajeKeyTyped(evt);
            }
        });

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

        btn_modificar.setBackground(new java.awt.Color(255, 255, 0));
        btn_modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });

        btn_eliminar.setBackground(new java.awt.Color(255, 0, 0));
        btn_eliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_salir.setText("Volver");
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_comuna_id)
                .addGap(857, 857, 857))
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_rut)
                                    .addComponent(lbl_nombres))
                                .addGap(20, 20, 20)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(txt_rut, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbl_puntaje)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_puntaje))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(txt_nombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_nombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(txt_apellido1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_apellido2)
                                            .addGroup(panelLayout.createSequentialGroup()
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(panelLayout.createSequentialGroup()
                                                        .addComponent(lbl_suscrito)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(chk_suscrito))
                                                    .addGroup(panelLayout.createSequentialGroup()
                                                        .addGap(71, 71, 71)
                                                        .addComponent(chk_activo))
                                                    .addComponent(lbl_activo))
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txt_password, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                            .addComponent(txt_email, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cb_comuna, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(57, 57, 57))))
                            .addComponent(lbl_apellidos)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_email)
                            .addComponent(lbl_comuna)
                            .addComponent(lbl_clave)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_rut)
                    .addComponent(txt_rut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_puntaje)
                    .addComponent(txt_puntaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nombres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_nombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nombre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_apellidos)
                    .addComponent(txt_apellido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_apellido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_clave)
                    .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_activo)
                    .addComponent(chk_activo))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_email)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_suscrito)
                    .addComponent(chk_suscrito))
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_comuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_comuna)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btn_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_salir)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_agregar)
                        .addComponent(btn_modificar)
                        .addComponent(btn_eliminar)))
                .addGap(117, 117, 117)
                .addComponent(lbl_comuna_id))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 680));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        new vista_Admin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed

        int s = JOptionPane.showConfirmDialog(null, "Eliminar consumidor", "Si/no", JOptionPane.YES_NO_OPTION);
        if (s == 0) {
            eliminar();
            tc.visualizar_Consumidor(tab_consumidores);
            activa_boton(true, false, false, true, false, false);
            lim.limpiar_texto(panel);
            mensajePanelInfo(false);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        modificar();
        tc.visualizar_Consumidor(tab_consumidores);
        activa_boton(true, false, false, true, false, false);
        lim.limpiar_texto(panel);
        mensajePanelInfo(false);
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void txt_passwordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passwordKeyTyped
        // TODO add your handling code here:
        int maximoCaracter = 25;
        char validarCaracter = evt.getKeyChar();
        if (txt_password.getText().length() >= maximoCaracter) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Contraseña maximo de 25 caracteres", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txt_passwordKeyTyped

    private void txt_puntajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_puntajeKeyTyped
        // TODO add your handling code here:
        int maximoPuntaje = 9;
        ArrayList<Character> lista = retornarListaCaracteres();
        int errores = 0;
        char validarCaracter = evt.getKeyChar();
        for (int i = 0; i < lista.size(); i++) {
            char caracter = lista.get(i);
            if (validarCaracter == caracter) {
                errores = errores + 1;
            }
        }

        if (Character.isLetter(validarCaracter) || errores > 0 || txt_puntaje.getText().length() >= maximoPuntaje) {
            evt.consume();
        }

    }//GEN-LAST:event_txt_puntajeKeyTyped

    private void txt_emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_emailKeyTyped
        int maximoCaracter = 30;
        char validarCaracter = evt.getKeyChar();
        if (txt_email.getText().length() >= maximoCaracter) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_emailKeyTyped

    private void txt_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_emailFocusLost
        // TODO add your handling code here:
        if (isEmail(txt_email.getText())) {
            txt_email.setForeground(Color.black);
        } else {
            txt_email.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, "Correo con formato incorrecto", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txt_emailFocusLost

    private void chk_suscritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_suscritoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_suscritoActionPerformed

    private void cb_comunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_comunaActionPerformed
        lbl_comuna_id.hide();
        lbl_comuna_id.setText("" + cb_comuna.getItemAt(cb_comuna.getSelectedIndex()).getId());
    }//GEN-LAST:event_cb_comunaActionPerformed

    private void chk_activoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_activoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_activoActionPerformed

    private void txt_apellido1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellido1KeyTyped
        // TODO add your handling code here:
        int maximoCaracter = 15;
        char validarCaracter = evt.getKeyChar();
        if ((validarCaracter < 'a' || validarCaracter > 'z') && (validarCaracter < 'A' || validarCaracter > 'Z') || txt_apellido1.getText().length() >= maximoCaracter) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_apellido1KeyTyped

    private void txt_nombre2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombre2KeyTyped
        int maximoCaracter = 15;
        char validarCaracter = evt.getKeyChar();
        if ((validarCaracter < 'a' || validarCaracter > 'z') && (validarCaracter < 'A' || validarCaracter > 'Z') || txt_nombre2.getText().length() >= maximoCaracter) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_nombre2KeyTyped

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed

        lim.limpiar_texto(panel);
        activa_boton(true, false, false, true, false, false);
        mensajePanelInfo(false);
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void txt_rutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rutKeyTyped
        // TODO add your handling code here:
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

    private void txt_rutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rutActionPerformed

    private void txt_nombre1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombre1KeyTyped
        // TODO add your handling code here:
        int maximoCaracter = 15;
        char validarCaracter = evt.getKeyChar();
        if ((validarCaracter < 'a' || validarCaracter > 'z') && (validarCaracter < 'A' || validarCaracter > 'Z') || txt_nombre1.getText().length() >= maximoCaracter) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_nombre1KeyTyped

    private void txt_nombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombre1ActionPerformed

    private void txt_apellido2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellido2KeyTyped
        // TODO add your handling code here:
        int maximoCaracter = 15;
        char validarCaracter = evt.getKeyChar();
        if ((validarCaracter < 'a' || validarCaracter > 'z') && (validarCaracter < 'A' || validarCaracter > 'Z') || txt_apellido2.getText().length() >= maximoCaracter) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_apellido2KeyTyped

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed

        int errores = 0;
        if (txt_rut.getText().equals("") || txt_puntaje.getText().equals("") || txt_nombre1.getText().equals("")
            || txt_nombre2.getText().equals("") || txt_apellido1.getText().equals("") || txt_apellido2.getText().equals("")
            || txt_email.getText().equals("") || txt_password.getText().equals("") || rutCorrecto==false || contraCorrecta==false) {
            errores = errores + 1;
        }

        if (errores >= 1) {
            JOptionPane.showMessageDialog(null, "Ingrese informacion faltante en el formulario", "Aviso", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                agregar();
            } catch (Exception ex) {
                Logger.getLogger(vista_Consumidores.class.getName()).log(Level.SEVERE, null, ex);
            }
            tc.visualizar_Consumidor(tab_consumidores);
            activa_boton(true, false, false, true, false, false);
            lim.limpiar_texto(panel);
            mensajePanelInfo(false);
        }

    }//GEN-LAST:event_btn_agregarActionPerformed

    private void tab_consumidoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_consumidoresMouseClicked
        clic_tabla = this.tab_consumidores.rowAtPoint(evt.getPoint());
        pInfo.setVisible(true);
        String run = "" + tab_consumidores.getValueAt(clic_tabla, 0);
        String p_nombre = "" + tab_consumidores.getValueAt(clic_tabla, 1);
        String s_nombre = "" + tab_consumidores.getValueAt(clic_tabla, 2);
        String apellido_p = "" + tab_consumidores.getValueAt(clic_tabla, 3);
        String apellido_m = "" + tab_consumidores.getValueAt(clic_tabla, 4);
        String email = "" + tab_consumidores.getValueAt(clic_tabla, 5);
        String clave = "" + tab_consumidores.getValueAt(clic_tabla, 6);
        int puntaje = (int) tab_consumidores.getValueAt(clic_tabla, 7);
        String activoStr = "" + tab_consumidores.getValueAt(clic_tabla, 8);
        char activo;
        if (activoStr.equals("Si")) {
            activo = '1';
        } else {
            activo = '0';
        }
        // char activo = (char)tab_consumidores.getValueAt(clic_tabla, 8);
        String suscritoStr = "" + tab_consumidores.getValueAt(clic_tabla, 9);
        char suscrito;
        if (suscritoStr.equals("Si")) {
            suscrito = '1';
        } else {
            suscrito = '0';
        }
        // char suscrito = (char)tab_consumidores.getValueAt(clic_tabla, 9);
        String comuna_id = "" + tab_consumidores.getValueAt(clic_tabla, 10);

        System.out.println(comuna_id);
        String rutAcortado = run.substring(0, run.indexOf("-"));
        String digitoRut = run.substring(run.length() - 1);
        String rutFinal=rutAcortado + digitoRut;

        // int comuna = comuna_id;
        txt_rut.setText(rutFinal);
        txt_nombre1.setText(p_nombre);
        txt_nombre2.setText(s_nombre);
        txt_apellido1.setText(apellido_p);
        txt_apellido2.setText(apellido_m);
        txt_email.setText(email);
        txt_password.setText(clave);
        txt_puntaje.setText("" + puntaje);

        String comuna = String.valueOf(cb_comuna.getSelectedItem());

        mostrarInfoDetallada(rutFinal,p_nombre,s_nombre,apellido_p,apellido_m,clave,comuna,activo,suscrito,email);

        if (activo == '1') {
            chk_activo.setSelected(true);
        } else {
            chk_activo.setSelected(false);
        }
        if (suscrito == '1') {
            chk_suscrito.setSelected(true);
        } else {
            chk_suscrito.setSelected(false);
        }

        ////////COMBOBOX REFERENCIADO
        cb_comuna.setSelectedIndex(obtenerIdComunaConsumidor(comuna_id) - 1);

        int column = tab_consumidores.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tab_consumidores.getRowHeight();

        if (row < tab_consumidores.getRowCount() && row >= 0 && column < tab_consumidores.getColumnCount() && column >= 0) {
            Object value = tab_consumidores.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

                if (boton.getName().equals("mod")) {
                    System.out.println("Click en el boton modificar");
                    //EVENTOS MODIFICAR
                    activa_boton(true, true, false, false, true, false);
                }
                if (boton.getName().equals("eli")) {
                    //JOptionPane.showConfirmDialog(null, "Desea eliminar este registro", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
                    System.out.println("Click en el boton eliminar");
                    //EVENTOS ELIMINAR
                    activa_boton(true, false, true, false, false, true);
                }
            }
        }
    }//GEN-LAST:event_tab_consumidoresMouseClicked

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

    private void txt_passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_passwordFocusLost
        int minimo=8;
        if(txt_password.getText().length()<minimo){
            txt_password.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, "Minimo de caracteres de rut es 8", "Aviso", JOptionPane.ERROR_MESSAGE);
            contraCorrecta=false;
        }else{
            txt_password.setForeground(Color.black);
            contraCorrecta=true;
        }
    }//GEN-LAST:event_txt_passwordFocusLost

    public boolean isEmail(String correo) {

        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[\\w\\\\\\+]+(\\.[\\w\\\\]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            return true;
        } else {
            return false;
        }

    }

    public void mostrarInfoDetallada(String rut, String nombre1, String nombre2, String apellido1, String apellido2, String contra, String comuna, char activo, char suscri,String email) {
        mensajePanelInfo(true);
        lblRut.setText(rut);
        lblnombreCompleto.setText(nombre1 + " " + nombre2);
        lblApellidoscom.setText(apellido1 + " " + apellido2);
        lblContra.setText(contra);
        lblComuna.setText(comuna);
        lblEmail.setText(email);
        if (activo == '1') {
            lblActividad.setText("Activo");
        } else {
            lblActividad.setText("No activo");
        }
        if (suscri == '1') {
            lblSuscrito.setText("Suscrito");
        } else {
            lblSuscrito.setText("No suscrito");
        }
    }

    public void mensajePanelInfo(boolean vali){
        if(vali==false){
        lblInfo.setText("Seleccione item para visualizar");
        lblRutMostrar.setVisible(false);
        lblRut.setVisible(false);
        lblmostrarNomreCom.setVisible(false);
        lblnombreCompleto.setVisible(false);
        lblApellidoscom.setVisible(false);
        lblMostraremail.setVisible(false);
        lblEmail.setVisible(false);
        lblMostrarContra.setVisible(false);
        lblContra.setVisible(false);
        lnlmostrarCoomuna.setVisible(false);
        lblComuna.setVisible(false);
        lblInfoConsumi.setVisible(false);
        lblActividad.setVisible(false);
        lblmostrarSubcrip.setVisible(false);
        lblSuscrito.setVisible(false);       
        }else{
        lblInfo.setText("Informacion detallada del consumidor:");
        lblRutMostrar.setVisible(true);
        lblRut.setVisible(true);
        lblmostrarNomreCom.setVisible(true);
        lblnombreCompleto.setVisible(true);
        lblApellidoscom.setVisible(true);
        lblMostraremail.setVisible(true);
        lblEmail.setVisible(true);
        lblMostrarContra.setVisible(true);
        lblContra.setVisible(true);
        lnlmostrarCoomuna.setVisible(true);
        lblComuna.setVisible(true);
        lblInfoConsumi.setVisible(true);
        lblActividad.setVisible(true);
        lblmostrarSubcrip.setVisible(true);
        lblSuscrito.setVisible(true);       
        }
        
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
    private javax.swing.JCheckBox chk_activo;
    private javax.swing.JCheckBox chk_suscrito;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblActividad;
    private javax.swing.JLabel lblApellidoscom;
    private javax.swing.JLabel lblComuna;
    private javax.swing.JLabel lblContra;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblInfoConsumi;
    private javax.swing.JLabel lblMostrarContra;
    private javax.swing.JLabel lblMostraremail;
    private javax.swing.JLabel lblRut;
    private javax.swing.JLabel lblRutMostrar;
    private javax.swing.JLabel lblSuscrito;
    private javax.swing.JLabel lbl_activo;
    private javax.swing.JLabel lbl_apellidos;
    private javax.swing.JLabel lbl_clave;
    private javax.swing.JLabel lbl_comuna;
    private javax.swing.JLabel lbl_comuna_id;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_nombres;
    private javax.swing.JLabel lbl_puntaje;
    private javax.swing.JLabel lbl_rut;
    private javax.swing.JLabel lbl_suscrito;
    private javax.swing.JLabel lblmostrarNomreCom;
    private javax.swing.JLabel lblmostrarSubcrip;
    private javax.swing.JLabel lblnombreCompleto;
    private javax.swing.JLabel lnlmostrarCoomuna;
    private javax.swing.JPanel pInfo;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tab_consumidores;
    private javax.swing.JTextField txt_apellido1;
    private javax.swing.JTextField txt_apellido2;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nombre1;
    private javax.swing.JTextField txt_nombre2;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_puntaje;
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
        validaciones.add('+');
        return validaciones;
    }

}

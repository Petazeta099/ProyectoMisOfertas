/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import Conexion.Database;
import Vistas.login_Admin;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
/**
 *
 * @author Jonathan
 */
public class GeneradorMail {
    
    	private final Properties properties = new Properties();

    private String password;

    private Session session;

    public void EnviarEmail() throws IOException {

        final String auth_host = "smtp.gmail.com";
        final String auth_port = "587";
        final String auth_email = "shopcheap.misofertas@gmail.com";
        final String auth_password = "misofertas";

        final Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", auth_host);
        props.put("mail.smtp.socketFactory.port", auth_port);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", auth_port);

        try {

            Session mailSession = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication
                        getPasswordAuthentication() {
                    return new PasswordAuthentication(auth_email, auth_password);
                }
            });

            Message message = new MimeMessage(mailSession);

            message.setFrom(new InternetAddress(auth_email)); // DE

            message.setSubject("Hola");
            message.addRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(listaCorreos()));  // lista correos

                             
		              /*** Recipiente ***/
            //----------------
            // crear mensaje 
            BodyPart messageBodyPart = new MimeBodyPart();

            //fill message
            String htmlText = "<H3>Prueba</H3><img src=\"cid:image\">"+ Arrays.toString(mostrarOfertasActivasCorreos()).substring(1,Arrays.toString(mostrarOfertasActivasCorreos()).length()-1).replace(',',' ');
            //messageBodyPart.setText("Haciendo prueba \n");
            messageBodyPart.setContent(htmlText, "text/html");

            Multipart multipart = new MimeMultipart("related");
            multipart.addBodyPart(messageBodyPart);

            //MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            // Part two is attachment
            /*messageBodyPart = new MimeBodyPart();
		              DataSource source = new FileDataSource(Attach);
		              messageBodyPart.setDataHandler(new DataHandler(source));
		              
		              String fileName = Attach.substring(Attach.lastIndexOf('\\')+1, Attach.length()); */
            //  messageBodyPart2.setText("Parrafo 2");
            //  multipart.addBodyPart(messageBodyPart2);
            /////////////// no borrar//////
            /*           MimeBodyPart messageBodyPart3 = new MimeBodyPart();
                            String str = System.getProperty("user.dir")+"/src/Imagenes/fondoAdmin.jpg";
                    DataSource source = new FileDataSource(str);
                    messageBodyPart3.setDataHandler(new DataHandler(source));
                    messageBodyPart3.setFileName(str);
                    multipart.addBodyPart(messageBodyPart3); */
            ////////////////////////////////////////////////////////////
            //correo
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(System.getProperty("user.dir") + "/src/Imagenes/banner.jpg");
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");
            multipart.addBodyPart(messageBodyPart);

            ///////////////////////
            message.setContent(multipart);

            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Correos enviados satisfactoriamente");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

      
    }
    
    public String listaCorreos(){  //arroja todos los correos de las personas sucritas
         char sus = '1';
         String lista ="";
        ResultSet lista2 = Database.crearConsulta("SELECT email FROM consumidor where suscrito='" + sus + "'");

        try {
            while (lista2.next()) {
                //System.out.println(lista2.getString(1));
                lista = lista +","+ lista2.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(login_Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(lista.substring(1, lista.length())); //para ver mas que nada
        return lista.substring(1, lista.length());
    }
    
    public String[] mostrarOfertasActivasCorreos(){
        //llama las ofertas activas
        char act = '1';
        String lista = "";
       // ArrayList<String> listaArray = new ArrayList<>();
        ResultSet lista1 = Database.crearConsulta("SELECT * FROM oferta where activa= '" + act + "'order by id asc");
        String[] arregloOf  = new String[0];
        try {
            while (lista1.next()) {
                int largo = arregloOf.length;
                arregloOf = Arrays.copyOf(arregloOf, largo + 1);
                arregloOf[largo] = "<p>------------------------------<br/>"+lista1.getString(2) +"<br>"+ lista1.getString(3) +"<br>$"+ lista1.getString(6)+"<br>$"+ lista1.getString(7)+"</p>";
                System.out.println(arregloOf[largo]);
                
                 

                
            }
        } catch (SQLException ex) {
            Logger.getLogger(login_Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arregloOf;
    }
}

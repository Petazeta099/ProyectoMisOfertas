/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import java.io.IOException;
import java.util.Properties;
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
 
	
	public void EnviarEmail() throws IOException{
 
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
		                              return new PasswordAuthentication
		                                (auth_email,auth_password);
		                          }
		                      });
		        	  
		        	  Message message = new MimeMessage(mailSession);
		        	  
		              message.setFrom(new InternetAddress(auth_email)); // DE
		              
                              message.setSubject("Hola bb");
                             message.addRecipients(Message.RecipientType.CC, 
                      InternetAddress.parse("delxenkin@gmail.com,jona.kenny@gmail.com,jon.reyesm@alumnos.duoc.cl"));
                              
		              /*** Recipient ***/
		            
		              // create the message part 
		              MimeBodyPart messageBodyPart = new MimeBodyPart();
		             
		              //fill message
		              messageBodyPart.setText("Haciendo prueba \n");
                              

		              Multipart multipart = new MimeMultipart();
		              multipart.addBodyPart(messageBodyPart);
                              
                              MimeBodyPart messageBodyPart2 = new MimeBodyPart();

		              // Part two is attachment
		            
                              /*messageBodyPart = new MimeBodyPart();
		              DataSource source = new FileDataSource(Attach);
		              messageBodyPart.setDataHandler(new DataHandler(source));
		              
		              String fileName = Attach.substring(Attach.lastIndexOf('\\')+1, Attach.length()); */
		              messageBodyPart2.setText("Parrafo 2");
		              multipart.addBodyPart(messageBodyPart2);
                              
                              
                              /////////////// no borrar//////
		               MimeBodyPart messageBodyPart3 = new MimeBodyPart();
                            String str = System.getProperty("user.dir")+"/src/Imagenes/fondoAdmin.jpg";
                    DataSource source = new FileDataSource(str);
                    messageBodyPart3.setDataHandler(new DataHandler(source));
                    messageBodyPart3.setFileName(str);
                    multipart.addBodyPart(messageBodyPart3);
 ////////////////////////////////////////////////////////////
 //prueba
   MimeBodyPart messageBodyPart4 = new MimeBodyPart();
                            DataSource fds = new FileDataSource(System.getProperty("user.dir")+"/src/Imagenes/fondoAdmin.jpg");
                            messageBodyPart4.setDataHandler(new DataHandler(fds));
 
 ///////////////////////
 
 
                              
                              
		              message.setContent(multipart);

		              Transport.send(message);

		              JOptionPane.showMessageDialog(null,"Correos enviados satisfactoriamente");

		          } catch (MessagingException e) {
		              throw new RuntimeException(e);
		          }
        
		
	}
        
        public void generarYEnviarEmail(String maildestino, String encabezado, String cuerpo) throws AddressException, MessagingException {

        String username = "seguros.chile.regional.2017@gmail.com";
        String password = "notengopass";
        String to = maildestino;
        String subject = encabezado;
        String email_body = cuerpo;

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        }
        );
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(email_body);
            Transport.send(message);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    
}

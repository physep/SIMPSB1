package simpsb.controller;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mailer {

    public static void send(String desti, String asunto, String mensaje) throws UnsupportedEncodingException {
        final String user = "physep17@gmail.com";
        final String pass = "1000320221a";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        //Primer paso, obtener el objeto de sesión 
        
        Session session;
        session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        
        //Segundo paso, armar el mensaje
        try {
            //BodyPart adjunto = new MimeBodyPart();
            //adjunto.setDataHandler(new DataHandler(new FileDataSource("C:/Users/SebastianParra/Downloads/image.png")));
            //adjunto.setFileName("Imagen de prueba");
            
            BodyPart texto = new MimeBodyPart();
            texto.setContent(mensaje, "text/html");
            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(texto);
            
            //multiparte.addBodyPart(adjunto);
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user, "SIMPSB"));
            InternetAddress destinatarios = new InternetAddress(desti);
            
            message.setRecipient(Message.RecipientType.TO, destinatarios);
            message.setSubject(asunto);
            message.setContent(multiparte, "text/html; charset=utf-8");
            
            //Tercer paso, enviar el mensaje
            Transport.send(message);
            System.out.println("Mensaje enviado");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
         
    }
}

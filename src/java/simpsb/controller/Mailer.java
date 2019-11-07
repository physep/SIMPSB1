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

    public void send() throws UnsupportedEncodingException {
        final String user = "physep17@gmail.com";
        final String pass = "1000320221a";
        final String host = "smtp.gmail.com";
        MailController mailC = new MailController();
        try {
            //SE CREA LA VARIABLE PROPERTIES
            Properties props = new Properties();
            //SE ESTABLECE LA CONEXION
            props.setProperty("mail.smtp.host", host);
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.starttls.required", "false");

            props.setProperty("mail.smtp.user", user);
            props.setProperty("mail.smtp.password", pass);
            props.setProperty("mail.smtp.auth", "true");

            //SE CREA LA SESION
            Session session;
            session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pass);
                }
            });
            
            // SE CREA LA VARIABLE MIME
            MimeMessage message = new MimeMessage(session);
            
            //INGRESO AL CORREO AL QUE SE VA A ENVIAR
            message.setFrom(new InternetAddress(mailC.getDestinatario()));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailC.getDestinatario()));
            
            //ASUNTO DEL CORREO
            message.setSubject(mailC.getAsunto());
            message.setContent(mailC.getMensaje(), "text/html; charset=utf-8");
            
            //CREO LA VARIABLE TRANSPORT
            Transport trans = session.getTransport("smtp");

            //CREO LA CONEXION
            trans.connect(host, user, pass);

            //ENVIO DEL CORREO
            trans.sendMessage(message, message.getAllRecipients());

            //CIERRO LA CONEXION
            trans.close();
        } catch (Exception e) {
        }

    }
}

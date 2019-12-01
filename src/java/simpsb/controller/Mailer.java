package simpsb.controller;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {

    public void send(String destinatario, String asunto, String mensaje) throws UnsupportedEncodingException {
        final String user = "physep17@gmail.com";
        final String pass = "1000320221a";
        final String host = "smtp.gmail.com";

        //SE CREA LA VARIABLE PROPERTIES
        Properties props = new Properties();
        //SE ESTABLECE LA CONEXION
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.required", "false");

        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", host);

        //SE CREA LA SESION
        Session session;
        session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        try {
            // SE CREA LA VARIABLE MIME
            MimeMessage message = new MimeMessage(session);

            //INGRESO AL CORREO AL QUE SE VA A ENVIAR
            message.setFrom(new InternetAddress(user, "SIMPSB"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

            //ASUNTO DEL CORREO
            message.setSubject(asunto);
            message.setContent(mensaje, "text/html; charset=utf-8");

            //CREO LA VARIABLE TRANSPORT
            Transport.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

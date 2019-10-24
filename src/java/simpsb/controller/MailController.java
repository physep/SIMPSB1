package simpsb.controller;

import java.io.UnsupportedEncodingException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MailController {

    public MailController() {

    }
    String asunto = "Recuperación de contraseña";
    String destinatario = "";
    String mensaje = "";

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void enviarCorreo() throws UnsupportedEncodingException {
        Mailer.send(destinatario, asunto, mensajeContra());
    }

    public String mensajeContra() {
        return "<body style=\"@import url('https://fonts.googleapis.com/css?family=Josefin+Sans');\n"
                + "margin: 0;\n"
                + "padding: 0;\n"
                + "font-family: 'Josefin Sans', sans-serif;\n"
                + "\">"
                + "<div style = \"width: 100%; float: left; padding: 20px; margin: 20px; border: 10px solid rgb(230,230,230);\">\n"
                + "<h1 style = \"margin-bottom: 40px;\"> Restablecer contraseña</h1>"
                + "<p style = \"color: rgb(110,110,110); margin-bottom: -10px;\"> Has solicitado restablecer tu contraseña.</p>"
                + "<p style = \"color: rgb(110,110,110);\"> Para continuar, haz clic en el siguiente botón:</p>"
                + "<button style = \"text-decoration: none; padding: 10px; color: #000; background-color: #D4AF37; border: 0px; font-weight: 600; margin: 10px; margin-left: 0;\" >RESTABLECER </button>"
                + "<p style = \"color: rgb(110,110,110); font-size: 13px;\"> Puede copiar y pegar este enlace en su navegador:</p>"
                + "<p style = \"color: rgb(110,110,110); font-size: 13px;\">https://www.youtube.com/</p> "
                + "<br>"
                + "<br>"
                + "<br>"
                + "<br>"
                + "<p style = \"color: rgb(110,110,110); font-size: 13px;\"> El vínculo anterior expirara en unas cuantas horas.</p>"
                + "<p style = \"color: rgb(110,110,110); font-size: 13px;\"> Si no has solicitado cambiar la contraseña, ignora este mensaje y no se realizara ningún cambio.</p>"
                + "</div>"
                + "</body>\n";
    }

}

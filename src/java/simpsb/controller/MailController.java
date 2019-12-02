package simpsb.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import simpsb.dao.*;
import simpsb.entidades.*;

@Named
@RequestScoped
public class MailController {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    private Usuario usuario;

    @EJB
    private CitasFacadeLocal citasFacadeLocal;
    private Citas citas;

    public MailController() {
        usuario = new Usuario();
        citas = new Citas();
    }
    //INICIALIZO EL MAILER
    Mailer mailer = new Mailer();
    String asunto = "";
    String destinatario = "";
    String mensaje = "";

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

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

    public void recuperacionContra() throws UnsupportedEncodingException {
        //Creo las variables 
        int pass;
        String newPass = "";
        Usuario user;
        user = usuarioFacadeLocal.getId(usuario.getNumDocumento());
//        //Verifico que el usuario exista
        if (user != null) {
            //Creo la nueva contraseña    
            pass = (int) (Math.random() * 34520 + 23);
            newPass = "Vx" + pass + "*";
//            }
//            //Edito la nueva contraseña
//            usuario.setPass(newPass);
//            //Guardo la nueva contraseña
//            usuarioFacadeLocal.edit(usuario);

            //Creo el cuerpo del mensaje
            String text;
            text = "<body style=\"@import url('https://fonts.googleapis.com/css?family=Josefin+Sans');\n"
                    + "margin: 0;\n"
                    + "padding: 0;\n"
                    + "font-family: 'Josefin Sans', sans-serif;\n"
                    + "\">"
                    + "<div style = \"width: 90%; float: left; padding: 20px; margin: 20px; border: 10px solid rgb(230,230,230);\">\n"
                    + "<h1 style = \"margin-bottom: 40px;\"> Restablecer contraseña</h1>"
                    + "<p style = \"color: rgb(110,110,110); margin-bottom: -10px;\"> Has solicitado restablecer tu contraseña.</p>"
                    + "<p style = \"color: rgb(110,110,110);\"> Esta es su nueva contraseña, puede cambiarla después de ingresar al sistema:</p>"
                    + "<p style = \"color: rgb(110,110,110); font-size: 20px;\">" + newPass + "</p>"
                    + "<br>"
                    + "<br>"
                    + "<br>"
                    + "<br>"
                    + "<p style = \"color: rgb(110,110,110); font-size: 13px;\"> Si no has solicitado cambiar la contraseña, ignora este mensaje y no se realizara ningún cambio.</p>"
                    + "</div>"
                    + "</body>\n";
            this.setMensaje(text);
            this.setDestinatario(destinatario);
            this.setAsunto("Recuperación de contraseña");
            //Envio el correo
            mailer.send(destinatario, asunto, mensaje);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Se envio la contraseña a su correo"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "El usuario no está registrado en el sistema"));
        }
    }

    public void citas() throws UnsupportedEncodingException {
        //Defino destinatario
        citas.getIdCita();
        Cliente cor = citas.getIdCliente();
        this.setDestinatario(citas.getIdCliente().getIdUsuario().getCorreo());
        //Defino asunto
        this.setAsunto("Se ha agendado correctamente su cita");
        //Defino mensaje
        String text;
        text = "<body style=\"@import url('https://fonts.googleapis.com/css?family=Josefin+Sans');\n"
                + "margin: 0;\n"
                + "padding: 0;\n"
                + "font-family: 'Josefin Sans', sans-serif;\n"
                + "\">"
                + "<div style = \"width: 90%; float: left; padding: 20px; margin: 20px; border: 10px solid rgb(230,230,230);\">\n"
                + "<h1 style = \"margin-bottom: 40px;\">Se ha agendado correctamente su cita</h1>"
                + "<p style = \"color: rgb(110,110,110); margin-bottom: -10px;\">" + citas.getIdCliente().getIdUsuario().getNombre() + " su cita se ha agendado exitosamente</p>"
                + "<p style = \"color: rgb(110,110,110);\">Datos de la cita:</p>"
                + "<p style = \"color: rgb(110,110,110);\"> Hora: " + citas.getHora() + "</p>"
                + "<p style = \"color: rgb(110,110,110);\"> Fecha: " + citas.getFecha() + "</p>"
                + "<p style = \"color: rgb(110,110,110);\"> " + citas.getIdEmpleado().getIdCargo().getCargo() + ": " + citas.getIdEmpleado().getIdUsuario().getNombre() + "</p>"
                + "<p style = \"color: rgb(110,110,110);\"> Servicios: " + citas.getIdServicio().getNombre() + "</p>"
                + "<br>"
                + "<br>"
                + "<br>"
                + "<br>"
                + "<p style = \"color: rgb(110,110,110); font-size: 13px;\">Por favor presentarse 10 minutos antes de la hora asignada para la cita.</p>"
                + "</div>"
                + "</body>\n";
        this.setMensaje(text);
        //Envio mensaje
        mailer.send(destinatario, asunto, mensaje);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Se han enviado los datos de su cita al correo."));

    }

}

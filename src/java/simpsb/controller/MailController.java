package simpsb.controller;

import java.io.UnsupportedEncodingException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import simpsb.dao.UsuarioFacadeLocal;
import simpsb.entidades.Usuario;

@Named
@RequestScoped
public class MailController {
    
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    private Usuario usuario;
    
    public MailController() {
        usuario = new Usuario();
    }
    //INICIALIZO EL MAILER
    Mailer mailer = new Mailer();
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
    
    public void recuperacionContra() throws UnsupportedEncodingException {
        //Creo las variables 
        
        Usuario user = null;
        String newPass = "";

        //Busco el usuario
        user = usuarioFacadeLocal.find(usuario.getIdUsuario());
        //Verifico que el usuario exista
        if (user != null) {
            //Creo la nueva contraseña    
            int a;
            for (int i = 0; i < 7; i++) {
                if (i < 4) {    // 0,1,2,3 posiciones de numeros
                    newPass = (int) (Math.random() * 9) + "" + newPass;
                    
                } else {       // 4,5,6 posiciones de letras
                    do {
                        a = (int) (Math.random() * 26 + 65);///
                    } while (a == 65 || a == 69 || a == 73 || a == 79 || a == 85);
                    
                    char letra = (char) a;
                    if (i == 4) {
                        newPass = newPass + "-" + letra;
                    } else {
                        newPass = newPass + "" + letra;
                    }
                    
                }
            }
            //Edito la nueva contraseña
            usuario.setPass(newPass);
            //Guardo la nueva contraseña
            usuarioFacadeLocal.edit(usuario);

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
            this.setDestinatario(usuario.getCorreo());
            //Envio el correo
            mailer.send();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Se envio la contraseña a su correo"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "El usuario no está registrado en el sistema"));
        }
        
    }
    
}

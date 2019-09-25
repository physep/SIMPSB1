package simpsb.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import simpsb.dao.*;
import simpsb.entidades.*;


@Named
@ViewScoped
public class SesionController implements Serializable{
    
    @EJB 
    private UsuarioFacadeLocal usuarioFacadeLocal;
    private Usuario usuario;
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String iniciarSesion(){
        Usuario u;
        String url = null;
        try {
            usuarioFacadeLocal.login(usuario);
            url = "SI/app/Perfiles/indexSupervisor";
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al iniciar sesión"));
        }
        return url;
    }
}

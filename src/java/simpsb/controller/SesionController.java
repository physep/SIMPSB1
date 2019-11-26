package simpsb.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import simpsb.dao.*;
import simpsb.entidades.*;
import simpsb.controller.*;

@Named
@SessionScoped
public class SesionController implements Serializable {

    @Inject 
    private Utils util;
    
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String iniciarSesion() {
        Usuario u = null;
        String url = null;
        try {
            u = usuarioFacadeLocal.login(usuario);
            if (u != null) {
                util.add("usuario", u);
                String rol = u.getIdRol().getRol();
                switch (rol) {
                    case "Cliente":
                        url = "Cliente/indexCliente?faces-redirect=true";
                        break;
                    case "Empleado":
                        url = "Empleado/indexEmpleado?faces-redirect=true";
                        break;
                    case "Supervisor":
                        url = "Supervisor/indexSupervisor?faces-redirect=true";
                        break;
                    default:
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Credenciales incorrectas"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al iniciar sesión"));
        }
        return url;
    }

    public void verificarSesion() {
        try {
            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            if (u == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../../../../Error/404.xhtml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "../../";
    }
}

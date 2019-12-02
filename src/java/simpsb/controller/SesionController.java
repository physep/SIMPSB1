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
        String url = null;
        Usuario u;
        try {
            u = usuarioFacadeLocal.login(usuario);
            if (u != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);
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
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso:", "Credenciales incorrectas"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error:", "Error al iniciar sesion"));
            e.printStackTrace();
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

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
import simpsb.controller.*;

@Named
@ViewScoped
public class SesionController implements Serializable {

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
                String rol = u.getIdRol().getRol();
                switch (rol) {
                    case "Cliente":
                        url = "Perfiles/indexCliente?faces-redirect=true";
                        break;
                    case "Empleado":
                        url = "Perfiles/indexEmpleado?faces-redirect=true";
                        break;
                    case "Supervisor":
                        url = "Perfiles/indexSupervisor?faces-redirect=true";
                        break;
                    default:
                }
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);
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
        return "../../../";
    }
}

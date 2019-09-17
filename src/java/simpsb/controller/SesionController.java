package simpsb.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import simpsb.entidades.*;
import simpsb.dao.*;

@Named
@ViewScoped
public class SesionController implements Serializable {

    @EJB
    private Usuario usuario;
    private UsuarioFacadeLocal usuarioFacadeLocal;

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

    public String login() {
        Usuario u;
        String url = null;
        try {
            u = usuarioFacadeLocal.login(usuario);
            if (u != null) {
                String rol = u.getIdRol().getRol();
                switch (rol) {
                    case "Cliente":
                        url = "SI/app/Perfiles/indexCliente.xhtml";
                        break;
                    case "Supervisor":
                        url = "SI/app/Perfiles/indexSupervisor.xhtml";
                        break;
                    case "Empleado":
                        url = "SI/app/Perfiles/indexEmpleado.xhtml";
                        break;
                    default:
                }
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Credenciales incorrectas"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al iniciar sesión"));
            e.printStackTrace();
        }

        return url;
    }

    public void verificarSesion(){
        try {
            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            if (u == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Error/404.xhtml");
            }
        } catch (Exception e) {
        }
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

}

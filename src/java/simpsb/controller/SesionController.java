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
        String url = null;
        Usuario u;
        try {
            usuarioFacadeLocal.login(usuario);
            url = "index";
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al iniciar sesión"));
        }
        return url;
    }
}
 //   public String iniciarSesion() {
   //     String url = null;
     //   Usuario u;
       // try {
         //   u = usuarioFacadeLocal.login(usuario);
           // if (u != null) {
          //      String rol = u.getRolFK().getRol();
           //     switch (rol) {
             //       case "Usuario":
               //         url = "app/user/principal?faces-redirect=true";
                 //       break;
                   // case "Administrador":
                     //   url = "app/admin/principal?faces-redirect=true";
   //                     break;
 //                   default:
       //         }
     //           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);
         //   } else {
           //     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso:", "Credenciales incorrectas"));
 //           }
   //     } catch (Exception e) {
////            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso:", "Error al iniciar sesion"));
     //       e.printStackTrace();
       // }
        //return url;
    //}

   // public void vericarSesion() {
     //   try {
       //     Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
         //   if (u == null) {
           //     FacesContext.getCurrentInstance().getExternalContext().redirect("./../../permisos.xhtml");
            //}
      //  } catch (Exception e) {
       // }
  //  }

    //public String logout() {
      //  FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //return "/index.xhtml?faces-redirect=true";
    //}

//}

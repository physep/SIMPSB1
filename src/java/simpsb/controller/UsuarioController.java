package simpsb.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import simpsb.dao.*;
import simpsb.entidades.*;

@Named
@RequestScoped
public class UsuarioController {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    private Usuario usuario;

    @EJB
    private RolesFacadeLocal rolesFacadeLocal;
    private Roles roles;
    private List<Roles> listRol;

    @EJB
    private ClienteFacadeLocal clienteFacadeLocal;
    private Cliente cliente;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        roles = new Roles();
        cliente = new Cliente();
        listRol = rolesFacadeLocal.findAll();
    }

    public List<Roles> getListRol() {
        return listRol;
    }

    public void setListRol(List<Roles> listRol) {
        this.listRol = listRol;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public void registrarUsuario() {
        try {
            roles.setIdRol(3);
            usuario.setIdRol(roles);
            usuarioFacadeLocal.create(usuario);
            cliente.setIdUsuario(usuario);
            clienteFacadeLocal.create(cliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha registrado exitosamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al registrarse"));
        }
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> listUsu = null;
        try {
            listUsu = usuarioFacadeLocal.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUsu;
    }

    public void eliminarUsuario(Usuario usuario) {
        try {
            usuarioFacadeLocal.remove(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al eliminar"));
        }
    }

    public String consultarUsuario(Usuario user) {
        try {
            usuario = usuarioFacadeLocal.find(user.getIdUsuario());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Funciona correcto"));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error"));
        }
        return "modificarUsuario";
    }

    public void modificarUsuario() {
        try {
            roles.setIdRol(3);
            usuario.setIdRol(roles);
            usuario.getPass();
            usuarioFacadeLocal.edit(usuario);

            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarUsuario.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error"));
        }
    }
}

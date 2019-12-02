package simpsb.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import simpsb.dao.*;
import simpsb.entidades.*;

@Named
@RequestScoped
public class UsuarioController {

    @Inject 
    private Utils util;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @EJB
    private ClienteFacadeLocal clienteFacadeLocal;
    @EJB
    private EmpleadoFacadeLocal empleadoFacadeLocal;
    @EJB
    private RolesFacadeLocal rolesFacadeLocal;
    
    private Usuario usuario;
    private Roles roles;
    private Cliente cliente;
    private Empleado empleado;
    private Part file;
    private String nombre;
    private String pathReal;

    @PostConstruct
    public void init() {
        //ENTIDADES
        usuario = new Usuario();
        roles = new Roles();
        cliente = new Cliente();
        empleado = new Empleado();
    }


    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPathReal() {
        return pathReal;
    }

    public void setPathReal(String pathReal) {
        this.pathReal = pathReal;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
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

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    //MÉTODOS CRUD DE USUARIOS
    public void registrarUsuario() {
        Usuario user = null;
        try {
            user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
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
    
    public void crearUsuario(){
        Usuario user = null;
        try {
            user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            roles.setIdRol(3);
            usuario.setIdRol(roles);
            usuarioFacadeLocal.create(usuario);
            cliente.setIdUsuario(usuario);
            clienteFacadeLocal.create(cliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha registrado exitosamente"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarUsuario.xhtml");
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

    public void eliminarUsuario(Usuario user) {
        try {
            empleadoFacadeLocal.remove(empleado);
            clienteFacadeLocal.remove(cliente);
            usuarioFacadeLocal.remove(user);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error al eliminar"));
        }
    }

    public String consultarUsuario(Usuario user) {
        try {
            usuario = usuarioFacadeLocal.find(user.getIdUsuario());
            roles = usuario.getIdRol();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Funciona correcto"));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error"));
        }
        return "modificarUsuario";
    }

    public void modificarUsuario() {
        try {
            usuario.getPass();
            roles = rolesFacadeLocal.find(roles.getIdRol());
            usuario.setIdRol(roles);
            usuarioFacadeLocal.edit(usuario);
            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarUsuario.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error"));
        }
    }

    //MÉTODOS PARA LA EDICIÓN DEL PERFIL CLIENT SIDE
    public void cambiarContra() {
        try {
            usuarioFacadeLocal.edit(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha cambiado su contraseña exitosamente"));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Ha ocurrido un error"));
        }
    }

    public void editarPerfil() {
        Usuario us = null;
        try {
            us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            usuario.setIdUsuario(us.getIdUsuario());
            usuarioFacadeLocal.edit(usuario);
        } catch (Exception e) {
        }
    }
}

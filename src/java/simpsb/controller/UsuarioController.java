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
    @EJB
    private RolesFacadeLocal rolesFacadeLocal;
    @EJB
    private ClienteFacadeLocal clienteFacadeLocal;
    @EJB
    private HorariotrabajoFacadeLocal horariotrabajoFacadeLocal;
    @EJB
    private CargosFacadeLocal cargosFacadeLocal;
    @EJB
    private EmpleadoFacadeLocal empleadoFacadeLocal;
    @EJB
    private DiadescansoFacadeLocal diadescansoFacadeLocal;

    private Usuario usuario;
    private Roles roles;
    private Cliente cliente;
    private Cargos cargos;
    private Empleado empleado;
    private Horariotrabajo horariotrabajo;
    private Diadescanso diadescanso;

    private List<Roles> listRol;
    private List<Cargos> listCargos;
    private List<Horariotrabajo> listHorario;
    private List<Diadescanso> listDiaD;

    @PostConstruct
    public void init() {
        //ENTIDADES
        cargos = new Cargos();
        diadescanso = new Diadescanso();
        horariotrabajo = new Horariotrabajo();
        usuario = new Usuario();
        roles = new Roles();
        cliente = new Cliente();
        empleado = new Empleado();
        //LISTAS
        listDiaD = diadescansoFacadeLocal.findAll();
        listCargos = cargosFacadeLocal.findAll();
        listHorario = horariotrabajoFacadeLocal.findAll();
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

    public Diadescanso getDiadescanso() {
        return diadescanso;
    }

    public void setDiadescanso(Diadescanso diadescanso) {
        this.diadescanso = diadescanso;
    }

    public List<Diadescanso> getListDiaD() {
        return listDiaD;
    }

    public void setListDiaD(List<Diadescanso> listDiaD) {
        this.listDiaD = listDiaD;
    }

    public Horariotrabajo getHorariotrabajo() {
        return horariotrabajo;
    }

    public void setHorariotrabajo(Horariotrabajo horariotrabajo) {
        this.horariotrabajo = horariotrabajo;
    }

    public List<Horariotrabajo> getListHorario() {
        return listHorario;
    }

    public void setListHorario(List<Horariotrabajo> listHorario) {
        this.listHorario = listHorario;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cargos getCargos() {
        return cargos;
    }

    public void setCargos(Cargos cargos) {
        this.cargos = cargos;
    }

    public List<Cargos> getListCargos() {
        return listCargos;
    }

    public void setListCargos(List<Cargos> listCargos) {
        this.listCargos = listCargos;
    }
    
    //MÉTODOS CRUD DE USUARIOS
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
    
    //MÉTODOS PARA CAMBIAR EL ROL A EMPLEADO
    public String consultarRol(Object idUsuario) {
        try {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Funciona correcto"));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error"));
        }
        return "asignarRol";
    }

    public void asignarRol(Object idUsuario) {
        try {
            usuario.getIdUsuario();
            empleado.setIdUsuario(usuario);
            //Asigno todos los valores del empleado y lo creo
            empleado.setIdCargo(cargos);
            empleado.setIdDiaDescanso(diadescanso);
            empleado.setIdHorarioTrabajo(horariotrabajo);
            empleadoFacadeLocal.create(empleado);
            //Cambio el rol en la tabla usuario
            roles.setIdRol(2);
            usuario.setIdRol(roles);
            usuarioFacadeLocal.edit(usuario);
            //Elimino el cliente
            clienteFacadeLocal.remove(cliente);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha asignado correctamente el rol"));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Ha ocurrido un error"));

        }
    }
    
    //MÉTODOS PARA LA EDICIÓN DEL PERFIL CLIENT SIDE
    public void cambiarContra(){
        try {
            usuarioFacadeLocal.edit(usuario);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha cambiado su contraseña exitosamente"));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Ha ocurrido un error"));
        }
    }
    
    public void editarPerfil(){
        Usuario us = null;
        try {
            us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            usuario.setIdUsuario(us.getIdUsuario());
            usuarioFacadeLocal.edit(usuario);
        } catch (Exception e) {
        }
    }
}

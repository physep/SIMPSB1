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
public class EmpleadoController {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @EJB
    private CargosFacadeLocal cargosFacadeLocal;
    @EJB
    private EmpleadoFacadeLocal empleadoFacadeLocal;
    @EJB
    private HorariotrabajoFacadeLocal horariotrabajoFacadeLocal;
    @EJB
    private RolesFacadeLocal rolesFacadeLocal;
    @EJB
    private DiadescansoFacadeLocal diadescansoFacadeLocal;
    @EJB
    private ClienteFacadeLocal clienteFacadeLocal;
    
    private Usuario usuario;
    private Cargos cargos;
    private Empleado empleado;
    private Roles roles;
    private Horariotrabajo horariotrabajo;
    private Diadescanso diadescanso;
    private Cliente cliente;

    //LISTAS
    private List<Cargos> listCargos;
    private List<Horariotrabajo> listHorario;
    private List<Diadescanso> listDiaD;

    @PostConstruct
    public void init() {
        empleado = new Empleado();
        cargos = new Cargos();
        diadescanso = new Diadescanso();
        horariotrabajo = new Horariotrabajo();
        usuario = new Usuario();
        roles = new Roles();
        cliente = new Cliente();
        listDiaD = diadescansoFacadeLocal.findAll();
        listCargos = cargosFacadeLocal.findAll();
        listHorario = horariotrabajoFacadeLocal.findAll();
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public String consultarRol(Usuario user) {
        try {
            usuario = usuarioFacadeLocal.find(user.getIdUsuario());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Funciona correcto"));
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ha ocurrido un error"));
        }
        return "asignarRol";
    }

    public void asignarRol(Cliente cl) {
        try {
            //Asigno todos los valores del empleado y lo creo
            usuario = usuarioFacadeLocal.find(usuario.getIdUsuario());
            empleado.setIdUsuario(usuario);
            empleado.setIdCargo(cargos);
            empleado.setIdDiaDescanso(diadescanso);
            empleado.setIdHorarioTrabajo(horariotrabajo);
            empleadoFacadeLocal.create(empleado);
            //Cambio el rol en la tabla usuario
            roles.setIdRol(2);
            usuario.setIdRol(roles);
            usuarioFacadeLocal.edit(usuario);
            //Elimino el cliente
            usuario = cliente.getIdUsuario();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se ha asignado correctamente el rol"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarUsuario.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Ha ocurrido un error"));

        }
    }

}
